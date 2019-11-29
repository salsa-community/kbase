package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.PasoService;
import com.github.danimaniarqsoft.domain.Paso;
import com.github.danimaniarqsoft.repository.PasoRepository;
import com.github.danimaniarqsoft.service.dto.PasoDTO;
import com.github.danimaniarqsoft.service.mapper.PasoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Paso}.
 */
@Service
public class PasoServiceImpl implements PasoService {

    private final Logger log = LoggerFactory.getLogger(PasoServiceImpl.class);

    private final PasoRepository pasoRepository;

    private final PasoMapper pasoMapper;

    public PasoServiceImpl(PasoRepository pasoRepository, PasoMapper pasoMapper) {
        this.pasoRepository = pasoRepository;
        this.pasoMapper = pasoMapper;
    }

    /**
     * Save a paso.
     *
     * @param pasoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PasoDTO save(PasoDTO pasoDTO) {
        log.debug("Request to save Paso : {}", pasoDTO);
        Paso paso = pasoMapper.toEntity(pasoDTO);
        paso = pasoRepository.save(paso);
        return pasoMapper.toDto(paso);
    }

    /**
     * Get all the pasos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<PasoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pasos");
        return pasoRepository.findAll(pageable)
            .map(pasoMapper::toDto);
    }


    /**
     * Get one paso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<PasoDTO> findOne(String id) {
        log.debug("Request to get Paso : {}", id);
        return pasoRepository.findById(id)
            .map(pasoMapper::toDto);
    }

    /**
     * Delete the paso by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Paso : {}", id);
        pasoRepository.deleteById(id);
    }
}
