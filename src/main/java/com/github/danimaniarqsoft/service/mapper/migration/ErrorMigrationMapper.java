package com.github.danimaniarqsoft.service.mapper.migration;

import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.migration.ErrorMigration;

import org.mapstruct.*;

/**
 *
 */
@Mapper(componentModel = "spring", uses = { InstruccionesMigrationMapper.class })
public interface ErrorMigrationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "instrucciones", target = "instruccion")
    MensajeError toEntity(ErrorMigration from);

}
