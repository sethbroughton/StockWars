package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.Game;
import com.techelevator.model.GameDao;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AuthProvider authProvider;
    
	@Autowired
    private GameDao gameDao;

    @Autowired
    private TradeDao tradeDao;

    @GetMapping("/")
    public String authorizedOnly() throws UnauthorizedException {
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.
        
        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if (!authProvider.userHasRole(new String[] { "admin" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
	
	@GetMapping("/games")
	public List<Game> getAllGames() {
        return gameDao.listAllGames();
    }

    @GetMapping("/activeGames")
    public List<Game> getActiveGames() {
        return gameDao.listActiveGames();
    }
    
	@PostMapping("/game")
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody Game game) {
        gameDao.createGame(game.getOrganizerId(), game.getName(), game.getNumberOfPlayers(), game.getLengthInDays());
        return "{\"success\":true}";
    }
    
    @GetMapping("/tradeHistory")
	public List<Trade> getAllTrades() {
        return tradeDao.listAllTrades();
    }

}