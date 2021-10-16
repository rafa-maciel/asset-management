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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findUserOrNull(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return null;

        return optionalUser.get();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserForm form) {
        User user = userRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserForm form) {
        User savedUser = findUserOrNull(id);
        if (savedUser == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        User user = userRepository.save(form.updateFrom(savedUser));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User user = findUserOrNull(id);
        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> details(@PathVariable Long id) {
        User user = findUserOrNull(id);
        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(user);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importUsers(@RequestBody @Valid List<UserForm> listForm) {
        System.out.println(listForm);
        if(listForm != null)
            listForm.forEach(form -> {
                if (form != null) userRepository.save(form.build());
            });

        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> search(Pageable pageable, @Valid UserSearchForm form) {
        Specification<User> specs = form.buildSpecs();
        Page<User> page = userRepository.findAll(specs, pageable);

        return ResponseEntity.ok(page);
    }


}