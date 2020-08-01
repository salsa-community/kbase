package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.EventoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Evento}.
 */
public interface EventoService {

    /**
     * Save a evento.
     *
     * @param eventoDTO the entity to save.
     * @return the persisted entity.
     */
    EventoDTO save(EventoDTO eventoDTO);

    /**
     * Get all the eventos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" evento.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventoDTO> findOne(String id);

    /**
     * Delete the "id" evento.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
