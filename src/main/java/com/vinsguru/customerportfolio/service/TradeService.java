package com.vinsguru.customerportfolio.service;

import com.vinsguru.customerportfolio.dto.StockTradeRequest;
import com.vinsguru.customerportfolio.dto.StockTradeResponse;
import com.vinsguru.customerportfolio.exceptions.ApplicationExceptions;
import com.vinsguru.customerportfolio.repository.CustomerRepository;
import com.vinsguru.customerportfolio.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TradeService {

    private final CustomerRepository customerRepository;
    private final PortfolioItemRepository portfolioItemRepository;

    public TradeService(CustomerRepository customerRepository, PortfolioItemRepository portfolioItemRepository) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public Mono<StockTradeResponse> trade(Integer customerId, StockTradeRequest request){
        return switch (request.tradeAction()) {
            case BUY -> buyStock(customerId, request);
            case SELL -> sellStock(customerId, request);
        };
    }

    private Mono<StockTradeResponse> buyStock(Integer customerId, StockTradeRequest request){
        var customerMno = this.customerRepository.findById(customerId)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(customerId))
                .filter(customer -> customer.getBalance() >= request.totalPrice())
                .switchIfEmpty(ApplicationExceptions.insufficientBalance(customerId));
        return Mono.empty();
    }

    private Mono<StockTradeResponse> sellStock(Integer customerId, StockTradeRequest request){
        return Mono.empty();
    }

}
