package com.techelevator.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.techelevator.authentication.PasswordHasher;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new user dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for users.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @param role the user's role
     * @return the new user
     */
    @Override
    public User saveUser(String userName, String password, String role) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO users(username, password, salt, role) VALUES (?, ?, ?, ?) RETURNING id", Long.class,
                userName, hashedPassword, saltString, role);

        User newUser = new User();
        newUser.setId(newId);
        newUser.setUsername(userName);
        newUser.setRole(role);

        return newUser;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));

        jdbcTemplate.update("UPDATE users SET password=?, salt=? WHERE id=?", hashedPassword, saltString, user.getId());
    }

    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    @Override
    public User getValidUserWithPassword(String userName, String password) {
        String sqlSearchForUser = "SELECT * FROM users WHERE UPPER(username) = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
        if (results.next()) {
            String storedSalt = results.getString("salt");
            String storedPassword = results.getString("password");
            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
            if (storedPassword.equals(hashedPassword)) {
                return mapResultToUser(results);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Get all of the users from the database.
     * @return a List of user objects
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sqlSelectAllUsers = "SELECT id, username, role FROM users";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

        while (results.next()) {
            User user = mapResultToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public List<User> getUsersInGame(Game game) {
        List<User> users = new ArrayList<User>();
        String sqlSelectAllUsersInGame = "SELECT users.* "
                                            + "FROM users "
                                            + "JOIN users_game ON (users.id = users_game.user_id) "
                                            + "JOIN game ON (users_game.game_id = game.game_id) "
                                            + "WHERE game.game_id = ?";
        
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsersInGame, game.getGameId());
        
        while(results.next()) {
            User theUser = mapResultToUser(results);
            users.add(theUser);
        }

        return users;
    }

    private User mapResultToUser(SqlRowSet results) {
        User user = new User();
        user.setId(results.getLong("id"));
        user.setUsername(results.getString("username"));
        user.setRole(results.getString("role"));
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        String sqlSelectUserByUsername = "SELECT id, username, role FROM users WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByUsername, username);

        if (results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
    }
    
    @Override
    public void inviteUser(User user, Long gameId) {
        String username = user.getUsername();

        String sqlFindUserIdByName = "SELECT * FROM users WHERE username = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindUserIdByName, username);
    	long userId = 0;
    	while (results.next()) {
    		userId = results.getLong("id");
    	}
    	String sqlAddInvite = "INSERT INTO users_game (user_id, game_id, invite_accepted) VALUES (?, ?, false)";
    	jdbcTemplate.update(sqlAddInvite, userId, gameId);
    }

    @Override
    public Map<String, Integer> getLeaderboard() {
        Map<String, Integer> leaderboard = new LinkedHashMap<String, Integer>();

        String sqlGetLeaderboard = "SELECT users.username, COUNT(game.winner_id) AS wins "
                                    + "FROM users "
                                    + "JOIN users_game ON (users.id = users_game.user_id) "
                                    + "JOIN game ON (users_game.game_id = game.game_id) "
                                    + "WHERE game.winner_id = users.id "
                                    + "GROUP BY users.username "
                                    + "ORDER BY wins DESC ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetLeaderboard);
        while (results.next()) {
            String userName = results.getString("username");
            int wins = results.getInt("wins");
            leaderboard.put(userName, wins);
        }

        return leaderboard;
    }

    @Override
    public User getUserById(long userId) {

        String sqlGetUserById = "SELECT * FROM users WHERE id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetUserById, userId);

        if (results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
    }

}
