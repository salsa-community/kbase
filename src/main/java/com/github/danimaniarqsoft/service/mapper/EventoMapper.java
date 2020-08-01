package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.EventoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evento} and its DTO {@link EventoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventoMapper extends EntityMapper<EventoDTO, Evento> {



    default Evento fromId(String id) {
        if (id == null) {
            return null;
        }
        Evento evento = new Evento();
        evento.setId(id);
        return evento;
    }
}
