package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.ArchivoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Archivo} and its DTO {@link ArchivoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArchivoMapper extends EntityMapper<ArchivoDTO, Archivo> {



    default Archivo fromId(String id) {
        if (id == null) {
            return null;
        }
        Archivo archivo = new Archivo();
        archivo.setId(id);
        return archivo;
    }
}
