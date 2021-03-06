package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

    boolean existsByName(String name);
    boolean existsByRe(Integer re);
    Optional<User> findByRe(Integer re);
}
