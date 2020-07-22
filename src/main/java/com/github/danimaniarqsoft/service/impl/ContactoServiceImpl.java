package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.ContactoService;
import com.github.danimaniarqsoft.domain.Contacto;
import com.github.danimaniarqsoft.repository.ContactoRepository;
import com.github.danimaniarqsoft.service.dto.ContactoDTO;
import com.github.danimaniarqsoft.service.mapper.ContactoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Contacto}.
 */
@Service
public class ContactoServiceImpl implements ContactoService {

    private final Logger log = LoggerFactory.getLogger(ContactoServiceImpl.class);

    private final ContactoRepository contactoRepository;

    private final ContactoMapper contactoMapper;

    public ContactoServiceImpl(ContactoRepository contactoRepository, ContactoMapper contactoMapper) {
        this.contactoRepository = contactoRepository;
        this.contactoMapper = contactoMapper;
    }

    /**
     * Save a contacto.
     *
     * @param contactoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContactoDTO save(ContactoDTO contactoDTO) {
        log.debug("Request to save Contacto : {}", contactoDTO);
        Contacto contacto = contactoMapper.toEntity(contactoDTO);
        contacto = contactoRepository.save(contacto);
        return contactoMapper.toDto(contacto);
    }

    /**
     * Get all the contactos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<ContactoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contactos");
        return contactoRepository.findAll(pageable)
            .map(contactoMapper::toDto);
    }


    /**
     * Get one contacto by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ContactoDTO> findOne(String id) {
        log.debug("Request to get Contacto : {}", id);
        return contactoRepository.findById(id)
            .map(contactoMapper::toDto);
    }

    /**
     * Delete the contacto by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Contacto : {}", id);
        contactoRepository.deleteById(id);
    }
}
