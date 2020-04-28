package com.techelevator.model;

import java.util.List;

public interface TradeDao {

    public List<Trade> listAllTrades();
    public Long createNewTrade(Trade trade);
    public List<Trade> getTradesPerPortfolio(long id);
	public List<Trade> getTradesPerGame(long gameId);
}