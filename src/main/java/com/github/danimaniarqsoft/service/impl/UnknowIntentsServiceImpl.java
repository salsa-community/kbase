package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.UnknowIntentsService;
import com.github.danimaniarqsoft.domain.UnknowIntents;
import com.github.danimaniarqsoft.repository.UnknowIntentsRepository;
import com.github.danimaniarqsoft.service.dto.UnknowIntentsDTO;
import com.github.danimaniarqsoft.service.mapper.UnknowIntentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnknowIntents}.
 */
@Service
public class UnknowIntentsServiceImpl implements UnknowIntentsService {

    private final Logger log = LoggerFactory.getLogger(UnknowIntentsServiceImpl.class);

    private final UnknowIntentsRepository unknowIntentsRepository;

    private final UnknowIntentsMapper unknowIntentsMapper;

    public UnknowIntentsServiceImpl(UnknowIntentsRepository unknowIntentsRepository, UnknowIntentsMapper unknowIntentsMapper) {
        this.unknowIntentsRepository = unknowIntentsRepository;
        this.unknowIntentsMapper = unknowIntentsMapper;
    }

    /**
     * Save a unknowIntents.
     *
     * @param unknowIntentsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnknowIntentsDTO save(UnknowIntentsDTO unknowIntentsDTO) {
        log.debug("Request to save UnknowIntents : {}", unknowIntentsDTO);
        UnknowIntents unknowIntents = unknowIntentsMapper.toEntity(unknowIntentsDTO);
        unknowIntents = unknowIntentsRepository.save(unknowIntents);
        return unknowIntentsMapper.toDto(unknowIntents);
    }

    /**
     * Get all the unknowIntents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<UnknowIntentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnknowIntents");
        return unknowIntentsRepository.findAll(pageable)
            .map(unknowIntentsMapper::toDto);
    }


    /**
     * Get one unknowIntents by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<UnknowIntentsDTO> findOne(String id) {
        log.debug("Request to get UnknowIntents : {}", id);
        return unknowIntentsRepository.findById(id)
            .map(unknowIntentsMapper::toDto);
    }

    /**
     * Delete the unknowIntents by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete UnknowIntents : {}", id);
        unknowIntentsRepository.deleteById(id);
    }
}
