package com.github.danimaniarqsoft.service.impl;

import com.github.danimaniarqsoft.service.LinkService;
import com.github.danimaniarqsoft.domain.Link;
import com.github.danimaniarqsoft.repository.LinkRepository;
import com.github.danimaniarqsoft.service.dto.LinkDTO;
import com.github.danimaniarqsoft.service.mapper.LinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Link}.
 */
@Service
public class LinkServiceImpl implements LinkService {

    private final Logger log = LoggerFactory.getLogger(LinkServiceImpl.class);

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;

    public LinkServiceImpl(LinkRepository linkRepository, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.linkMapper = linkMapper;
    }

    /**
     * Save a link.
     *
     * @param linkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LinkDTO save(LinkDTO linkDTO) {
        log.debug("Request to save Link : {}", linkDTO);
        Link link = linkMapper.toEntity(linkDTO);
        link = linkRepository.save(link);
        return linkMapper.toDto(link);
    }

    /**
     * Get all the links.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<LinkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Links");
        return linkRepository.findAll(pageable)
            .map(linkMapper::toDto);
    }


    /**
     * Get one link by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<LinkDTO> findOne(String id) {
        log.debug("Request to get Link : {}", id);
        return linkRepository.findById(id)
            .map(linkMapper::toDto);
    }

    /**
     * Delete the link by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Link : {}", id);
        linkRepository.deleteById(id);
    }
}
