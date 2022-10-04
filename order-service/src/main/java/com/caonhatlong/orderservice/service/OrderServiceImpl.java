package com.caonhatlong.orderservice.service;

import com.caonhatlong.orderservice.dto.OrderLineItemsDto;
import com.caonhatlong.orderservice.dto.OrderRequest;
import com.caonhatlong.orderservice.model.Order;
import com.caonhatlong.orderservice.model.OrderLineItems;
import com.caonhatlong.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);

        Boolean result =  webClient.get()
                .uri("http://localhost:2000/api/inventory")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if(Boolean.TRUE.equals(result)){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock, please try it later!");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
