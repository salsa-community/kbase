package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.danimaniarqsoft.domain.Authority;
import com.github.danimaniarqsoft.domain.Contexto;
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
@ChangeLog(order = "005")
public class ContextoMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-Contextos")
    public void addContextos(MongoTemplate mongoTemplate) {
        Contexto redcofidi = new Contexto();
        redcofidi.setNombre("Red Cofidi");
        redcofidi.clave("RedCofidi");
        redcofidi.desc("Contexto para el sistema de Red Cofidi");
        mongoTemplate.save(redcofidi);

        Contexto pacWeb = new Contexto();
        pacWeb.setNombre("Pac Web");
        pacWeb.clave("PacWeb");
        pacWeb.desc("Contexto para el sistema de PacWeb");
        mongoTemplate.save(pacWeb);
    }
}
