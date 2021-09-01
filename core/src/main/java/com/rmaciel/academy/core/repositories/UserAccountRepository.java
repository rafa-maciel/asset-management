package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
