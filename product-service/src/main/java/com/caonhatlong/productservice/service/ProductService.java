package com.caonhatlong.productservice.service;

import com.caonhatlong.productservice.dto.ProductRequest;
import com.caonhatlong.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    public void createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProducts();
}
