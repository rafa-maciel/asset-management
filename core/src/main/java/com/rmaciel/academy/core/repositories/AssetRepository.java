package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Asset;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends PagingAndSortingRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {
}
