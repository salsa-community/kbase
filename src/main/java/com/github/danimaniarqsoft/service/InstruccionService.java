package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.InstruccionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Instruccion}.
 */
public interface InstruccionService {

    /**
     * Save a instruccion.
     *
     * @param instruccionDTO the entity to save.
     * @return the persisted entity.
     */
    InstruccionDTO save(InstruccionDTO instruccionDTO);

    /**
     * Get all the instruccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InstruccionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" instruccion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InstruccionDTO> findOne(String id);

    /**
     * Delete the "id" instruccion.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
