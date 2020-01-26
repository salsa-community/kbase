package com.github.danimaniarqsoft.config.dbmigrations;

import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.domain.Usuario;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "002")
public class MensajeErroresMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-addMensajeErrores")
    public void addErrors(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        MigrationUtils.saveMensajesErrores("202026011248_create_errores.json", mongoTemplate);
    }

    @ChangeSet(order = "02", author = "arquitectura", id = "02-addDefaultUsuario")
    public void addDefaultUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        mongoTemplate.save(new Usuario().nombre("ITZIA MARIA DEL CARMEN").primerApellido("SANCHEZ")
                .segundoApellido("MENDEZ").rfc("SAMI860101RH10"));
    }

    @ChangeSet(order = "03", author = "arquitectura", id = "03-addAtebUsuario")
    public void addAtebUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        mongoTemplate.save(new Usuario().nombre("ATEB").rfc("ASE0209252Q1"));
    }

    @ChangeSet(order = "04", author = "arquitectura", id = "04-deleteAllMensajeErrores")
    public void deleteAllMensajeErrores(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        mongoTemplate.dropCollection(MensajeError.class);
    }

    @ChangeSet(order = "05", author = "arquitectura", id = "05-updateMensajeErrores")
    public void updateMensajeErrores(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        MigrationUtils.saveMensajesErrores("202026011333_update_errores.json", mongoTemplate);
    }
}
