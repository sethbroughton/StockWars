package com.techelevator.model;
import java.util.List;

public interface GameDao {
	 public Game saveGame(String name);
	 public List<Game> list();
	 public Game create(Game game);
	 public Game read(int gameId);
	public Game update(Game game);
	}

