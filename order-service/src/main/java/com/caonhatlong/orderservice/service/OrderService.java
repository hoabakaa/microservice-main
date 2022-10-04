package com.caonhatlong.orderservice.service;

import com.caonhatlong.orderservice.dto.OrderRequest;

public interface OrderService {

    public void placeOrder(OrderRequest orderRequest);
}
