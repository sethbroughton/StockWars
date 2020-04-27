package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface GameDao {

    public List<Game> listAllGames();
    public Game getGameById(long id);
    public List<Game> listAvailableGames();
    public List<Game> listActiveGames();
    public List<Game> listPendingGames();
    public void joinGame(long gameId);
    public void createGame(long organizerId, String organizerName, String name, int numberOfPlayers, int lengthInDays);
    public void startGame(LocalDate start_date, LocalDate end_date, int id);
    public long getGameWithPortfolioId(long id);
  
}