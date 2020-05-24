package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.ContextoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing
 * {@link com.github.danimaniarqsoft.domain.Contexto}.
 */
public interface ContextoService {

    /**
     * Save a contexto.
     *
     * @param contextoDTO the entity to save.
     * @return the persisted entity.
     */
    ContextoDTO save(ContextoDTO contextoDTO);

    /**
     * Get all the contextos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContextoDTO> findAll(Pageable pageable);

    /**
     * Get all the contextos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    List<ContextoDTO> findAll();

    /**
     * Get the "id" contexto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContextoDTO> findOne(String id);

    /**
     * Delete the "id" contexto.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
