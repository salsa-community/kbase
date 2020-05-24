package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.UnknowIntentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnknowIntents} and its DTO {@link UnknowIntentsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UnknowIntentsMapper extends EntityMapper<UnknowIntentsDTO, UnknowIntents> {



    default UnknowIntents fromId(String id) {
        if (id == null) {
            return null;
        }
        UnknowIntents unknowIntents = new UnknowIntents();
        unknowIntents.setId(id);
        return unknowIntents;
    }
}
