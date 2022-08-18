package com.gussoft.shoppingservice.service;

import com.gussoft.shoppingservice.models.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> findInvoiceAll();

    Invoice createInvoice(Invoice invoice);

    Invoice updateInvoice(Invoice invoice);

    Invoice deleteInvoice(Invoice invoice);

    Invoice getInvoice(Long id);
}
