package com.gussoft.shoppingservice.service.impl;

import com.gussoft.shoppingservice.models.Invoice;
import com.gussoft.shoppingservice.repository.InvoiceItemsRepository;
import com.gussoft.shoppingservice.repository.InvoiceRepository;
import com.gussoft.shoppingservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    @Autowired
    private InvoiceItemsRepository irepo;

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
        return repo.save(invoice);
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
        return repo.findById(id).orElse(null);
    }
}
