package com.github.danimaniarqsoft.config.dbmigrations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danimaniarqsoft.domain.Instruccion;
import com.github.danimaniarqsoft.domain.Link;
import com.github.danimaniarqsoft.domain.MensajeError;
import com.github.danimaniarqsoft.domain.Paso;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.migration.Instrucciones;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * MigrationUtils
 */
public class MigrationUtils {

    private MigrationUtils() {

    }

    public static void saveMensajesErrores(String fileName, MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, IOException {
        Resource resource = new ClassPathResource("dbmigration/" + fileName);
        InputStream inputStream = resource.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ErrorMigration[] model = mapper.readValue(inputStream, ErrorMigration[].class);
        for (ErrorMigration errorMigration : model) {
            MensajeError mensajeError = MigrationUtils.toEntity(errorMigration);
            mongoTemplate.save(mensajeError);
        }
    }

    public static MensajeError toEntity(ErrorMigration from) {
        MensajeError to = new MensajeError();
        to.setOrden(from.getOrden());
        to.setTipo(from.getTipo());
        to.setClave(from.getClave());
        to.setDesc(from.getDesc());
        to.setLinks(toLink(from.getLinks()));
        to.setInstruccion(toInstruccion(from.getInstrucciones()));
        return to;
    }

    public static List<Link> toLink(List<com.github.danimaniarqsoft.migration.Link> from) {
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

    public static Instruccion toInstruccion(Instrucciones from) {
        Instruccion to = new Instruccion();
        to.setDesc(from.getDesc());
        to.setPasos(toPasos(from.getPasos()));
        return to;
    }

    public static List<Paso> toPasos(List<com.github.danimaniarqsoft.migration.Paso> from) {
        if (from == null || from.isEmpty()) {
            return Collections.emptyList();
        }
        List<Paso> toList = new ArrayList<>();
        for (com.github.danimaniarqsoft.migration.Paso temp : from) {
            Paso paso = new Paso();
            paso.setDesc(temp.getDesc());
            paso.setPaso(temp.getPaso());
            toList.add(paso);
        }
        return toList;
    }

}