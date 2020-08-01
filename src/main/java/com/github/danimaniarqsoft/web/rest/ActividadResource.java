package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.ActividadService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;

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
 * REST controller for managing {@link com.github.danimaniarqsoft.domain.Actividad}.
 */
@RestController
@RequestMapping("/api")
public class ActividadResource {

    private final Logger log = LoggerFactory.getLogger(ActividadResource.class);

    private static final String ENTITY_NAME = "actividad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActividadService actividadService;

    public ActividadResource(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * {@code POST  /actividads} : Create a new actividad.
     *
     * @param actividadDTO the actividadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new actividadDTO, or with status {@code 400 (Bad Request)} if the actividad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/actividads")
    public ResponseEntity<ActividadDTO> createActividad(@RequestBody ActividadDTO actividadDTO) throws URISyntaxException {
        log.debug("REST request to save Actividad : {}", actividadDTO);
        if (actividadDTO.getId() != null) {
            throw new BadRequestAlertException("A new actividad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActividadDTO result = actividadService.save(actividadDTO);
        return ResponseEntity.created(new URI("/api/actividads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /actividads} : Updates an existing actividad.
     *
     * @param actividadDTO the actividadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated actividadDTO,
     * or with status {@code 400 (Bad Request)} if the actividadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the actividadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/actividads")
    public ResponseEntity<ActividadDTO> updateActividad(@RequestBody ActividadDTO actividadDTO) throws URISyntaxException {
        log.debug("REST request to update Actividad : {}", actividadDTO);
        if (actividadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActividadDTO result = actividadService.save(actividadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, actividadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /actividads} : get all the actividads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of actividads in body.
     */
    @GetMapping("/actividads")
    public ResponseEntity<List<ActividadDTO>> getAllActividads(Pageable pageable) {
        log.debug("REST request to get a page of Actividads");
        Page<ActividadDTO> page = actividadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /actividads/:id} : get the "id" actividad.
     *
     * @param id the id of the actividadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actividadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/actividads/{id}")
    public ResponseEntity<ActividadDTO> getActividad(@PathVariable String id) {
        log.debug("REST request to get Actividad : {}", id);
        Optional<ActividadDTO> actividadDTO = actividadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actividadDTO);
    }

    /**
     * {@code DELETE  /actividads/:id} : delete the "id" actividad.
     *
     * @param id the id of the actividadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/actividads/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable String id) {
        log.debug("REST request to delete Actividad : {}", id);
        actividadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
