package com.caonhatlong.productservice.repository;

import com.caonhatlong.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
