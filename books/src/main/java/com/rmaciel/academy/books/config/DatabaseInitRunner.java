package com.rmaciel.academy.books.config;

import com.rmaciel.academy.core.models.Book;
import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.BookRepository;
import com.rmaciel.academy.core.repositories.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("DEV")
@Component
@Slf4j
public class DatabaseInitRunner implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final UserAccountRepository userAccountRepository;

    public DatabaseInitRunner(BookRepository bookRepository, UserAccountRepository userAccountRepository) {
        this.bookRepository = bookRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        log.info(
//                this.bookRepository.save(new Book(1L, "Deny Lovato", "Deny and the boys", true)).toString()
//        );
    }
}
