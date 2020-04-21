package com.techelevator.model;
import java.util.List;

public interface GameDao {
	 public Game saveGame(String name);
	 public List<Game> getAllGames();
}
