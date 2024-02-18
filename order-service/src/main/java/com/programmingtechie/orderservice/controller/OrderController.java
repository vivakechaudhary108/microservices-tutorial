package com.programmingtechie.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	@Autowired
	OrderService orderService;
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest){
		orderService.placeOrder(orderRequest);
		return "Order placed successfully";
		
	}
	
	@GetMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public List<Order> getAllOrders(){
		return orderService.findAllOrders();
		
	}
}
