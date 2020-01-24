package com.github.danimaniarqsoft.service.mapper;

import com.github.danimaniarqsoft.domain.*;
import com.github.danimaniarqsoft.service.dto.UsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usuario} and its DTO {@link UsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {



    default Usuario fromId(String id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
