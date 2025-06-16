package com.shahriar.OrderService.service;

import com.shahriar.OrderService.entity.Order;
import com.shahriar.OrderService.external.client.ProductService;
import com.shahriar.OrderService.model.OrderRequest;
import com.shahriar.OrderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        //Order Entity -> save the data with status Order Created
        //Product Service -> Call the product service to check the availability of the product
        //Payment Service -> Call the payment service to process the payment
        log.info("Placing Order Request: {}", orderRequest);

        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Product quantity reduced successfully for productId: {}", orderRequest.getProductId());

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        Order savedOrder = orderRepository.save(order);
        log.info("Order placed successfully with ID: {}", savedOrder.getId());

        return savedOrder.getId();
    }
}
