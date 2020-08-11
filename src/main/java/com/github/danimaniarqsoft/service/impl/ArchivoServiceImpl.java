package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.ArchivoService;
import com.github.danimaniarqsoft.domain.Archivo;
import com.github.danimaniarqsoft.repository.ArchivoRepository;
import com.github.danimaniarqsoft.service.dto.ArchivoDTO;
import com.github.danimaniarqsoft.service.mapper.ArchivoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Archivo}.
 */
@Service
public class ArchivoServiceImpl implements ArchivoService {

    private final Logger log = LoggerFactory.getLogger(ArchivoServiceImpl.class);

    private final ArchivoRepository archivoRepository;

    private final ArchivoMapper archivoMapper;

    public ArchivoServiceImpl(ArchivoRepository archivoRepository, ArchivoMapper archivoMapper) {
        this.archivoRepository = archivoRepository;
        this.archivoMapper = archivoMapper;
    }

    /**
     * Save a archivo.
     *
     * @param archivoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ArchivoDTO save(ArchivoDTO archivoDTO) {
        log.debug("Request to save Archivo : {}", archivoDTO);
        Archivo archivo = archivoMapper.toEntity(archivoDTO);
        archivo = archivoRepository.save(archivo);
        return archivoMapper.toDto(archivo);
    }

    /**
     * Get all the archivos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ArchivoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Archivos");
        return archivoRepository.findAll(pageable)
            .map(archivoMapper::toDto);
    }


    /**
     * Get one archivo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ArchivoDTO> findOne(String id) {
        log.debug("Request to get Archivo : {}", id);
        return archivoRepository.findById(id)
            .map(archivoMapper::toDto);
    }

    /**
     * Delete the archivo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Archivo : {}", id);
        archivoRepository.deleteById(id);
    }
}
