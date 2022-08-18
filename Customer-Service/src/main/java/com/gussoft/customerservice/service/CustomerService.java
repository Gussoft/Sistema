package com.gussoft.customerservice.service;

import com.gussoft.customerservice.models.Customer;
import com.gussoft.customerservice.models.Region;

import java.util.List;

public interface CustomerService {

    List<Customer> findCustomerAll();

    List<Customer> findCustomersByRegion(Region obj);

    Customer createCustomer(Customer obj);

    Customer updateCustomer(Customer obj);

    Customer deleteCustomer(Customer obj);

    Customer getCustomer(Long id);
}
