package com.shahriar.OrderService.service;

import com.shahriar.OrderService.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
