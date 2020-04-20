package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Portfolio {

private long portfolioId;
private List<Trade> trades;
private Map<Stock, Integer> stocks;
BigDecimal totalValue;

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

public Map<Stock, Integer> getStocks() {
    return stocks;
}

public void setStocks(Map<Stock, Integer> stocks) {
    this.stocks = stocks;
}

public BigDecimal getTotalValue() {
    return totalValue;
}

public void setTotalValue(BigDecimal totalValue) {
    this.totalValue = totalValue;
}


}