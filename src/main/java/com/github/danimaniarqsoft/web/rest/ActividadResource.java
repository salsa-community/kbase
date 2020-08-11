package com.github.danimaniarqsoft.web.rest;

import com.github.danimaniarqsoft.service.ActividadService;
import com.github.danimaniarqsoft.web.rest.errors.BadRequestAlertException;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;
import com.github.danimaniarqsoft.service.dto.Filtro;
import com.github.danimaniarqsoft.service.dto.ReporteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<List<ActividadDTO>> getAllActividads(
            @RequestParam(required = false) List<String> contexto,
            @RequestParam(required = false) List<String> evento,
            @RequestParam(required = false) Instant fechaInicio, 
            @RequestParam(required = false) Instant fechaFin, 
            Pageable pageable) {
        log.debug("contextos {}", contexto);
        log.debug("eventos {}", evento);
        log.debug("fechaInicio {}", fechaInicio);
        log.debug("fechaFin {}", fechaFin);
        Page<ActividadDTO> page = actividadService.findByFilter( resolveFiltro(contexto, evento, fechaInicio, fechaFin), pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/actividads/reportes/excel")
    public ResponseEntity<StreamingResponseBody> getReporte(
            @RequestParam(required = false) List<String> contexto,
            @RequestParam(required = false) List<String> evento,
            @RequestParam(required = false) Instant fechaInicio, 
            @RequestParam(required = false) Instant fechaFin, 
            Pageable pageable) throws IOException {
        log.debug("contextos {}", contexto);
        log.debug("eventos {}", evento);
        log.debug("fechaInicio {}", fechaInicio);

        Workbook wb = actividadService.findByFilterAllReport(resolveFiltro(contexto, evento, fechaInicio, fechaFin));
    
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", "reporte_plenaria.xlsx"))
                .header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body((os) -> {
                    wb.write(os);
                });
    }
    
    private Filtro resolveFiltro(
            @RequestParam(required = false) List<String> contexto,
            @RequestParam(required = false) List<String> evento,
            @RequestParam(required = false) Instant fechaInicio, 
            @RequestParam(required = false) Instant fechaFin){
        Filtro filtro = new Filtro();
        filtro.setContexto(contexto);
        filtro.setEvento(evento);
        filtro.setFechaInicio(fechaInicio);
        filtro.setFechaFin(fechaFin);
        return filtro;
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
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
    
    /**
     * {@code GET  /actividads/reportes} : get the "reporte.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the actividadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/actividads/reportes")
    public ResponseEntity<ReporteDTO> getReporte() {
        ReporteDTO reporte = actividadService.loadReporte();
        Optional<ReporteDTO> reporteDTO = Optional.of(reporte);
        return ResponseUtil.wrapOrNotFound(reporteDTO);
    }
}
