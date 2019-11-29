package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.PasoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Paso}.
 */
public interface PasoService {

    /**
     * Save a paso.
     *
     * @param pasoDTO the entity to save.
     * @return the persisted entity.
     */
    PasoDTO save(PasoDTO pasoDTO);

    /**
     * Get all the pasos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PasoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" paso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PasoDTO> findOne(String id);

    /**
     * Delete the "id" paso.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
