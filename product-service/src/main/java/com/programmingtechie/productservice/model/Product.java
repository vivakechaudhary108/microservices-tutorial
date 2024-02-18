package com.programmingtechie.productservice.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class Product {
	
	@GeneratedValue
	@jakarta.persistence.Id
	private Integer id;
	private String name;
	private String description;
	private BigDecimal price;
	

}
