package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.PasoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paso} and its DTO {@link PasoDTO}.
 */
@Mapper(componentModel = "spring", uses = {InstruccionMapper.class})
public interface PasoMapper extends EntityMapper<PasoDTO, Paso> {



}
