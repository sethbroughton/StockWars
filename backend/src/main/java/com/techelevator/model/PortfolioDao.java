package com.techelevator.model;

import java.util.List;

public interface PortfolioDao {

    public void createPortfolio(long userId, long gameId);
    public List<Portfolio> getAllPortfolios();
    public Portfolio getCurrentPortfolio();
    public Portfolio getPortfolioById(long id);

}