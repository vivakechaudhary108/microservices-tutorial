package com.programmingtechie.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programmingtechie.inventoryservice.dto.InventoryResponse;
import com.programmingtechie.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	@Autowired
	private final InventoryRepository inventoryRepository;
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory->InventoryResponse.builder().skuode(inventory.getSkuCode())
					.isInStock(inventory.getQuantity()>0).build()).collect(Collectors.toList());
	}
}
