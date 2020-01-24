package com.github.danimaniarqsoft.service.mapper.migration;

import com.github.danimaniarqsoft.migration.Paso;

import org.mapstruct.*;

/**
 *
 */
@Mapper(componentModel = "spring", uses = {})
public interface PasoMigrationMapper {

    @Mapping(source = "paso.$numberInt", target = "paso")
    com.github.danimaniarqsoft.domain.Paso toEntity(Paso paso);

}
