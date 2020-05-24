package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.UnknowIntentsService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.UnknowIntentsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.UnknowIntents}.
 */
@RestController
@RequestMapping("/api")
public class UnknowIntentsResource {

    private final Logger log = LoggerFactory.getLogger(UnknowIntentsResource.class);

    private static final String ENTITY_NAME = "unknowIntents";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnknowIntentsService unknowIntentsService;

    public UnknowIntentsResource(UnknowIntentsService unknowIntentsService) {
        this.unknowIntentsService = unknowIntentsService;
    }

    /**
     * {@code POST  /unknow-intents} : Create a new unknowIntents.
     *
     * @param unknowIntentsDTO the unknowIntentsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unknowIntentsDTO, or with status {@code 400 (Bad Request)} if the unknowIntents has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unknow-intents")
    public ResponseEntity<UnknowIntentsDTO> createUnknowIntents(@RequestBody UnknowIntentsDTO unknowIntentsDTO) throws URISyntaxException {
        log.debug("REST request to save UnknowIntents : {}", unknowIntentsDTO);
        if (unknowIntentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new unknowIntents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnknowIntentsDTO result = unknowIntentsService.save(unknowIntentsDTO);
        return ResponseEntity.created(new URI("/api/unknow-intents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unknow-intents} : Updates an existing unknowIntents.
     *
     * @param unknowIntentsDTO the unknowIntentsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unknowIntentsDTO,
     * or with status {@code 400 (Bad Request)} if the unknowIntentsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unknowIntentsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unknow-intents")
    public ResponseEntity<UnknowIntentsDTO> updateUnknowIntents(@RequestBody UnknowIntentsDTO unknowIntentsDTO) throws URISyntaxException {
        log.debug("REST request to update UnknowIntents : {}", unknowIntentsDTO);
        if (unknowIntentsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnknowIntentsDTO result = unknowIntentsService.save(unknowIntentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unknowIntentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unknow-intents} : get all the unknowIntents.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unknowIntents in body.
     */
    @GetMapping("/unknow-intents")
    public ResponseEntity<List<UnknowIntentsDTO>> getAllUnknowIntents(Pageable pageable) {
        log.debug("REST request to get a page of UnknowIntents");
        Page<UnknowIntentsDTO> page = unknowIntentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unknow-intents/:id} : get the "id" unknowIntents.
     *
     * @param id the id of the unknowIntentsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unknowIntentsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unknow-intents/{id}")
    public ResponseEntity<UnknowIntentsDTO> getUnknowIntents(@PathVariable String id) {
        log.debug("REST request to get UnknowIntents : {}", id);
        Optional<UnknowIntentsDTO> unknowIntentsDTO = unknowIntentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unknowIntentsDTO);
    }

    /**
     * {@code DELETE  /unknow-intents/:id} : delete the "id" unknowIntents.
     *
     * @param id the id of the unknowIntentsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unknow-intents/{id}")
    public ResponseEntity<Void> deleteUnknowIntents(@PathVariable String id) {
        log.debug("REST request to delete UnknowIntents : {}", id);
        unknowIntentsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
