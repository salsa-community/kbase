package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.ContactoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Contacto}.
 */
public interface ContactoService {

    /**
     * Save a contacto.
     *
     * @param contactoDTO the entity to save.
     * @return the persisted entity.
     */
    ContactoDTO save(ContactoDTO contactoDTO);

    /**
     * Get all the contactos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContactoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contacto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContactoDTO> findOne(String id);

    /**
     * Delete the "id" contacto.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
