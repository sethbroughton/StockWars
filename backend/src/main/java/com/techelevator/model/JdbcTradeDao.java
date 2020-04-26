package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.authentication.AuthProvider;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class JdbcTradeDao implements TradeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuthProvider auth;

	@Autowired
	public JdbcTradeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// inserts a new trade into the database -Kevin

	@Override
	public void createNewTrade(Trade trade) {
		BigDecimal accountBalance = null;
		
		//Get current CASH balance from portfolio table
//		String sqlGetCurrentCashBalance = "SELECT cash from portfolio where portfolio_id=?";
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCurrentCashBalance, trade.getPortfolioId());
//		accountBalance = new BigDecimal(results.getString("cash"));
//
//		
//		//BUY (subtract money) or SELL (add money)
//		BigDecimal newBalance = null;
//		if(trade.getType().equals("BUY")) {
//			newBalance = accountBalance.subtract(trade.getStockValue());
//		} else {
//			newBalance = accountBalance.add(trade.getStockValue());
//		}
//		
//		//Updated current CASH balance in portfolio table
//		String sqlUpdateAccountBalance = "UPDATE portfolio SET total_value = ? WHERE porfolio_id = ?";
//		jdbcTemplate.update(sqlUpdateAccountBalance, newBalance, trade.getPortfolioId());
//		
		
		//Create trade in trade table
		String sqlInsertNewTrade = "INSERT INTO trade (portfolio_id, type, ticker, "
				+ "quantity, stock_value, commission, date_of_purchase) VALUES (?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sqlInsertNewTrade, trade.getPortfolioId(), trade.getType(), trade.getTicker(),
				trade.getQuantity(), trade.getStockValue(), trade.getCommission(), trade.getDateOfPurchase());
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