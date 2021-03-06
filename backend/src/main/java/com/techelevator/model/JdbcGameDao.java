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
	private UserDao userDao;

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
                                    + "(organizer_id, name, number_of_players, length_in_days) "
                                    + " VALUES (?, ?, ?, ?) RETURNING game_id";
        
        String sqlAddCurrentUserToNewGame = "INSERT INTO users_game "
                                            + "(user_id, game_id, invite_accepted) "
                                            + "VALUES (?, ?, ?)";

        long gameId = jdbcTemplate.queryForObject(sqlInsertNewGame, Long.class, organizerId, name, numberOfPlayers, lengthInDays);

        jdbcTemplate.update(sqlAddCurrentUserToNewGame, organizerId, gameId, true);
    }
    
    @Override
    public Game getGameById(long id) {
        Game theGame = null;

        String sqlGetGameById = "SELECT * FROM game WHERE game.game_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetGameById, id);
        if (results.next()) {
            theGame = mapRowSetToGame(results);
        }
        
        //Adds a list of players to the game - SB
        List<User> players = userDao.getUsersInGame(theGame);
        theGame.setPlayers(players);

        return theGame;
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
                                        + "WHERE game.start_date IS NULL AND users_game.game_id NOT IN "
                                            + "(SELECT game_id "
                                            + "FROM users_game "
                                            + "WHERE user_id = ?) "
                                        + "ORDER BY game.game_id";

        String sqlGetOrganizerName = "SELECT users.username AS organizer_name " 
                                        + " FROM game "
                                        + " INNER JOIN users ON (game.organizer_id = users.id) "
                                        + " WHERE game_id = ? ";   
                                        
        List<Game> availableGames = new ArrayList<Game>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAvailableGames, userId);
        while (results.next()) {
            theGame = mapRowSetToGame(results);
            List<User> theUsers = userDao.getUsersInGame(theGame);
            theGame.setPlayers(theUsers);
            availableGames.add(theGame);

        }

        for (Game game : availableGames) {
            SqlRowSet organizerNameResults = jdbcTemplate.queryForRowSet(sqlGetOrganizerName, game.getGameId());
            while (organizerNameResults.next()) {
                game.setOrganizerName(organizerNameResults.getString("organizer_name"));
            }
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
                                        "WHERE users.id = ? AND game.start_date IS NOT NULL AND winner_id IS NULL " +
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
        List<Game> pendingGames = new ArrayList<Game>();
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();

        String sqlGetPendingGames = "SELECT game.*, users.username AS organizer_name " +
                                        "FROM game " +
                                        "INNER JOIN users_game ON (game.game_id = users_game.game_id) " +
                                        "INNER JOIN users ON (users_game.user_id = users.id) " +
                                        "WHERE users.id = ? AND game.start_date IS NULL";   
                                        
        String sqlGetOrganizerName = "SELECT users.username AS organizer_name " 
                                        + " FROM game "
                                        + " INNER JOIN users ON (game.organizer_id = users.id) "
                                        + " WHERE game_id = ? ";         
        
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPendingGames, userId);
        while (results.next()) {  
            Game theGame = mapRowSetToGame(results);
            List<User> theUsers =userDao.getUsersInGame(theGame);
            theGame.setPlayers(theUsers);
            pendingGames.add(theGame);
        }

        for (Game game : pendingGames) {
            SqlRowSet organizerNameResults = jdbcTemplate.queryForRowSet(sqlGetOrganizerName, game.getGameId());
            while (organizerNameResults.next()) {
                game.setOrganizerName(organizerNameResults.getString("organizer_name"));
            }
        }
        

        return pendingGames;
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

    @Override
    public void startGame(LocalDate start_date, LocalDate end_date, int id) {
            String sqlStartGame = "UPDATE game SET start_date = ?, end_date = ? " +
                                   "WHERE game.game_id = ?";

            jdbcTemplate.update(sqlStartGame, start_date, end_date, id);
            
    }

    @Override
    public List<Game> getAllInvites() {
        List<Game> games = new ArrayList<Game>();
        
        User currentUser = auth.getCurrentUser();
        long userId = currentUser.getId();
        
        String sqlGetInvites = "SELECT game.* FROM users_game "
                                + "JOIN game ON (users_game.game_id = game.game_id) "
                                + "JOIN users ON (users_game.user_id = users.id) "
                                + "WHERE user_id = ? AND invite_accepted = false";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetInvites, userId);

        while(results.next()) {
            Game theGame = mapRowSetToGame(results);
            games.add(theGame);
        }

        return games;
    }

    @Override
	public void acceptInvite(long userId, long gameId) {
    	
		String sqlAcceptInvite = "UPDATE users_game SET invite_accepted = true WHERE game_id = ? AND user_id = ?";
		jdbcTemplate.update(sqlAcceptInvite, gameId, userId);
    }
    
    //Obstacle : We don't want to simply DELETE FROM users_game WHERE invite_accepted = false
    //Reason : Invite_accepted = false may simply mean the invite has not been accepted OR rejected - pending
    //Objective: How do we make a query to reject an invite, without deleting pending invites?
    @Override
	public void rejectInvite(long userId, long gameId) {
    	//Current user using authenticator
    	//Relevant invite for game id 
		String sqlRejectInvite = "DELETE FROM users_game WHERE user_id = ? AND game_id = ?";
		jdbcTemplate.update(sqlRejectInvite, userId, gameId);
    }
    
    @Override
    public boolean doesInviteExist(long userId, long gameId) {
    	String sqlQueryForInvite = "SELECT * FROM users_game WHERE user_id = ? AND game_id = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryForInvite, userId, gameId);
    	if(results.next()) {
    		return true;
    	} else {
    		return false;
    	}
    }
       
    
    @Override
    public void setWinner(long winnerId, long gameId){
            String sqlUpdateWinner = "UPDATE game SET winner_id = ? WHERE game_id = ?";

            jdbcTemplate.update(sqlUpdateWinner, winnerId, gameId);
    }

    
    @Override
    public boolean testForEndgame(LocalDate today, long gameId) {
        LocalDate endDate = null;
        
        String sqlGameEndDate = "SELECT end_date FROM game WHERE game_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGameEndDate, gameId);
        if(results.next()) {
            String endDateString = results.getString("end_date");
            endDate = LocalDate.parse(endDateString);
        }

        if (today.compareTo(endDate) >= 0) {
            return true;
        } else {
            return false;
        }

    }

    private Game mapRowSetToGame(SqlRowSet results) {
        Game theGame = new Game();

        theGame.setGameId(results.getLong("game_id"));
        theGame.setOrganizerId(results.getLong("organizer_id"));
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