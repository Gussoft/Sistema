package com.gussoft.customerservice.service.impl;

import com.gussoft.customerservice.models.Customer;
import com.gussoft.customerservice.models.Region;
import com.gussoft.customerservice.repository.CustomerRepository;
import com.gussoft.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repo;

    @Override
    public List<Customer> findCustomerAll() {
        return repo.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region obj) {
        return repo.findByRegion(obj);
    }

    @Override
    public Customer createCustomer(Customer obj) {
        Customer data = repo.findByNumberID(obj.getNumberID());
        if (data != null){
            return data;
        }

        obj.setState("Created");
        data = repo.save(obj);
        return data;
    }

    @Override
    public Customer updateCustomer(Customer obj) {
        Customer data = getCustomer(obj.getId());
        if (data == null) {
            return null;
        }
        data.setFirstName(obj.getFirstName());
        data.setLastName(obj.getLastName());
        data.setEmail(obj.getEmail());
        data.setPhotoUrl(obj.getPhotoUrl());
        return repo.save(data);
    }

    @Override
    public Customer deleteCustomer(Customer obj) {
        Customer data = getCustomer(obj.getId());
        if (data == null) {
            return null;
        }

        return repo.save(obj);
    }

    @Override
    public Customer getCustomer(Long id) {
        return repo.findById(id).orElse(null);
    }
}
