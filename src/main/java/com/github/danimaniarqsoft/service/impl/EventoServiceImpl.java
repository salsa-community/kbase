package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.EventoService;
import com.github.danimaniarqsoft.domain.Evento;
import com.github.danimaniarqsoft.repository.EventoRepository;
import com.github.danimaniarqsoft.service.dto.EventoDTO;
import com.github.danimaniarqsoft.service.mapper.EventoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Evento}.
 */
@Service
public class EventoServiceImpl implements EventoService {

    private final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;

    public EventoServiceImpl(EventoRepository eventoRepository, EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
    }

    /**
     * Save a evento.
     *
     * @param eventoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventoDTO save(EventoDTO eventoDTO) {
        log.debug("Request to save Evento : {}", eventoDTO);
        Evento evento = eventoMapper.toEntity(eventoDTO);
        evento = eventoRepository.save(evento);
        return eventoMapper.toDto(evento);
    }

    /**
     * Get all the eventos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<EventoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Eventos");
        return eventoRepository.findAll(pageable)
            .map(eventoMapper::toDto);
    }


    /**
     * Get one evento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<EventoDTO> findOne(String id) {
        log.debug("Request to get Evento : {}", id);
        return eventoRepository.findById(id)
            .map(eventoMapper::toDto);
    }

    /**
     * Delete the evento by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Evento : {}", id);
        eventoRepository.deleteById(id);
    }
}
