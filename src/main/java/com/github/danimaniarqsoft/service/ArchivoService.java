package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.ArchivoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Archivo}.
 */
public interface ArchivoService {

    /**
     * Save a archivo.
     *
     * @param archivoDTO the entity to save.
     * @return the persisted entity.
     */
    ArchivoDTO save(ArchivoDTO archivoDTO);

    /**
     * Get all the archivos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ArchivoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" archivo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArchivoDTO> findOne(String id);

    /**
     * Delete the "id" archivo.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
