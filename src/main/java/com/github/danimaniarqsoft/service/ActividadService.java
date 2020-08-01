package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.ActividadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Actividad}.
 */
public interface ActividadService {

    /**
     * Save a actividad.
     *
     * @param actividadDTO the entity to save.
     * @return the persisted entity.
     */
    ActividadDTO save(ActividadDTO actividadDTO);

    /**
     * Get all the actividads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActividadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" actividad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActividadDTO> findOne(String id);

    /**
     * Delete the "id" actividad.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
