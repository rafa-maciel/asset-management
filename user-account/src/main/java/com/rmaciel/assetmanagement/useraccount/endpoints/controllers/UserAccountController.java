package com.rmaciel.assetmanagement.useraccount.endpoints.controllers;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import com.rmaciel.assetmanagement.useraccount.endpoints.controllers.forms.UserAccountForm;
import com.rmaciel.assetmanagement.useraccount.endpoints.controllers.forms.UserAccountSearchForm;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class UserAccountController {

    private final UserAccountRepository accountRepository;

    public UserAccountController(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public ResponseEntity<UserAccount> create(@RequestBody @Valid UserAccountForm form) {
        UserAccount account = accountRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> details(@PathVariable Long id) {
        Optional<UserAccount> optional = accountRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("User account has been not find by ID " + id);

        return  ResponseEntity.ok(optional.get());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> update(@PathVariable Long id, @RequestBody @Valid UserAccountForm form) {
        Optional<UserAccount> optional = accountRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("User account has been not find by ID " + id);

        UserAccount account = accountRepository.save(form.updateFrom(optional.get()));

        return ResponseEntity.ok(account);
    }

    @SneakyThrows
    @PutMapping("/{id}/reset")
    public ResponseEntity<UserAccount> reset(@PathVariable Long id, @RequestBody String newPassword) {
        Optional<UserAccount> optional = accountRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("User account has been not find by ID " + id);
        UserAccount account = optional.get();
        account.reset(newPassword);

        UserAccount accountUpdated = accountRepository.save(account);

        return ResponseEntity.ok(accountUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
        Optional<UserAccount> optional = accountRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("User account has been not find by ID " + id);

        accountRepository.delete(optional.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserAccount>> search(Pageable pageable, @Valid UserAccountSearchForm form) {
        Specification<UserAccount> accountSpecs = form.buildSpecs();
        Page<UserAccount> accountPage = accountRepository.findAll(accountSpecs, pageable);

        return ResponseEntity.ok(accountPage);
    }


}
