package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.MensajeErrorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MensajeError} and its DTO {@link MensajeErrorDTO}.
 */
@Mapper(componentModel = "spring", uses = {InstruccionMapper.class})
public interface MensajeErrorMapper extends EntityMapper<MensajeErrorDTO, MensajeError> {

}
