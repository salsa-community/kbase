package com.github.danimaniarqsoft.service;

import com.github.danimaniarqsoft.service.dto.ActividadDTO;
import com.github.danimaniarqsoft.service.dto.Filtro;
import com.github.danimaniarqsoft.service.dto.ReporteDTO;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.github.danimaniarqsoft.domain.Actividad}.
 */
public interface ActividadService {

    /**
     * Save a actividad.
     *
     * @param actividadDTO the entity to save.
     * @return the persisted entity.
     */
    ActividadDTO save(ActividadDTO actividadDTO);

    /**
     * Get all the actividads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ActividadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" actividad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ActividadDTO> findOne(String id);

    /**
     * Delete the "id" actividad.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    ReporteDTO loadReporte();


    Page<ActividadDTO> findByFilter(Filtro filtro, Pageable pageable);
    
    List<ActividadDTO> findAllByFilter(Filtro filtro, Pageable pageable);

    Workbook findByFilterAllReport(Filtro filtro) throws IOException;
}
