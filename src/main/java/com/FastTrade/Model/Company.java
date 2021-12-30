package com.FastTrade.Model;

import org.springframework.stereotype.Component;

@Component
public class Company
{
    private String symbol;
    private int price;
    private String time;

    public Company(String symbol, int price, String time) {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    public Company() {
    }

    @Override
    public String toString() {
        return
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", time='" + time + '\'';
    }
}
