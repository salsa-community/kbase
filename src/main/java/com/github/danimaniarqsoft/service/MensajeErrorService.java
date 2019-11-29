package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.service.dto.MensajeErrorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.MensajeError}.
 */
public interface MensajeErrorService {

    /**
     * Save a mensajeError.
     *
     * @param mensajeErrorDTO the entity to save.
     * @return the persisted entity.
     */
    MensajeErrorDTO save(MensajeErrorDTO mensajeErrorDTO);

    /**
     * Get all the mensajeErrors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MensajeError> findAll(Pageable pageable);


    /**
     * Get the "id" mensajeError.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MensajeErrorDTO> findOne(String id);

    /**
     * Delete the "id" mensajeError.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
