package com.techelevator.model.portfolio;

import java.util.List;

public interface PortfolioDao {

    public Portfolio getActivePortfolio(long id);

    public List<Portfolio> listAllPortfolios();

}