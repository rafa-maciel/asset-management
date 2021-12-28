package com.rmaciel.assetmanagement.invoice.endpoints;

import com.rmaciel.academy.core.models.Invoice;
import com.rmaciel.academy.core.repositories.InvoiceRepository;
import com.rmaciel.assetmanagement.invoice.endpoints.forms.InvoiceForm;
import com.rmaciel.assetmanagement.invoice.endpoints.forms.InvoiceSearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;

    protected InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    private Invoice findOrNull(Long id) {
        Optional<Invoice> invoiceOp = invoiceRepository.findById(id);
        if (invoiceOp.isEmpty())
                return null;

        return invoiceOp.get();
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody @Valid InvoiceForm form) {
        Invoice invoice = invoiceRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<Invoice> update(@PathVariable("invoiceId") Long id, @RequestBody @Valid InvoiceForm form) {
        Invoice invoice = findOrNull(id);
        if (invoice == null) return ResponseEntity.badRequest().build();

        Invoice updatedInvoice = invoiceRepository.save(form.updateFrom(invoice));
        return ResponseEntity.ok(updatedInvoice);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Invoice>> search(Pageable pageable, @Valid InvoiceSearchForm form) {
        Specification<Invoice> specs = form.buildSpecs();
        Page<Invoice> page = invoiceRepository.findAll(specs, pageable);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> delete(@PathVariable("invoiceId") Long id) {
        Invoice invoice = findOrNull(id);
        log.info(invoice.getVendor());
        if (invoice == null) return ResponseEntity.badRequest().build();

        invoiceRepository.delete(invoice);
        log.info(findOrNull(id).getVendor());
        return ResponseEntity.ok().build();
    }
}
