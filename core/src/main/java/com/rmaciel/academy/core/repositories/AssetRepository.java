package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends PagingAndSortingRepository<Asset, Long>, JpaSpecificationExecutor<Asset> {
    boolean existsByOwner(User user);
    boolean existsByChipIdentification(String chipIdentification);
    boolean existsByCompanyIdentification(String companyIdentification);
    boolean existsByHostname(String hostname);
    boolean existsByLineIdentification(String lineIdentification);
    boolean existsBySerialNumber(String serialNumber);
    boolean existsByTag(String tag);
    boolean existsByImei(String imei);
}
