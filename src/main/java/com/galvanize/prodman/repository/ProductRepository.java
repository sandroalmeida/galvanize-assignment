package com.galvanize.prodman.repository;

import com.galvanize.prodman.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product>  getByStrId(String strId);
}
