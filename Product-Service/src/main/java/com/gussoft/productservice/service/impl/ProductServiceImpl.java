package com.gussoft.productservice.service.impl;

import com.gussoft.productservice.models.Category;
import com.gussoft.productservice.models.Product;
import com.gussoft.productservice.repository.ProductRepository;
import com.gussoft.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    //@Autowired
    private final ProductRepository repo;

    @Override
    public List<Product> listAllProduct() {
        return repo.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product obj) {
        obj.setStatus("Created");
        obj.setCreateAt(new Date());
        return repo.save(obj);
    }

    @Override
    public Product updateProduct(Product obj) {
        Product data = getProduct(obj.getId());
        if (null == data) {
            return null;
        }
        data.setName(obj.getName());
        data.setDescription(obj.getDescription());
        data.setCategory(obj.getCategory());
        data.setPrice(obj.getPrice());
        return repo.save(data);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product data = getProduct(id);
        if (null == data) {
            return null;
        }
        data.setStatus("Deleted");
        return repo.save(data);
    }

    @Override
    public List<Product> findByCategory(Category obj) {

        return repo.findByCategory(obj);
    }

    @Override
    public Product updateStock(Long id, Integer quantity) {
        Product data = getProduct(id);
        if (null == data) {
            return null;
        }
        Integer stock = data.getStock() + quantity;
        data.setStock(stock);
        return repo.save(data);
    }
}
