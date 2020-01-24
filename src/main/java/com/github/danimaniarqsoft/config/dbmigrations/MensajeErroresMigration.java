package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.danimaniarqsoft.domain.Instruccion;
import com.github.danimaniarqsoft.domain.Link;
import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.domain.Paso;
import com.github.danimaniarqsoft.domain.Usuario;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.migration.Instrucciones;
import com.github.danimaniarqsoft.service.mapper.MensajeErrorMapper;
import com.github.danimaniarqsoft.service.mapper.migration.ErrorMigrationMapper;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "002")
public class MensajeErroresMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-addMensajeErrores")
    public void addErrors(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String errorUrl = "https://raw.githubusercontent.com/danimaniarqsoft/kbase/develop/src/main/resources/dbmigration/errores.json";
        ObjectMapper mapper = new ObjectMapper();
        ErrorMigration[] model = mapper.readValue(new URL(errorUrl), ErrorMigration[].class);
        for (ErrorMigration errorMigration : model) {
            MensajeError mensajeError = toEntity(errorMigration);
            mongoTemplate.save(mensajeError);
        }
    }

    @ChangeSet(order = "02", author = "arquitectura", id = "02-addDefaultUsuario")
    public void addDefaultUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        Usuario usuario = new Usuario();
        usuario.setNombre("ITZIA MARIA DEL CARMEN");
        usuario.setPrimerApellido("SANCHEZ");
        usuario.setSegundoApellido("MENDEZ");
        usuario.setRfc("SAMI860101RH10");
        mongoTemplate.save(usuario);
    }

    private static MensajeError toEntity(ErrorMigration from) {
        MensajeError to = new MensajeError();
        to.setClave(from.getClave());
        to.setDesc(from.getDesc());
        to.setLinks(toLink(from.getLinks()));
        to.setInstruccion(toInstruccion(from.getInstrucciones()));
        return to;
    }

    private static List<Link> toLink(List<com.github.danimaniarqsoft.migration.Link> from) {
        if (from == null || from.isEmpty()) {
            return Collections.emptyList();
        }
        List<Link> toList = new ArrayList<>();
        for (com.github.danimaniarqsoft.migration.Link temp : from) {
            Link link = new Link();
            link.setUrl(temp.getUrl());
            toList.add(link);
        }
        return toList;
    }

    private static Instruccion toInstruccion(Instrucciones from) {
        Instruccion to = new Instruccion();
        to.setDesc(from.getDesc());
        to.setPasos(toPasos(from.getPasos()));
        return to;
    }

    private static List<Paso> toPasos(List<com.github.danimaniarqsoft.migration.Paso> from) {
        if (from == null || from.isEmpty()) {
            return Collections.emptyList();
        }
        List<Paso> toList = new ArrayList<>();
        for (com.github.danimaniarqsoft.migration.Paso temp : from) {
            Paso paso = new Paso();
            paso.setDesc(temp.getDesc());
            paso.setPaso(Integer.valueOf(temp.getPaso().get$numberInt()));
            toList.add(paso);
        }
        return toList;
    }

}
