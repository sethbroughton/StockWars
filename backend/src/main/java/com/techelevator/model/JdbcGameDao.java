package com.techelevator.model;

import java.io.InputStream;
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
    public void createGame(long organizerId, String name, int numberOfPlayers, int lengthInDays) {

        String sqlInsertNewGame = "INSERT INTO game "
                + "(organizer_id, name, number_of_players, length_in_days) "
                + " VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sqlInsertNewGame, organizerId, name, numberOfPlayers, lengthInDays);
    }

    //gets all games, hopefully -Kevin 
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

    //still need to fix the setPlayers section -Kevin 
    private Game mapRowSetToGame(SqlRowSet results) {
        Game theGame = new Game();

        theGame.setGameId(results.getLong("game_id"));
        theGame.setOrganizerId(results.getLong("organizer_id"));
        theGame.setWinnerId(results.getLong("winner_id"));
        theGame.setName(results.getString("name"));
        theGame.setNumberOfPlayers(results.getInt("number_of_players"));
        theGame.setLengthInDays(results.getInt("length_in_days"));
        theGame.setStartDate(results.getDate("start_date").toLocalDate());
        theGame.setEndDate(results.getDate("end_date").toLocalDate());
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