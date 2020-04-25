package com.techelevator.model;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.bouncycastle.util.test.Test;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.authentication.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import io.jsonwebtoken.io.IOException;

@Component
public class JdbcGameDao implements GameDao {

    private JdbcTemplate jdbcTemplate;

	@Autowired
	private AuthProvider auth;

    @Autowired
    public JdbcGameDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
	private Map<Long, Game> games = new HashMap<>();
    public void GameDao() throws IOException, java.io.IOException {
		loadJSON();
	}	    

    @Override
    public void createGame(long organizerId, String organizerName, String name, int numberOfPlayers, int lengthInDays) {
    	
    	
        String sqlInsertNewGame = "INSERT INTO game "
                                    + "(organizer_id, organizer_name, name, number_of_players, length_in_days) "
                                    + " VALUES (?, ?, ?, ?) RETURNING game_id";
        
        String sqlAddCurrentUserToNewGame = "INSERT INTO users_game "
                                            + "(user_id, game_id, invite_accepted) "
                                            + "VALUES (?, ?, ?)";

        long gameId = jdbcTemplate.queryForObject(sqlInsertNewGame, Long.class, organizerId, name, numberOfPlayers, lengthInDays);

        jdbcTemplate.update(sqlAddCurrentUserToNewGame, organizerId, gameId, true);
    }

    @Override 
    public List<Game> listAllGames() {
        Game theGame = null;
        String sqlGetAllGames = "SELECT * from game";
        List<Game> allGames = new ArrayList<Game>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllGames);
        while (results.next()) {
            theGame = mapRowSetToGame(results);
            allGames.add(theGame);
        }
        return allGames;
    }

    @Override
    public List<Game> listAvailableGames() {
        
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();

        Game theGame = null;
        String sqlGetAvailableGames = "SELECT DISTINCT users_game.game_id, game.* "
                                        + "FROM users_game "
                                        + "LEFT OUTER JOIN game ON (users_game.game_id = game.game_id) "
                                        + "WHERE users_game.game_id NOT IN "
                                            + "(SELECT game_id "
                                            + "FROM users_game "
                                            + "WHERE user_id = ?) "
                                        + "ORDER BY game.game_id";
        List<Game> availableGames = new ArrayList<Game>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAvailableGames, userId);
        while (results.next()) {
            theGame = mapRowSetToGame(results);
            availableGames.add(theGame);
        }
        return availableGames;
    }

    @Override
    public List<Game> listActiveGames() {
        
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();

        Game theGame = null;
        String sqlGetActiveGames = "SELECT game.* " +
                                        "FROM game " +
                                        "INNER JOIN users_game ON (game.game_id = users_game.game_id) " +
                                        "INNER JOIN users ON (users_game.user_id = users.id) " +
                                        "WHERE users.id = ? AND game.start_date IS NOT NULL " +
                                        "GROUP BY game.game_id";
        List<Game> activeGames = new ArrayList<Game>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetActiveGames, userId);
        while (results.next()) {
            theGame = mapRowSetToGame(results);
            activeGames.add(theGame);
        }
        return activeGames;
    }

    @Override
    public List<Game> listPendingGames() {
        
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();

        Game theGame = null;
        String sqlGetActiveGames = "SELECT game.*, users.username " +
                                        "FROM game " +
                                        "INNER JOIN users_game ON (game.game_id = users_game.game_id) " +
                                        "INNER JOIN users ON (users_game.user_id = users.id) " +
                                        "WHERE users.id = ? AND game.start_date IS NULL AND users.id = game.organizer_id" +
                                        "GROUP BY game.game_id ";        
        List<Game> activeGames = new ArrayList<Game>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetActiveGames, userId);
        while (results.next()) {
            theGame = mapRowSetToGame(results);
            activeGames.add(theGame);
        }
        return activeGames;
    }

    @Override
    public void joinGame(long gameId) {
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();

        String sqlAddPlayerToGame = "INSERT INTO users_game "
                                    + "(user_id, game_id, invite_accepted) "
                                    + "VALUES (?, ?, true)";
        jdbcTemplate.update(sqlAddPlayerToGame, userId, gameId);
    }

    //still need to fix the setPlayers section -Kevin 
    private Game mapRowSetToGame(SqlRowSet results) {
        Game theGame = new Game();

        theGame.setGameId(results.getLong("game_id"));
        theGame.setOrganizerId(results.getLong("organizer_id"));
        theGame.setOrganizerName(results.getString("organizer_name"));
        theGame.setWinnerId(results.getLong("winner_id"));
        theGame.setName(results.getString("name"));
        theGame.setNumberOfPlayers(results.getInt("number_of_players"));
        theGame.setLengthInDays(results.getInt("length_in_days"));
 
        String startDate = results.getString("start_date");
		if (startDate != null) {
			theGame.setStartDate(LocalDate.parse(startDate));
		}
        String endDate = results.getString("end_date");
        if(endDate!=null) {
        	theGame.setEndDate(LocalDate.parse(endDate));
        }
    
        theGame.setPublicGame(results.getBoolean("public_game"));

        return theGame;

    }
    
    private void loadJSON() throws IOException, java.io.IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = Test.class.getResourceAsStream("/data.json");
		Game[] jsondata = mapper.readValue(inputStream, Game[].class);
		for (Game game : jsondata) {
			games.put(game.getGameId(), game);
		}
	}
}