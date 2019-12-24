package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.github.danimaniarqsoft.domain.Instruccion;
import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.domain.Paso;
import com.github.danimaniarqsoft.domain.Usuario;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.service.mapper.migration.ErrorMigrationMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;


/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "002")
public class MensajeErroresMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-addMensajeErrores")
    public void addErrors(MongoTemplate mongoTemplate, ErrorMigrationMapper migrationMapper)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String errorUrl = "https://raw.githubusercontent.com/danimaniarqsoft/kbase/develop/src/main/resources/dbmigration/errores.json";
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("ADD ERRORS");
        ErrorMigration[] model = mapper.readValue(new URL(errorUrl), ErrorMigration[].class);
        for (ErrorMigration errorMigration : model) {
            System.out.println("************************");
            MensajeError mensajeError = migrationMapper.toEntity(errorMigration);
            System.out.println(mensajeError.getClave());
            System.out.println(mensajeError.getDesc());
            Instruccion instruccion = mensajeError.getInstruccion();
            for (Paso paso : instruccion.getPasos()) {
                System.out.println(paso.getPaso());
                System.out.println(paso.getDesc());
            }
            System.out.println("************************");
        }
    }

    @ChangeSet(order = "02", author = "arquitectura", id = "02-addDefaultUsuario")
    public void addDefaultUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        System.out.println("ADD Usuario default");
        Usuario usuario = new Usuario();
        usuario.setNombre("ITZIA MARIA DEL CARMEN");
        usuario.setPrimerApellido("SANCHEZ");
        usuario.setSegundoApellido("MENDEZ");
        usuario.setRfc("SAMI860101RH10");
        mongoTemplate.save(usuario);
    }
}
