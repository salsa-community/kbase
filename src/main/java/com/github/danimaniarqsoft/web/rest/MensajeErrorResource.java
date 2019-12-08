package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.service.MensajeErrorService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.MensajeErrorDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.MensajeError}.
 */
@RestController
@RequestMapping("/api")
public class MensajeErrorResource {

    private final Logger log = LoggerFactory.getLogger(MensajeErrorResource.class);

    private static final String ENTITY_NAME = "mensajeError";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MensajeErrorService mensajeErrorService;

    public MensajeErrorResource(MensajeErrorService mensajeErrorService) {
        this.mensajeErrorService = mensajeErrorService;
    }

    /**
     * {@code POST  /mensaje-errors} : Create a new mensajeError.
     *
     * @param mensajeErrorDTO the mensajeErrorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mensajeErrorDTO, or with status {@code 400 (Bad Request)} if the mensajeError has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mensaje-errors")
    public ResponseEntity<MensajeErrorDTO> createMensajeError(@Valid @RequestBody MensajeError mensajeErrorDTO) throws URISyntaxException {
        log.debug("REST request to save MensajeError : {}", mensajeErrorDTO);
        if (mensajeErrorDTO.getId() != null) {
            throw new BadRequestAlertException("A new mensajeError cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MensajeErrorDTO result = mensajeErrorService.save(mensajeErrorDTO);
        return ResponseEntity.created(new URI("/api/mensaje-errors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mensaje-errors} : Updates an existing mensajeError.
     *
     * @param mensajeErrorDTO the mensajeErrorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mensajeErrorDTO,
     * or with status {@code 400 (Bad Request)} if the mensajeErrorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mensajeErrorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mensaje-errors")
    public ResponseEntity<MensajeErrorDTO> updateMensajeError(@Valid @RequestBody MensajeError mensajeErrorDTO) throws URISyntaxException {
        log.debug("REST request to update MensajeError : {}", mensajeErrorDTO);
        if (mensajeErrorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MensajeErrorDTO result = mensajeErrorService.save(mensajeErrorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mensajeErrorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mensaje-errors} : get all the mensajeErrors.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mensajeErrors in body.
     */
    @GetMapping("/mensaje-errors")
    public ResponseEntity<List<MensajeError>> getAllMensajeErrors(Pageable pageable) {
        log.debug("REST request to get a page of MensajeErrors");
        Page<MensajeError> page = mensajeErrorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mensaje-errors/:id} : get the "id" mensajeError.
     *
     * @param id the id of the mensajeErrorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mensajeErrorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mensaje-errors/{id}")
    public ResponseEntity<MensajeError> getMensajeError(@PathVariable String id) {
        log.debug("REST request to get MensajeError : {}", id);
        Optional<MensajeError> mensajeErrorDTO = mensajeErrorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mensajeErrorDTO);
    }

    @GetMapping("/mensaje-errors/codigo/{id}")
    public ResponseEntity<MensajeError> getMensajeErrorByCodigo(@PathVariable String id) {
        log.debug("REST request to get MensajeError : {}", id);
        Optional<MensajeError> mensajeErrorDTO = mensajeErrorService.findOneByClave(id);
        return ResponseUtil.wrapOrNotFound(mensajeErrorDTO);
    }

    /**
     * {@code DELETE  /mensaje-errors/:id} : delete the "id" mensajeError.
     *
     * @param id the id of the mensajeErrorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mensaje-errors/{id}")
    public ResponseEntity<Void> deleteMensajeError(@PathVariable String id) {
        log.debug("REST request to delete MensajeError : {}", id);
        mensajeErrorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
