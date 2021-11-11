package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {
    Contract findByAssetId(Long id);
}
