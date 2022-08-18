package com.gussoft.shoppingservice.repository;

import com.gussoft.shoppingservice.models.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}
