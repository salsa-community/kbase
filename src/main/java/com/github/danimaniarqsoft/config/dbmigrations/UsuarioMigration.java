package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.danimaniarqsoft.domain.Authority;
import com.github.danimaniarqsoft.domain.User;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.security.AuthoritiesConstants;

import com.github.mongobee.changeset.ChangeLog;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "003")
public class UsuarioMigration {

    // @ChangeSet(order = "01", author = "arquitectura", id =
    // "01-addMensajeErrores")
    public void addUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String errorUrl = "https://raw.githubusercontent.com/danimaniarqsoft/kbase/develop/src/main/resources/dbmigration/errores.json";
        ObjectMapper mapper = new ObjectMapper();
        ErrorMigration[] model = mapper.readValue(new URL(errorUrl), ErrorMigration[].class);
        for (ErrorMigration error : model) {
            
        }
    }
}
