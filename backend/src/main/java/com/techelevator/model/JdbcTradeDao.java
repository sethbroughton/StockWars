package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JdbcTradeDao implements TradeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcTradeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long createNewTrade(Trade trade) {
		Long tradeId = null;
		BigDecimal accountBalance = null;
		int quantityBought = 0;
		int quantitySold = 0;
		int shareTotal = 0;
		
		if(trade.getQuantity()<=0) {
			return null;
		}
	    
	    //Check for sufficient Shares to sell
	    //TODO: This sql query needs to be changed or we need to put negative 
	    // quantities into database when selling shares
	    
	    if(trade.getType().equals("SELL")) {   
	    //Get current share count
		    String sqlGetSharesBought = "SELECT sum(quantity) FROM trade WHERE (portfolio_id=? AND ticker=? AND type='BUY')";
		    SqlRowSet sharesBoughtResult = jdbcTemplate.queryForRowSet(sqlGetSharesBought, trade.getPortfolioId(), trade.getTicker());
		    if (sharesBoughtResult.next()) {
		    	quantityBought = sharesBoughtResult.getInt("sum");
		    }
		    
		    String sqlGetSharesSold = "SELECT sum(quantity) FROM trade WHERE (portfolio_id=? AND ticker=? AND type='SELL')";
		    SqlRowSet shareSoldResult = jdbcTemplate.queryForRowSet(sqlGetSharesSold, trade.getPortfolioId(), trade.getTicker());
		    if (shareSoldResult.next()) {
		    	quantitySold = shareSoldResult.getInt("sum");
		    }
		    shareTotal = quantityBought-quantitySold;
		    System.out.println(shareTotal);
		    
		//If trying to sell more shares than possible then return null
		  if(trade.getQuantity()>shareTotal) {  
		    return null;   
		  	}
	    }
	    
		//Get current CASH balance from portfolio table
		String sqlGetCurrentCashBalance = "SELECT cash from portfolio where portfolio_id=?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCurrentCashBalance, trade.getPortfolioId());
	    if (results.next()) {
	    	accountBalance = new BigDecimal(results.getString("cash"));
	    }
	    
		//BUY (subtract money) or SELL (add money)
		BigDecimal newBalance = null;
		if(trade.getType().equals("BUY")) {
			if(accountBalance.subtract(trade.getStockValue()).compareTo(BigDecimal.ZERO)>0) {
				newBalance = accountBalance.subtract(trade.getStockValue());
			} else {
				return null;
			}		
		} else {
			newBalance = accountBalance.add(trade.getStockValue());
		}
		
		//Updated current CASH balance in portfolio table
		String sqlUpdateAccountBalance = "UPDATE portfolio SET cash = ? WHERE portfolio_id = ?";
		jdbcTemplate.update(sqlUpdateAccountBalance, newBalance, trade.getPortfolioId());
		
		//Create trade in trade table
		String sqlInsertNewTrade = "INSERT INTO trade (portfolio_id, type, ticker, "
				+ "quantity, stock_value, commission, date_of_purchase) VALUES (?, ?, ?, ?, ?, ?, ?) "
				+ "RETURNING trade_id";
		
		//If it a sale, change the quantity to a negative
		if(trade.getType().equals("SELL")) {
			trade.setQuantity(-trade.getQuantity());
		}

		SqlRowSet insertResult = jdbcTemplate.queryForRowSet(sqlInsertNewTrade, trade.getPortfolioId(), trade.getType(), trade.getTicker(),
				trade.getQuantity(), trade.getStockValue(), trade.getCommission(), trade.getDateOfPurchase());
		
		if (insertResult.next()) {
			tradeId = insertResult.getLong("trade_id");
		}
		
		return tradeId;
	}

	// Lists all the trades in the database - Kevin
	@Override
	public List<Trade> listAllTrades() {

		Trade theTrade = null;
		String sqlGetAllTrades = "SELECT * FROM trade";
		ArrayList<Trade> allTheTrades = new ArrayList<Trade>();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllTrades);
		while (results.next()) {
			theTrade = mapRowSetToTrade(results);
			allTheTrades.add(theTrade);
		}
		return allTheTrades;
	}
	
	// List of all trades for a given portfolio - Seth
	@Override
	public List<Trade> listAllTradesForAPortfolio(long portfolioId){
		Trade theTrade = null;
		ArrayList<Trade> allTrades = new ArrayList<Trade>();
		String sqlGetAllTrades = "SELECT * FROM trade WHERE portfolio_id=? ORDER BY date_of_purchase DESC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllTrades, portfolioId);
		while (results.next()) {
			theTrade = mapRowSetToTrade(results);
			allTrades.add(theTrade);
		}
		return allTrades;
		
	}

	
	//Return quantity of shares for a portfolio.
	@Override
	public List<Trade> getStockInPortfolio(long id) {

		ArrayList<Trade> allTheTrades = new ArrayList<Trade>();
		String sqlGetTradesPerPortfolio = "SELECT sum(quantity) as quantity, ticker from "
				+ "trade WHERE portfolio_id=? GROUP BY ticker HAVING sum(quantity)>0;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetTradesPerPortfolio, id);

		while (results.next()) {
			Trade theTrade = new Trade();
			theTrade.setTicker(results.getString("ticker"));
			theTrade.setQuantity(results.getInt("quantity"));
			allTheTrades.add(theTrade);
		}
		return allTheTrades;							
	}
	
	
	
	
	
	@Override
	public List<Trade> getTradesPerGame(long gameId) {
		
		//Trade theTrade = null;
		ArrayList<Trade> allTheTrades = new ArrayList<Trade>();
		String sqlGetTradesPerGame = "SELECT sum(quantity) as quantity, trade.ticker AS ticker, "
				+ "portfolio.portfolio_id as portfolio_id FROM trade " + 
				"INNER JOIN portfolio ON portfolio.portfolio_id = trade.portfolio_id "
				+ "WHERE game_id=? GROUP BY trade.ticker, portfolio.portfolio_id";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetTradesPerGame, gameId);

		while (results.next()) {
			Trade theTrade = new Trade();
			theTrade.setPortfolioId(results.getLong("portfolio_id"));
			theTrade.setTicker(results.getString("ticker"));
			theTrade.setQuantity(results.getInt("quantity"));
			allTheTrades.add(theTrade);
		}
		return allTheTrades;							
	}

	private Trade mapRowSetToTrade(SqlRowSet results) {
		Trade theTrade = new Trade();

		theTrade.setTradeId(results.getLong("trade_id"));
		theTrade.setPortfolioId(results.getLong("portfolio_id"));
		theTrade.setType(results.getString("type"));
		theTrade.setTicker(results.getString("ticker"));
		theTrade.setQuantity(results.getInt("quantity"));
		theTrade.setStockValue(new BigDecimal(results.getString("stock_value")));
		theTrade.setDateOfPurchase(LocalDate.parse(results.getString("date_of_purchase")));
		return theTrade;
		
	}


	public long getGameIdByPortfolioId(long portfolioId) {

		long gameId = 0;

		String sqlGetGameId = "SELECT game.game_id FROM game "
							+	"INNER JOIN portfolio "
							+	"ON  game.game_id = portfolio.game_id "
							+	"WHERE portfolio_id = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGameId, portfolioId);

		while (results.next()) {
				gameId = results.getLong("game_id");
		}
		return gameId;
	}

	@Override
	public void deleteAllTrades(long portfolioId) {
		String sqlDeleteTradesFromPortfolio = "DELETE FROM trade WHERE portfolio_id = ?";
		
		jdbcTemplate.update(sqlDeleteTradesFromPortfolio, portfolioId);
	}


	// this is similar to what I was trying to do with the portfolio pages
	// hoping we can work thru this later - Kevin
	// @Override
	// public List<Trade> listSpecificTrades() {
	// Trade theTrade = null;
	// String sqlGetAllTrades = "SELECT type, quantity, (quantity * stock_value),
	// date_of_purchase FROM trade " +
	// "INNER JOIN portfolio " +
	// "ON portfolio.portfolio_id = trade.portfolio_id " +
	// "INNER JOIN users " +
	// "ON users.user_id = portfolio.portfolio_id " +
	// "WHERE user = ? ";


	// ArrayList<Trade> allTheTrades = new ArrayList<Trade>();
	// SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllTrades);
	// while (results.next()) {
	// theTrade = mapRowSetToTrade(results);
	// allTheTrades.add(theTrade);
	// }

	// return allTheTrades;
	// }

}