package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TradeDao {

    public List<Trade> listAllTrades();
    public void createNewTrade(Trade trade);
    public List<Trade> getTradesPerPortfolio(long id);

    // public List<Trade> listSpecificTrades();                      

}