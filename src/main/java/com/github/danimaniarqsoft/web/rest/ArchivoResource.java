package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.ArchivoService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.ArchivoDTO;

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
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Archivo}.
 */
@RestController
@RequestMapping("/api")
public class ArchivoResource {

    private final Logger log = LoggerFactory.getLogger(ArchivoResource.class);

    private static final String ENTITY_NAME = "archivo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArchivoService archivoService;

    public ArchivoResource(ArchivoService archivoService) {
        this.archivoService = archivoService;
    }

    /**
     * {@code POST  /archivos} : Create a new archivo.
     *
     * @param archivoDTO the archivoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new archivoDTO, or with status {@code 400 (Bad Request)} if the archivo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/archivos")
    public ResponseEntity<ArchivoDTO> createArchivo(@RequestBody ArchivoDTO archivoDTO) throws URISyntaxException {
        log.debug("REST request to save Archivo : {}", archivoDTO);
        if (archivoDTO.getId() != null) {
            throw new BadRequestAlertException("A new archivo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArchivoDTO result = archivoService.save(archivoDTO);
        return ResponseEntity.created(new URI("/api/archivos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /archivos} : Updates an existing archivo.
     *
     * @param archivoDTO the archivoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated archivoDTO,
     * or with status {@code 400 (Bad Request)} if the archivoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the archivoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/archivos")
    public ResponseEntity<ArchivoDTO> updateArchivo(@RequestBody ArchivoDTO archivoDTO) throws URISyntaxException {
        log.debug("REST request to update Archivo : {}", archivoDTO);
        if (archivoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArchivoDTO result = archivoService.save(archivoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, archivoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /archivos} : get all the archivos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of archivos in body.
     */
    @GetMapping("/archivos")
    public ResponseEntity<List<ArchivoDTO>> getAllArchivos(Pageable pageable) {
        log.debug("REST request to get a page of Archivos");
        Page<ArchivoDTO> page = archivoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /archivos/:id} : get the "id" archivo.
     *
     * @param id the id of the archivoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the archivoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/archivos/{id}")
    public ResponseEntity<ArchivoDTO> getArchivo(@PathVariable String id) {
        log.debug("REST request to get Archivo : {}", id);
        Optional<ArchivoDTO> archivoDTO = archivoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(archivoDTO);
    }

    /**
     * {@code DELETE  /archivos/:id} : delete the "id" archivo.
     *
     * @param id the id of the archivoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/archivos/{id}")
    public ResponseEntity<Void> deleteArchivo(@PathVariable String id) {
        log.debug("REST request to delete Archivo : {}", id);
        archivoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
