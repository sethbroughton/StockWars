package com.techelevator.model.portfolio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcPortfolioDao implements PortfolioDao {

    private JdbcTemplate jdbcTemplate;

    // @Override
    // public Portfolio getActiveUserPortfolio(User user) {

    //     Portfolio portfolio = null;
    //     ArrayList<Portfolio> portfolios = new ArrayList<Portfolio>();
    //     String sqlGetPortfolio = "SELECT type, stock_id, quantity, stock_value, total_value, date_of_purchase " +
    //                             "FROM portfolio " +
    //                             "INNER JOIN users " +
    //                             "ON portfolio.user_id = users.user_id" +
    //                             "INNER JOIN trade " +
    //                             "ON trade.portfolio_id = portfolio.portfolio_id " +
    //                             "WHERE user = ? ";

    //     SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPortfolio, user);
    //     while (results.next()) {

    //     }
        
    //     return null;
    // }


    //I made a more specific portfolios query above, this one is just to get the 
    //information out of the individual Portfolio table -Kevin
    @Override
    public List<Portfolio> getBasicPortfolios() {
        Portfolio thePortfolio = null;
        String sqlGetAllPortfolios = "SELECT * FROM portfolio";
        ArrayList<Portfolio> allThePortfolios = new ArrayList<Portfolio>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllPortfolios);
        while (results.next()) {
            thePortfolio = mapRowToPortfolio(results);
            allThePortfolios.add(thePortfolio);
        }

        return allThePortfolios;
    }
                        
    private Portfolio mapRowToPortfolio(SqlRowSet results) {
    Portfolio portfolio = new Portfolio();
    portfolio.setGameId(results.getLong("game_id"));
    portfolio.setUserId(results.getLong("user_id"));
    portfolio.setPortfolioId(results.getLong("portfolioId"));
    //not sure if we need trades here? -Kevin 
    portfolio.setTotalValue(new BigDecimal(results.getString("total_value")));
                    
                    
        return portfolio;
  }

}