package com.vinsguru.customerportfolio.mapper;

import com.vinsguru.customerportfolio.dto.CustomerInformation;
import com.vinsguru.customerportfolio.dto.Holding;
import com.vinsguru.customerportfolio.entity.Customer;
import com.vinsguru.customerportfolio.entity.PortfolioItem;

import java.util.List;

public class EntityDtoMapper {

    public static CustomerInformation toCustomerInformation(Customer customer, List<PortfolioItem> items){
        var holdings = items.stream()
                .map(item -> new Holding(item.getTicker(), item.getQuantity()))
                .toList();
        return new CustomerInformation(customer.getId(), customer.getName(), customer.getBalance(), holdings);
    }

}
