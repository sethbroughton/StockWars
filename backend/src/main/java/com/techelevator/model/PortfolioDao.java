package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public interface PortfolioDao {

    public void createPortfolio(long userId, long gameId);
    public List<Portfolio> getAllPortfolios();
    public Portfolio getPortfolioById(long id);
    public List<Portfolio> getPortfoliosByGameId(long id);
    public long getQuantityOfShares(String ticker, long portfolioId);
    public void setCashBalanceById(BigDecimal newBalance, long portfolioId); 
    //public Portfolio getUserSpecificPortfolio(User user, Game game);

}