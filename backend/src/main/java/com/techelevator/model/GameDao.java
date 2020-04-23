package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface GameDao {

    public List<Game> listAllGames();

    public long createGame(long organizer_id, String name, int number_of_players, int length_in_days);

}