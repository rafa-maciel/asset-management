package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Model;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<Model, Long>, JpaSpecificationExecutor<Model> {

    boolean existsByTitle(String title);
    Optional<Model> findByTitle(String Title);
}
