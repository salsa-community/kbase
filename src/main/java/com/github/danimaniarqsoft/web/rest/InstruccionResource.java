package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.InstruccionService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.InstruccionDTO;

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
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Instruccion}.
 */
@RestController
@RequestMapping("/api")
public class InstruccionResource {

    private final Logger log = LoggerFactory.getLogger(InstruccionResource.class);

    private static final String ENTITY_NAME = "instruccion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstruccionService instruccionService;

    public InstruccionResource(InstruccionService instruccionService) {
        this.instruccionService = instruccionService;
    }

    /**
     * {@code POST  /instruccions} : Create a new instruccion.
     *
     * @param instruccionDTO the instruccionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instruccionDTO, or with status {@code 400 (Bad Request)} if the instruccion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/instruccions")
    public ResponseEntity<InstruccionDTO> createInstruccion(@RequestBody InstruccionDTO instruccionDTO) throws URISyntaxException {
        log.debug("REST request to save Instruccion : {}", instruccionDTO);
        if (instruccionDTO.getId() != null) {
            throw new BadRequestAlertException("A new instruccion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstruccionDTO result = instruccionService.save(instruccionDTO);
        return ResponseEntity.created(new URI("/api/instruccions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /instruccions} : Updates an existing instruccion.
     *
     * @param instruccionDTO the instruccionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instruccionDTO,
     * or with status {@code 400 (Bad Request)} if the instruccionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instruccionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/instruccions")
    public ResponseEntity<InstruccionDTO> updateInstruccion(@RequestBody InstruccionDTO instruccionDTO) throws URISyntaxException {
        log.debug("REST request to update Instruccion : {}", instruccionDTO);
        if (instruccionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstruccionDTO result = instruccionService.save(instruccionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instruccionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /instruccions} : get all the instruccions.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instruccions in body.
     */
    @GetMapping("/instruccions")
    public ResponseEntity<List<InstruccionDTO>> getAllInstruccions(Pageable pageable) {
        log.debug("REST request to get a page of Instruccions");
        Page<InstruccionDTO> page = instruccionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /instruccions/:id} : get the "id" instruccion.
     *
     * @param id the id of the instruccionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instruccionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/instruccions/{id}")
    public ResponseEntity<InstruccionDTO> getInstruccion(@PathVariable String id) {
        log.debug("REST request to get Instruccion : {}", id);
        Optional<InstruccionDTO> instruccionDTO = instruccionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instruccionDTO);
    }

    /**
     * {@code DELETE  /instruccions/:id} : delete the "id" instruccion.
     *
     * @param id the id of the instruccionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/instruccions/{id}")
    public ResponseEntity<Void> deleteInstruccion(@PathVariable String id) {
        log.debug("REST request to delete Instruccion : {}", id);
        instruccionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
