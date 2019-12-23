package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.danimaniarqsoft.domain.Authority;
import com.github.danimaniarqsoft.domain.Instruccion;
import com.github.danimaniarqsoft.domain.User;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.security.AuthoritiesConstants;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.List;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "002")
public class MensajeErroresMigration {

    // @ChangeSet(order = "01", author = "arquitectura", id =
    // "01-addMensajeErrores")
    public void addErrors(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String errorUrl = "https://raw.githubusercontent.com/danimaniarqsoft/kbase/develop/src/main/resources/dbmigration/errores.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(new URL(errorUrl), ErrorMigration.class);
    }

    public static void main(String[] args)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String errorUrl = "https://raw.githubusercontent.com/danimaniarqsoft/kbase/develop/src/main/resources/dbmigration/errores.json";
        ObjectMapper mapper = new ObjectMapper();
        ErrorMigration[] model = mapper.readValue(new URL(errorUrl), ErrorMigration[].class);
        for (ErrorMigration error : model) {
            System.out.println(error.getClave());
        }
        
    }
}
