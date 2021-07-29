package com.github.danimaniarqsoft.service.impl;

import java.util.Optional;

import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.repository.MensajeErrorRepository;
import com.github.danimaniarqsoft.service.BotClient;
import com.github.danimaniarqsoft.service.MensajeErrorService;
import com.github.danimaniarqsoft.service.dto.MensajeErrorDTO;
import com.github.danimaniarqsoft.service.mapper.MensajeErrorMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link MensajeError}.
 */
@Service
public class MensajeErrorServiceImpl implements MensajeErrorService {

    private final Logger log = LoggerFactory.getLogger(MensajeErrorServiceImpl.class);

    private final MensajeErrorRepository mensajeErrorRepository;

    private final MensajeErrorMapper mensajeErrorMapper;

    @Autowired
    private BotClient botClient;

    public MensajeErrorServiceImpl(MensajeErrorRepository mensajeErrorRepository, MensajeErrorMapper mensajeErrorMapper) {
        this.mensajeErrorRepository = mensajeErrorRepository;
        this.mensajeErrorMapper = mensajeErrorMapper;
    }

    /**
     * Save a mensajeError.
     *
     * @param mensajeErrorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MensajeErrorDTO save(MensajeError mensajeErrorDTO) {
        botClient.cleanCache();
        log.debug("Request to save MensajeError : {}", mensajeErrorDTO);
        mensajeErrorDTO.setClave(mensajeErrorDTO.getClave().toUpperCase());
        mensajeErrorDTO = mensajeErrorRepository.save(mensajeErrorDTO);
        return mensajeErrorMapper.toDto(mensajeErrorDTO);
    }

    /**
     * Get all the mensajeErrors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<MensajeError> findAll(String codigo, Pageable pageable) {
        log.debug("Request to get all MensajeErrors");
        if (codigo != null && !codigo.isEmpty() && !codigo.equals("null")) {
            return mensajeErrorRepository.findByClaveLikeIgnoreCase(codigo, pageable);
        } else {
            return mensajeErrorRepository.findAll(pageable);
        }
    }


    /**
     * Get one mensajeError by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<MensajeError> findOne(String id) {
        log.debug("Request to get MensajeError : {}", id);
        return mensajeErrorRepository.findById(id);
    }

    @Override
    public Optional<MensajeError> findOneByClave(String clave) {
        log.debug("Request to get MensajeError : {}", clave);
        return mensajeErrorRepository.findByClave(clave.toUpperCase());
    }

    /**
     * Delete the mensajeError by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete MensajeError : {}", id);
        mensajeErrorRepository.deleteById(id);
    }
}
