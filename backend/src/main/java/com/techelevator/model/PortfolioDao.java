package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioDao {

    public void createPortfolio(long userId, long gameId);
    public List<Portfolio> getBasicPortfolios();
    public Portfolio getCurrentPortfolio();
    public Portfolio getPortfolioById(long id);

}