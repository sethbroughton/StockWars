package com.techelevator.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.Game;
import com.techelevator.model.JdbcGameDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoGameIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JdbcGameDao gameDao;
	
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/stockwars");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String sqlInsertNewGame = "INSERT INTO game (organizer_id, name, "
				+ "winner_id, number_of_players, length_in_days, start_date, end_date, public_game) "
				+ "VALUES (1, 'Austin', 1, 3, 30, '1990-03-07', '1990-03-12', true)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertNewGame);

	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void listAllGames() throws SQLException {
		Long id = 1l;
		List<Game> games = gameDao.listAllGames();
		Assert.assertNotNull(games);
	}

}
