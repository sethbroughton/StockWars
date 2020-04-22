package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TradeDao {

    public List<Trade> listAllTrades();

    public long createNewTrade(long tradeId, long portfolioId, String type,
                                 String ticker, int quantity, BigDecimal stockValue,
                                 BigDecimal commission, LocalDate dateOfPurchase);

}