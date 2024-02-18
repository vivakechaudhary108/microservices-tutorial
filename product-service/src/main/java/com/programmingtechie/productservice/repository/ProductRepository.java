package com.programmingtechie.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programmingtechie.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
