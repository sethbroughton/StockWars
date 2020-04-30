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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /////////////////////////////////////////////////////
    ////////////////////     USERS     /////////////////
    /////////////////////////////////////////////////////

    @GetMapping("/leaderboard")
    public Map<String, Integer> leaderboard() {
        return userDao.getLeaderboard();
    }
    
    /////////////////////////////////////////////////////
    ////////////////////     GAMES     /////////////////
    /////////////////////////////////////////////////////

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

    @GetMapping("/invites")
    public List<Game> getInvites() {
        return gameDao.getAllInvites();
    }

    @GetMapping("/gameByPortfolio/{portfolioId}")
    public long getGameByPortfolioId(@PathVariable long portfolioId) {
        return tradeDao.getGameIdByPortfolioId(portfolioId);
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
    
    @PostMapping("/invite/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String invitePlayer(/*User user*/ @RequestBody String username, @PathVariable long gameId) {
    	//-Charles
    	
    	/*String existingInvite = gameDao.getAllInvites();
		//Need variable for an existing invite
    	List<Game> existingInvite = gameDao.getAllInvites(id);
    	//If the user is already invited
    	if(existingInvite.contains(gameId)) {
    	//Do not send an invite
    		throw new GameNotFoundException(gameId, "Player already invited to this game!!!");
    	}
    	//Otherwise, send an invite*/
    	
    	username = username.replaceAll("^\"|\"$", "");
    	
        User user = userDao.getUserByUsername(username);
        userDao.inviteUser(user, gameId);
        return "{\"success\":true}";
    }

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

       
     @PutMapping("/invite/{userId}/{gameId}")
     public String acceptInvite(@PathVariable long userId , @PathVariable long gameId) {
    	 gameDao.acceptInvite(userId, gameId);
    	 return "{\"success\":true}";
     }

     @PutMapping("/setWinner/{gameId}/{winnerId}")
     public String setWinner(@PathVariable long gameId, @PathVariable long winnerId) {
         gameDao.setWinner(gameId, winnerId);
    	 return "{\"success\":true}";
     }
     
     @DeleteMapping("/rejectInvite/{userId}/{gameId}")
     public String rejectInvite(@PathVariable long userId , @PathVariable long gameId) {
    	 gameDao.rejectInvite(userId, gameId);
    	 return "{\"success\":true}";
     }

    /////////////////////////////////////////////////////
    ////////////////////     PORTFOLIOS     /////////////////
    /////////////////////////////////////////////////////

    @GetMapping("/portfolios")
    public List<Portfolio> getAllPortfolios() {
        return portfolioDao.getAllPortfolios();
    }    

    @GetMapping("/portfolio/{id}")
    public Portfolio getPortfolio(@PathVariable long id) {
        Portfolio portfolio = portfolioDao.getPortfolioById(id);
        return portfolio;
    }

    @GetMapping("/portfoliosInGame/{gameId}")
    public List<Portfolio> getAllPortfoliosInGame(@PathVariable long gameId) {
        List<Portfolio> portfolios = portfolioDao.getPortfoliosByGameId(gameId);
        return portfolios;
    }

    @GetMapping("/stockShares/{portfolioId}/{ticker}")
    public long getQuantityOfShares(@PathVariable String ticker, @PathVariable long portfolioId) {
        long sharesQuantity = portfolioDao.getQuantityOfShares(ticker, portfolioId);
        return sharesQuantity;
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
        
    /////////////////////////////////////////////////////
    ////////////////////     TRADES     /////////////////
    /////////////////////////////////////////////////////

    @GetMapping("/tradeHistory")
	public List<Trade> getAllTrades() {
        return tradeDao.listAllTrades();
    }
    
    @PostMapping("/trade")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTrade(@RequestBody Trade trade) throws UnauthorizedException {
    	trade.setDateOfPurchase((LocalDate.now()));
    	Long tradeId = tradeDao.createNewTrade(trade);
    	if(tradeId!=null) {
    		return tradeId;
    	} else {
    		throw new UnauthorizedException();
    	}	
    }
    
    @GetMapping("/tradeHistory/{portfolioId}")
    public List<Trade> getAllTradesInPortfolio(@PathVariable long portfolioId){
    	return tradeDao.listAllTradesForAPortfolio(portfolioId);
    }
    
    
    @GetMapping("/trades/{id}")
    public List<Trade> getAllStocksInPortfolio(@PathVariable long id) {
        return tradeDao.getStockInPortfolio(id);
    }   
    
    
    @GetMapping("/trades/game/{gameId}")
    public List<Trade> getAllTradesInGame(@PathVariable long gameId) {
        return tradeDao.getTradesPerGame(gameId);
    }  

    @PutMapping("/balance/{newBalance}/{portfolioId}")
    public String updateBalance(@PathVariable BigDecimal newBalance , @PathVariable long portfolioId) {
        portfolioDao.setCashBalanceById(newBalance, portfolioId);;
        return "{\"success\":true}";
    }

    @DeleteMapping("/deleteTrades/{profileId}")
    public String deleteTrades(@PathVariable long profileId) {
        tradeDao.deleteAllTrades(profileId);
        return "{\"success\":true}";
    }    
    
}