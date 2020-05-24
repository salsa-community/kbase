package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.UnknowIntentsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.UnknowIntents}.
 */
public interface UnknowIntentsService {

    /**
     * Save a unknowIntents.
     *
     * @param unknowIntentsDTO the entity to save.
     * @return the persisted entity.
     */
    UnknowIntentsDTO save(UnknowIntentsDTO unknowIntentsDTO);

    /**
     * Get all the unknowIntents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnknowIntentsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unknowIntents.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnknowIntentsDTO> findOne(String id);

    /**
     * Delete the "id" unknowIntents.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
