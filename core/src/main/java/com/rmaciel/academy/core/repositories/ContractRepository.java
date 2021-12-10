package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends PagingAndSortingRepository<Contract, Long>, JpaSpecificationExecutor<Contract> {
    Contract findByAssetId(Long id);
}
