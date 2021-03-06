package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Invoice;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {
    Invoice findByAssetId(Long id);
    boolean existsByNumber(Integer number);
    Optional<Invoice> findByNumber(Integer number);
}
