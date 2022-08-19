package com.gussoft.shoppingservice.service.impl;

import com.gussoft.shoppingservice.client.CustomerClient;
import com.gussoft.shoppingservice.client.ProductClient;
import com.gussoft.shoppingservice.models.Invoice;
import com.gussoft.shoppingservice.models.InvoiceItem;
import com.gussoft.shoppingservice.models.dto.Customer;
import com.gussoft.shoppingservice.models.dto.Product;
import com.gussoft.shoppingservice.repository.InvoiceItemsRepository;
import com.gussoft.shoppingservice.repository.InvoiceRepository;
import com.gussoft.shoppingservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    @Autowired
    private InvoiceItemsRepository irepo;

    @Autowired
    CustomerClient client;

    @Autowired
    ProductClient product;

    @Override
    public List<Invoice> findInvoiceAll() {
        return repo.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice data = repo.findByNumberInvoice(invoice.getNumberInvoice());
        if (data != null) {
            return data;
        }
        invoice.setState("Created");
        data = repo.save(invoice);
        data.getItems().forEach(invoiceItem -> {
            product.updateStockProduct(invoiceItem.getProductId(), (int) (invoiceItem.getQuantity() * -1));
        });
        return data;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice data = getInvoice(invoice.getId());
        if (data == null) {
            return null;
        }
        data.setCustomerId(invoice.getCustomerId());
        data.setDescription(invoice.getDescription());
        data.setNumberInvoice(invoice.getNumberInvoice());
        data.getItems().clear();
        data.setItems(invoice.getItems());
        return repo.save(data);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice data = getInvoice(invoice.getId());
        if (data == null) {
            return null;
        }
        data.setState("Deleted");
        return repo.save(data);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice data = repo.findById(id).orElse(null);
        if (data != null) {
            Customer customer = client.getCustomer(data.getCustomerId()).getBody();
            data.setCustomer(customer);
            List<InvoiceItem> itemsList = data.getItems().stream().map(item -> {
               Product pro = product.getProduct(item.getProductId()).getBody();
               item.setProduct(pro);
               return item;
            }).collect(Collectors.toList());
            data.setItems(itemsList);
        }
        return data;
    }
}
