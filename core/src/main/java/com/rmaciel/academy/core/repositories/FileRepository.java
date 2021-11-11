package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Iterable<File> findAllByAssetId(Long id);
}
