package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.PasoService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.PasoDTO;

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
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Paso}.
 */
@RestController
@RequestMapping("/api")
public class PasoResource {

    private final Logger log = LoggerFactory.getLogger(PasoResource.class);

    private static final String ENTITY_NAME = "paso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PasoService pasoService;

    public PasoResource(PasoService pasoService) {
        this.pasoService = pasoService;
    }

    /**
     * {@code POST  /pasos} : Create a new paso.
     *
     * @param pasoDTO the pasoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pasoDTO, or with status {@code 400 (Bad Request)} if the paso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pasos")
    public ResponseEntity<PasoDTO> createPaso(@RequestBody PasoDTO pasoDTO) throws URISyntaxException {
        log.debug("REST request to save Paso : {}", pasoDTO);
        if (pasoDTO.getId() != null) {
            throw new BadRequestAlertException("A new paso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PasoDTO result = pasoService.save(pasoDTO);
        return ResponseEntity.created(new URI("/api/pasos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pasos} : Updates an existing paso.
     *
     * @param pasoDTO the pasoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pasoDTO,
     * or with status {@code 400 (Bad Request)} if the pasoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pasoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pasos")
    public ResponseEntity<PasoDTO> updatePaso(@RequestBody PasoDTO pasoDTO) throws URISyntaxException {
        log.debug("REST request to update Paso : {}", pasoDTO);
        if (pasoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PasoDTO result = pasoService.save(pasoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pasoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pasos} : get all the pasos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pasos in body.
     */
    @GetMapping("/pasos")
    public ResponseEntity<List<PasoDTO>> getAllPasos(Pageable pageable) {
        log.debug("REST request to get a page of Pasos");
        Page<PasoDTO> page = pasoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pasos/:id} : get the "id" paso.
     *
     * @param id the id of the pasoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pasoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pasos/{id}")
    public ResponseEntity<PasoDTO> getPaso(@PathVariable String id) {
        log.debug("REST request to get Paso : {}", id);
        Optional<PasoDTO> pasoDTO = pasoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pasoDTO);
    }

    /**
     * {@code DELETE  /pasos/:id} : delete the "id" paso.
     *
     * @param id the id of the pasoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pasos/{id}")
    public ResponseEntity<Void> deletePaso(@PathVariable String id) {
        log.debug("REST request to delete Paso : {}", id);
        pasoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
