package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.net.MalformedURLException;
import com.github.danimaniarqsoft.domain.Evento;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates default events.
 */
@ChangeLog(order = "006")
public class EventoMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "01-AddEventos")
    public void addUsuario(MongoTemplate mongoTemplate)
            throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

        Evento consultarCodigoError = new Evento();
        consultarCodigoError.nombre("Consultar código de error").desc("Consulta de un código de error en la plataforma").setId("1");
        mongoTemplate.save(consultarCodigoError);

        Evento registrarEncuesta = new Evento();
        registrarEncuesta.nombre("Registrar encuesta").desc("Registro de encuensta en la plataforma").setId("2");
        mongoTemplate.save(registrarEncuesta);

        Evento registrarContacto = new Evento();
        registrarContacto.nombre("Registrar contacto").desc("Registro de contacto nuevo en la plataforma").setId("3");
        mongoTemplate.save(registrarContacto);

    }
}
