package com.gussoft.productservice.service;

import com.gussoft.productservice.models.Category;
import com.gussoft.productservice.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProduct();

    Product getProduct(Long id);

    Product createProduct(Product obj);

    Product updateProduct(Product obj);

    Product deleteProduct(Long id);

    List<Product> findByCategory(Category obj);

    Product updateStock(Long id, Integer quantity);
}
