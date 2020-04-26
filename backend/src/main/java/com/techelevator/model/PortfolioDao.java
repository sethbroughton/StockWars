package com.techelevator.model;

import java.util.List;

public interface PortfolioDao {

    public List<Portfolio> getBasicPortfolios();
    public Portfolio getCurrentPortfolio();
    public Portfolio getPortfolioById(long id);

}