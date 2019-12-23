package com.github.danimaniarqsoft.service.mapper.migration;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.migration.Instrucciones;
import com.github.danimaniarqsoft.service.dto.InstruccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Instruccion} and its DTO {@link InstruccionDTO}.
 */
@Mapper(componentModel = "spring", uses = { PasoMigrationMapper.class})
public interface InstruccionesMigrationMapper {

    Instruccion toEntity(Instrucciones from);

}
