package edu.hw3.task6;

import java.util.Collections;
import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {

    private final PriorityQueue<Stock> stocks = new PriorityQueue<>(Collections.reverseOrder());

    @Override
    public void add(Stock stock) {
        this.stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        this.stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return this.stocks.peek();
    }
}
