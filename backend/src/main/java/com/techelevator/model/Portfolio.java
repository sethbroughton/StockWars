package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Portfolio {
    
private long userId;
private long gameId;
private long portfolioId;
private List<Trade> trades;
private BigDecimal cash;


public long getPortfolioId() {
    return portfolioId;
}

public void setPortfolioId(long portfolioId) {
    this.portfolioId = portfolioId;
}

public List<Trade> getTrades() {
    return trades;
}

public void setTrades(List<Trade> trades) {
    this.trades = trades;
}


public BigDecimal geCash() {
    return cash;
}

public void setCash(BigDecimal cash) {
    this.cash = cash;
}

public long getUserId() {
    return userId;
}

public void setUserId(long userId) {
    this.userId = userId;
}

public long getGameId() {
    return gameId;
}

public void setGameId(long gameId) {
    this.gameId = gameId;
}


}