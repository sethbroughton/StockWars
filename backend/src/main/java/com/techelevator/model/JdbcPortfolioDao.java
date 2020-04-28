package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcPortfolioDao implements PortfolioDao {

    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TradeDao tradeDao;

    @Autowired
    public JdbcPortfolioDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //Played around with this but I didn't want to screw anything up in the controller, 
    //I figured we could discuss tomorrow - Kevin 
    // @Override
    // public Portfolio getUserSpecificPortfolio(User user, Game game) {

    //     Portfolio portfolio = null;
      
    //     String sqlGetPortfolio = "SELECT portfolio.* " +
    //                             "FROM portfolio " +
    //                             "INNER JOIN users " +
    //                             "ON portfolio.user_id = users.id" +
    //                             "INNER JOIN trade " +
    //                             "ON trade.portfolio_id = portfolio.portfolio_id " +
    //                             "WHERE user = ? AND game_id = ?";

    //     SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPortfolio, user, game);
    //     while (results.next()) {
    //             portfolio = mapRowToPortfolio(results);
             
    //     }
        
    //     return portfolio;
    // }

    @Override
    public List<Portfolio> getAllPortfolios() {
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

    @Override
    public void createPortfolio(long userId, long gameId) {
        String sqlInsertNewPortfolio = "INSERT INTO portfolio "
                                        + "(user_id, game_id, cash) "
                                        + "VALUES (?, ?, 100000)";

        jdbcTemplate.update(sqlInsertNewPortfolio, userId, gameId);
    }

    @Override
    public Portfolio getPortfolioById(long id) {
        Portfolio thePortfolio = null;

        String sqlGetPortfolioById = "SELECT * FROM portfolio WHERE portfolio.portfolio_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPortfolioById, id);
        while (results.next()) {
            thePortfolio = mapRowToPortfolio(results);
        }

        return thePortfolio;
    }
    
    @Override
    public List<Portfolio> getPortfoliosByGameId(long gameId) {
        List<Portfolio> portfolios = new ArrayList<Portfolio>();  
        
        String sqlGetAllPortfoliosInGame = "SELECT portfolio.* "
                                            + "FROM portfolio "
                                            + "INNER JOIN game ON (portfolio.game_id = game.game_id) "
                                            + "WHERE game.game_id = ?";
                                            
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllPortfoliosInGame, gameId);
        
        while (results.next()) {
            Portfolio thePortfolio = mapRowToPortfolio(results);
            List<Trade> tradesForPortfolio = tradeDao.getTradesPerPortfolio(results.getLong("portfolio_id"));
            thePortfolio.setTrades(tradesForPortfolio);
            portfolios.add(thePortfolio);
        }

        return portfolios;
    }
    
	@Override
	public long getQuantityOfShares(String ticker, long portfolioId) {
		String sqlGetSharesCount = "SELECT SUM(quantity) as total_shares FROM trade WHERE ticker = ? AND portfolio_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSharesCount, ticker, portfolioId);

        long totalShares = 0;
        while (results.next()) {
            totalShares = results.getLong("total_shares");
        }
		
		return totalShares;
	}   
                        
    private Portfolio mapRowToPortfolio(SqlRowSet results) {
    Portfolio portfolio = new Portfolio();
    portfolio.setGameId(results.getLong("game_id"));
    portfolio.setUserId(results.getLong("user_id"));
    portfolio.setPortfolioId(results.getLong("portfolio_id"));
    portfolio.setCash(new BigDecimal(results.getString("cash")));
                    
                    
        return portfolio;
  }


}