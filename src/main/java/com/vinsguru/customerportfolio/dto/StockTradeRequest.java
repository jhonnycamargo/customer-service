package com.vinsguru.customerportfolio.dto;

import com.vinsguru.customerportfolio.domain.Ticker;
import com.vinsguru.customerportfolio.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction tradeAction) {

    public Integer totalPrice(){
        return this.price() * this.quantity();
    }
}
