package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.InstruccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Instruccion} and its DTO {@link InstruccionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstruccionMapper extends EntityMapper<InstruccionDTO, Instruccion> {


}
