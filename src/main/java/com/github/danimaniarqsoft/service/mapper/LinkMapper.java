package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.LinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Link} and its DTO {@link LinkDTO}.
 */
@Mapper(componentModel = "spring", uses = {MensajeErrorMapper.class})
public interface LinkMapper extends EntityMapper<LinkDTO, Link> {

}
