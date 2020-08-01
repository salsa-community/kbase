package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.ActividadService;
import com.github.danimaniarqsoft.domain.Actividad;
import com.github.danimaniarqsoft.repository.ActividadRepository;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;
import com.github.danimaniarqsoft.service.mapper.ActividadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Actividad}.
 */
@Service
public class ActividadServiceImpl implements ActividadService {

    private final Logger log = LoggerFactory.getLogger(ActividadServiceImpl.class);

    private final ActividadRepository actividadRepository;

    private final ActividadMapper actividadMapper;

    public ActividadServiceImpl(ActividadRepository actividadRepository, ActividadMapper actividadMapper) {
        this.actividadRepository = actividadRepository;
        this.actividadMapper = actividadMapper;
    }

    /**
     * Save a actividad.
     *
     * @param actividadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ActividadDTO save(ActividadDTO actividadDTO) {
        log.debug("Request to save Actividad : {}", actividadDTO);
        Actividad actividad = actividadMapper.toEntity(actividadDTO);
        actividad = actividadRepository.save(actividad);
        return actividadMapper.toDto(actividad);
    }

    /**
     * Get all the actividads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ActividadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actividads");
        return actividadRepository.findAll(pageable)
            .map(actividadMapper::toDto);
    }


    /**
     * Get one actividad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ActividadDTO> findOne(String id) {
        log.debug("Request to get Actividad : {}", id);
        return actividadRepository.findById(id)
            .map(actividadMapper::toDto);
    }

    /**
     * Delete the actividad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Actividad : {}", id);
        actividadRepository.deleteById(id);
    }
}
