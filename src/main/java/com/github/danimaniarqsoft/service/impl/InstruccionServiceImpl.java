package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.InstruccionService;
import com.github.danimaniarqsoft.domain.Instruccion;
import com.github.danimaniarqsoft.repository.InstruccionRepository;
import com.github.danimaniarqsoft.service.dto.InstruccionDTO;
import com.github.danimaniarqsoft.service.mapper.InstruccionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Instruccion}.
 */
@Service
public class InstruccionServiceImpl implements InstruccionService {

    private final Logger log = LoggerFactory.getLogger(InstruccionServiceImpl.class);

    private final InstruccionRepository instruccionRepository;

    private final InstruccionMapper instruccionMapper;

    public InstruccionServiceImpl(InstruccionRepository instruccionRepository, InstruccionMapper instruccionMapper) {
        this.instruccionRepository = instruccionRepository;
        this.instruccionMapper = instruccionMapper;
    }

    /**
     * Save a instruccion.
     *
     * @param instruccionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InstruccionDTO save(InstruccionDTO instruccionDTO) {
        log.debug("Request to save Instruccion : {}", instruccionDTO);
        Instruccion instruccion = instruccionMapper.toEntity(instruccionDTO);
        instruccion = instruccionRepository.save(instruccion);
        return instruccionMapper.toDto(instruccion);
    }

    /**
     * Get all the instruccions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<InstruccionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Instruccions");
        return instruccionRepository.findAll(pageable)
            .map(instruccionMapper::toDto);
    }


    /**
     * Get one instruccion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<InstruccionDTO> findOne(String id) {
        log.debug("Request to get Instruccion : {}", id);
        return instruccionRepository.findById(id)
            .map(instruccionMapper::toDto);
    }

    /**
     * Delete the instruccion by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Instruccion : {}", id);
        instruccionRepository.deleteById(id);
    }
}
