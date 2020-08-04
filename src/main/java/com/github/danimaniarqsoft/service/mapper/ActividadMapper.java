package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.ActividadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Actividad} and its DTO {@link ActividadDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventoMapper.class})
public interface ActividadMapper extends EntityMapper<ActividadDTO, Actividad> {

    ActividadDTO toDto(Actividad actividad);
       
    Actividad toEntity(ActividadDTO actividadDTO);

    default Actividad fromId(String id) {
        if (id == null) {
            return null;
        }
        Actividad actividad = new Actividad();
        actividad.setId(id);
        return actividad;
    }
}
