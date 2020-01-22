package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.danimaniarqsoft.domain.Authority;
import com.github.danimaniarqsoft.domain.User;
import com.github.danimaniarqsoft.domain.Usuario;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.security.AuthoritiesConstants;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "003")
public class UsuarioMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-UsuariosDemo")
    public void addUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNombre("Admin");
        usuario.setPrimerApellido("main");
        usuario.setSegundoApellido("root");
        usuario.setRfc("MSE061107IA8");
        mongoTemplate.save(usuario);
    }
}
