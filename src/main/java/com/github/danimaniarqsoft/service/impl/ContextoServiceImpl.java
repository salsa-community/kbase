package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.ContextoService;
import com.github.danimaniarqsoft.domain.Contexto;
import com.github.danimaniarqsoft.repository.ContextoRepository;
import com.github.danimaniarqsoft.service.dto.ContextoDTO;
import com.github.danimaniarqsoft.service.mapper.ContextoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Contexto}.
 */
@Service
public class ContextoServiceImpl implements ContextoService {

    private final Logger log = LoggerFactory.getLogger(ContextoServiceImpl.class);

    private final ContextoRepository contextoRepository;

    private final ContextoMapper contextoMapper;

    public ContextoServiceImpl(ContextoRepository contextoRepository, ContextoMapper contextoMapper) {
        this.contextoRepository = contextoRepository;
        this.contextoMapper = contextoMapper;
    }

    /**
     * Save a contexto.
     *
     * @param contextoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContextoDTO save(ContextoDTO contextoDTO) {
        log.debug("Request to save Contexto : {}", contextoDTO);
        Contexto contexto = contextoMapper.toEntity(contextoDTO);
        contexto = contextoRepository.save(contexto);
        return contextoMapper.toDto(contexto);
    }

    /**
     * Get all the contextos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ContextoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contextos");
        return contextoRepository.findAll(pageable).map(contextoMapper::toDto);
    }

    /**
     * Get one contexto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ContextoDTO> findOne(String id) {
        log.debug("Request to get Contexto : {}", id);
        return contextoRepository.findById(id).map(contextoMapper::toDto);
    }

    /**
     * Delete the contexto by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Contexto : {}", id);
        contextoRepository.deleteById(id);
    }

    @Override
    public List<ContextoDTO> findAll() {
        return contextoRepository.findAll().stream().map(contextoMapper::toDto).collect(Collectors.toList());
    }
}
