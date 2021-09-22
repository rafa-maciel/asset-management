package com.rmaciel.assetmanagement.user.endpoints.controllers;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.repositories.UserRepository;
import com.rmaciel.assetmanagement.user.endpoints.controllers.forms.UserForm;
import com.rmaciel.assetmanagement.user.endpoints.controllers.forms.UserSearchForm;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findUserOrRunException(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new NotFoundException("User has not been found by id: " + id);

        return optionalUser.get();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserForm form) {
        User user = userRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserForm form) throws NotFoundException {
        User savedUser = findUserOrRunException(id);
        User user = userRepository.save(form.updateFrom(savedUser));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NotFoundException {
        User user = findUserOrRunException(id);
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> details(@PathVariable Long id) throws NotFoundException {
        User user = findUserOrRunException(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> search(Pageable pageable, @Valid UserSearchForm form) {
        Specification<User> specs = form.buildSpecs();
        Page<User> page = userRepository.findAll(specs, pageable);

        return ResponseEntity.ok(page);
    }


}
