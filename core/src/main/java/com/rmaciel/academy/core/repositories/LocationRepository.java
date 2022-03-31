package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Location;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long>, JpaSpecificationExecutor<Location> {
    boolean existsByTitle(String title);
    Optional<Location> findByTitle(String title);
}
