package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.ContextoService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.ContextoDTO;

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
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Contexto}.
 */
@RestController
@RequestMapping("/api")
public class ContextoResource {

    private final Logger log = LoggerFactory.getLogger(ContextoResource.class);

    private static final String ENTITY_NAME = "contexto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContextoService contextoService;

    public ContextoResource(ContextoService contextoService) {
        this.contextoService = contextoService;
    }

    /**
     * {@code POST  /contextos} : Create a new contexto.
     *
     * @param contextoDTO the contextoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contextoDTO, or with status {@code 400 (Bad Request)} if the contexto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contextos")
    public ResponseEntity<ContextoDTO> createContexto(@RequestBody ContextoDTO contextoDTO) throws URISyntaxException {
        log.debug("REST request to save Contexto : {}", contextoDTO);
        if (contextoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contexto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContextoDTO result = contextoService.save(contextoDTO);
        return ResponseEntity.created(new URI("/api/contextos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contextos} : Updates an existing contexto.
     *
     * @param contextoDTO the contextoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contextoDTO,
     * or with status {@code 400 (Bad Request)} if the contextoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contextoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contextos")
    public ResponseEntity<ContextoDTO> updateContexto(@RequestBody ContextoDTO contextoDTO) throws URISyntaxException {
        log.debug("REST request to update Contexto : {}", contextoDTO);
        if (contextoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContextoDTO result = contextoService.save(contextoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contextoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contextos} : get all the contextos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contextos in body.
     */
    @GetMapping("/contextos")
    public ResponseEntity<List<ContextoDTO>> getAllContextos(Pageable pageable) {
        log.debug("REST request to get a page of Contextos");
        Page<ContextoDTO> page = contextoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/catalogos/contextos")
    public ResponseEntity<List<ContextoDTO>> findAllContextos() {
        log.debug("REST request to get a page of Contextos");
        List<ContextoDTO> contextoList = contextoService.findAll();
        return ResponseEntity.ok().body(contextoList);
    }

    /**
     * {@code GET  /contextos/:id} : get the "id" contexto.
     *
     * @param id the id of the contextoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contextoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contextos/{id}")
    public ResponseEntity<ContextoDTO> getContexto(@PathVariable String id) {
        log.debug("REST request to get Contexto : {}", id);
        Optional<ContextoDTO> contextoDTO = contextoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contextoDTO);
    }

    /**
     * {@code DELETE  /contextos/:id} : delete the "id" contexto.
     *
     * @param id the id of the contextoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contextos/{id}")
    public ResponseEntity<Void> deleteContexto(@PathVariable String id) {
        log.debug("REST request to delete Contexto : {}", id);
        contextoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
