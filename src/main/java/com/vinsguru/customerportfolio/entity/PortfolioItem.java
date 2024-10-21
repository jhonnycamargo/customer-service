package com.vinsguru.customerportfolio.entity;

import com.vinsguru.customerportfolio.domain.Ticker;
import org.springframework.data.annotation.Id;

public class PortfolioItem {

    @Id
    private Integer id;
    private Integer customerId;
    private Ticker ticker;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
