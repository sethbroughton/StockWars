package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTradeDao implements TradeDao {

    private JdbcTemplate jdbcTemplate;

    //inserts a new trade into the database -Kevin 
    @Override
    public long createNewTrade(long tradeId, long portfolioId, String type, String ticker, int quantity, BigDecimal stockValue, BigDecimal commission, LocalDate dateOfPurchase) {

        String sqlInsertNewTrade = "INSERT INTO trade " +
                                    "(portfolio_id, stock_id, type, quantity, date_of_purchase, stock_value, commission) " +
                                    " VALUES (?, ?, ?, ?, ?, ?, ?,) RETURNING game_id ";
        
       long gameId = jdbcTemplate.update(sqlInsertNewTrade, tradeId, portfolioId, type, ticker, quantity, stockValue, commission, dateOfPurchase);                             
        return gameId;
    }




    //Lists all the trades in the database - Kevin 
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
            theTrade.setStockValue(new BigDecimal(results.getString("stockValue")));
            theTrade.setCommission(new BigDecimal(results.getString("commission")));
            theTrade.setDateOfPurchase(LocalDate.parse(results.getString("trade_id")));

            return theTrade;

        }
    
}