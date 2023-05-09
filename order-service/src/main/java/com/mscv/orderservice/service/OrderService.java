package com.mscv.orderservice.service;

import com.mscv.orderservice.dto.OrderLineItemDto;
import com.mscv.orderservice.dto.OrderRequest;
import com.mscv.orderservice.entities.Order;
import com.mscv.orderservice.entities.OrderLineItems;
import com.mscv.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapOrderLineItemDtoToOrderLineItemEntity)
                .collect(Collectors.toList());

        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapOrderLineItemDtoToOrderLineItemEntity(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setCantidad(orderLineItemDto.getCantidad());
        orderLineItems.setCodSku(orderLineItemDto.getCodSku());
        orderLineItems.setPrice(orderLineItemDto.getPrice());

        return orderLineItems;
    }
}
