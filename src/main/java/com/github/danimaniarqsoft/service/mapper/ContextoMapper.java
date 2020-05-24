package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.ContextoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contexto} and its DTO {@link ContextoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContextoMapper extends EntityMapper<ContextoDTO, Contexto> {



    default Contexto fromId(String id) {
        if (id == null) {
            return null;
        }
        Contexto contexto = new Contexto();
        contexto.setId(id);
        return contexto;
    }
}
