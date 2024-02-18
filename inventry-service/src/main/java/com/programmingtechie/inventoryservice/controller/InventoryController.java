package com.programmingtechie.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	// http://localhost:8082/api/inventory/iphone13,iphone11 this we can achieve using @PathVariable but @RequestParam is more efficient
	// http://localhost:8082/api/inventory?skuCode=iphone11&skuCode=iphone12 this we can achieve using @RequestParam
//	@GetMapping("/{skuCode}")
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}

}
