package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.LinkDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Link}.
 */
public interface LinkService {

    /**
     * Save a link.
     *
     * @param linkDTO the entity to save.
     * @return the persisted entity.
     */
    LinkDTO save(LinkDTO linkDTO);

    /**
     * Get all the links.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LinkDTO> findAll(Pageable pageable);


    /**
     * Get the "id" link.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LinkDTO> findOne(String id);

    /**
     * Delete the "id" link.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
