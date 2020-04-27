package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.exceptions.GameNotFoundException;
import com.techelevator.model.Game;
import com.techelevator.model.GameDao;
import com.techelevator.model.Portfolio;
import com.techelevator.model.PortfolioDao;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private UserDao userDao;
    
	@Autowired
    private GameDao gameDao;

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private PortfolioDao portfolioDao;


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

    @GetMapping("/game/{id}")
    public Game read(@PathVariable long id) {
        Game game = gameDao.getGameById(id);
        return game;
    }

    @GetMapping("/activeGames")
    public List<Game> getActiveGames() {
        return gameDao.listActiveGames();
    }

    @GetMapping("/availableGames")
    public List<Game> getAvailableGames() {
        return gameDao.listAvailableGames();
    }

    @GetMapping("/pendingGames")
    public List<Game> getPendingGames() {
        return gameDao.listPendingGames();
    }    

    @GetMapping("/portfolios")
    public List<Portfolio> getAllPortfolios() {
        return portfolioDao.getAllPortfolios();
    }    

    @GetMapping("/portfolio/{id}")
    public Portfolio getPortfolio(@PathVariable long id) {
        Portfolio portfolio = portfolioDao.getPortfolioById(id);
        return portfolio;
    }
        
    @GetMapping("/tradeHistory")
	public List<Trade> getAllTrades() {
        return tradeDao.listAllTrades();
    }

	@PostMapping("/game")
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody Game game) {
        gameDao.createGame(game.getOrganizerId(), game.getOrganizerName(), game.getName(), game.getNumberOfPlayers(), game.getLengthInDays());
        return "{\"success\":true}";
    }

    @PostMapping("/joinGame")
    @ResponseStatus(HttpStatus.CREATED)
    public String joinGame(@RequestBody long gameId) {
        gameDao.joinGame(gameId);
        return "{\"success\":true}";
    }

    @PostMapping("/portfolios")
	@ResponseStatus(HttpStatus.CREATED)
	public String createPortfolios(@RequestBody Game game) {

        List<User> users = userDao.getUsersInGame(game);

        for (User user : users) {
            portfolioDao.createPortfolio(user.getId(), game.getGameId());
        }

        return "{\"success\":true}";
    }

    //starting a new game, updating it with start and end dates
    @PutMapping("/game/{id}")
    public String startGame(@RequestBody Game game, @PathVariable int id) {
        if (game.getGameId() != id) {
            throw new GameNotFoundException(id, "Game not found!!!!");
        }
        
        Game existingGame = gameDao.getGameById(id);
        if(existingGame == null) {
            throw new GameNotFoundException(id, "Game not found!!!!");
        }
    
        LocalDate now = LocalDate.now();  

        gameDao.startGame(now, now.plusDays(game.getLengthInDays()), id);
        return "{\"success\":true}";      
    }
    
    @PostMapping("/trade")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTrade(@RequestBody Trade trade) {
    	trade.setDateOfPurchase((LocalDate.now()));
    	tradeDao.createNewTrade(trade);
    	return "{\"success\":true}";
    }
    
    @GetMapping("/trades/{id}")
    public List<Trade> getAllTradesInPortfolio(@PathVariable long id) {
        return tradeDao.getTradesPerPortfolio(id);
    }
    //for the return to game button
    @GetMapping("/game/myGame/{id}")
    public long getGameWithPortfolioId(@PathVariable long id) {
        return gameDao.getGameWithPortfolioId(id);
    }

}