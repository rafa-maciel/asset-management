package com.rmaciel.academy.core.repositories;

import com.rmaciel.academy.core.models.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    Iterable<Note> findAllByAssetId(Long id);

}
