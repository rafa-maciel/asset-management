package com.rmaciel.academy.books.controllers;

import com.rmaciel.academy.core.models.Book;
import com.rmaciel.academy.core.repositories.BookRepository;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listing all books",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Book[].class))
            })
    })
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<Page<Book>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bookRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Book> create(Book formBook) {
        Book book = bookRepository.save(formBook);
        return ResponseEntity.ok(book);
    }
}
