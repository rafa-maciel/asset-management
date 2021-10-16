package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Model;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<Model, Long> {
}
