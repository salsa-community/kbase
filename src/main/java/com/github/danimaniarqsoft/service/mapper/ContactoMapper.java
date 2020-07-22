package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.ContactoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contacto} and its DTO {@link ContactoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactoMapper extends EntityMapper<ContactoDTO, Contacto> {



    default Contacto fromId(String id) {
        if (id == null) {
            return null;
        }
        Contacto contacto = new Contacto();
        contacto.setId(id);
        return contacto;
    }
}
