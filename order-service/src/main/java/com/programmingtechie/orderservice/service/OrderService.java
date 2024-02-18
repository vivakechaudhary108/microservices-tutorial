package com.programmingtechie.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderLineItemsRequest;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	@Autowired
	private final OrderRepository orderRepository;
	@Autowired
	private final WebClient webClient;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).orderLineItemsList(orderRequest
				.getOrderLineItemsDtoList().stream().map(this::mapToOrderLineItems).collect(Collectors.toList()))
				.build();
		List<String> codes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
		
		// call inventory service, and place order if product is in stock
		InventoryResponse[] res = webClient.get().uri("http://localhost:8084/api/inventory", uriBuilder->uriBuilder.queryParam("skuCode", codes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		Boolean allProductIsInStock = Arrays.stream(res).allMatch(InventoryResponse::getIsInStock);
		if(allProductIsInStock) {
			orderRepository.save(order);
		} else {
			throw new IllegalStateException("Producrt is not in Stock");
		}
		
	}

	private OrderLineItems mapToOrderLineItems(OrderLineItemsRequest request) {
		return OrderLineItems.builder().price(request.getPrice()).quantity(request.getQuantity())
				.skuCode(request.getSkuCode()).build();
	}

	public List<Order> findAllOrders() {
		List<Order> orderList = orderRepository.findAll();
		return orderList;
	}

//	private final OrderRequest mapToOrderDto(OrderRequest req) {
//		return Order.builder().orderLineItemsList(req.getOrderLineItemsDtoList().stream().map(this::mapTo)).
//	}
}
