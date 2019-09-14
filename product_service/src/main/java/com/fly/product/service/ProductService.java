package com.fly.product.service;

import com.fly.product.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);

}
