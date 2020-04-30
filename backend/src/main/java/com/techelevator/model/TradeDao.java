package com.techelevator.model;

import java.util.List;

public interface TradeDao {

    public List<Trade> listAllTrades();
    public Long createNewTrade(Trade trade);
    public List<Trade> getStockInPortfolio(long id);
    public List<Trade> listAllTradesForAPortfolio(long portfolioId);
	public List<Trade> getTradesPerGame(long gameId);
    public long getGameIdByPortfolioId(long portfolioId);  
    public void deleteAllTrades(long portfolioId);            
}