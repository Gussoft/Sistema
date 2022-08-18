package com.gussoft.shoppingservice.repository;

import com.gussoft.shoppingservice.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomerId(Long customerId);

    Invoice findByNumberInvoice(String numberInvoice);
}
