package com.mscv.orderservice.service;

import com.mscv.orderservice.dto.InventoryResponse;
import com.mscv.orderservice.dto.OrderLineItemDto;
import com.mscv.orderservice.dto.OrderRequest;
import com.mscv.orderservice.entities.Order;
import com.mscv.orderservice.entities.OrderLineItems;
import com.mscv.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapOrderLineItemDtoToOrderLineItemEntity)
                .collect(toList());

        List<String> codSkuList = extractCodSku(orderLineItems);

        InventoryResponse[] codSkus = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", (uriBuilder -> uriBuilder.queryParam("codSku", codSkuList).build()))
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if (allProductInStock(codSkus)) {
            order.setOrderLineItems(orderLineItems);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Todos los productos no están en stock");
        }


    }

    private boolean allProductInStock(InventoryResponse[] codSkus) {
        return asList(codSkus)
                .stream()
                .allMatch(InventoryResponse::isInStock);
    }

    private List<String> extractCodSku(List<OrderLineItems> orderLineItems) {
        return orderLineItems.stream()
                .map(OrderLineItems::getCodSku)
                .collect(toList());
    }

    private OrderLineItems mapOrderLineItemDtoToOrderLineItemEntity(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setCantidad(orderLineItemDto.getCantidad());
        orderLineItems.setCodSku(orderLineItemDto.getCodSku());
        orderLineItems.setPrice(orderLineItemDto.getPrice());

        return orderLineItems;
    }
}
