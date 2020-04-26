package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.authentication.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcPortfolioDao implements PortfolioDao {

    private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuthProvider auth;

    @Autowired
    public JdbcPortfolioDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // @Override
    // public Portfolio getActiveUserPortfolio(User user, Game game) {

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
                                        + "(user_id, game_id, total_value) "
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

    // @Override
    // public Game getGameById(long id) {
    //     Game theGame = null;

    //     String sqlGetGameById = "SELECT * FROM game WHERE game.game_id = ?";
    //     SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGameById, id);
    //     while (results.next()) {
    //         theGame = mapRowSetToGame(results);
    //     }

    //     return theGame;
    // }
                        
    private Portfolio mapRowToPortfolio(SqlRowSet results) {
    Portfolio portfolio = new Portfolio();
    portfolio.setGameId(results.getLong("game_id"));
    portfolio.setUserId(results.getLong("user_id"));
    portfolio.setPortfolioId(results.getLong("portfolio_id"));
    portfolio.setTotalValue(new BigDecimal(results.getString("total_value")));
                    
                    
        return portfolio;
  }

}