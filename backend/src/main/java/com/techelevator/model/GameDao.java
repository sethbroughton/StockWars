package com.techelevator.model;

import java.util.List;

public interface GameDao {

    public List<Game> listAllGames();
    public List<Game> listAvailableGames();
    public void createGame(long organizerId, String name, int numberOfPlayers, int lengthInDays);

}