//package com.techelevator.controller;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.techelevator.authentication.AuthProvider;
//import com.techelevator.model.Game;
//import com.techelevator.model.GameDao;
//
//


//@RestController
//@CrossOrigin
//
//@RequestMapping("/api/creategame")
//public class GameController {
//	
//	//Autowired for when gameDao is filled - Charles
//	//Made an empty GameDao class
//	@Autowired 
//	GameDao gameDao;
//	
//	@GetMapping
//	public List<Game> list() {
//		//Create method List() in GameDao - Charles
//		return gameDao.list();
//	}
//	
//	//For when the game is actually created - Charles
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Game create(@RequestBody Game game) {
//		game.setStartDate(LocalDate.now());
//		//Do we need to make a create() method in GameDao? - Charles
//		return gameDao.create(game);
//	}
//	
//	//This method will make sure the game's id is correct - Charles
//	//Validation: May need to create a GameNotFoundException - Charles
//	@GetMapping("/api/{id}")
//	public Game read(@PathVariable int gameId) throws GameNotFoundException {
//		Game game = GameDao.read(gameId);
//		if(game != null) {
//			return game;
//		}else {
//			throw new GameNotFoundException(gameId, "Game Not Found!");
//		}
//	}
//	
//	//These next few delete mapping and put mapping sections are for exception handling - Charles
//	@DeleteMapping("/api/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable int gameId) {
//		Game game = gameDao.read(gameId);
//		if(game != null ) {
//			gameDao.delete(gameId);
//		} else {
//			throw new GameNotFoundException(gameId, "Game Not Found!");
//		}
//	}
//	
//	@PutMapping("/api/{id}")
//	public Game update(@RequestBody Game game, @PathVariable int gameId) {
//		if(game.getGameId() != gameId) {
//			throw new GameNotFoundException(gameId, "Product Review Not Found!");
//		}
//		Game existingGame = gameDao.read(gameId);
//		if(existingGame == null) {
//			throw new GameNotFoundException(gameId, "Product Review Not Found!");
//		}
//		
//		// Map over any system set fields to prevent the user from 
//		// overwriting them
//		game.setCreatedAt(existingGame.getCreatedAt());
//		
//		return gameDao.update(game);
//		
//	}
//
//}
//}
