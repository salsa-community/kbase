package com.github.danimaniarqsoft.config.dbmigrations;


import java.util.List;

import com.github.danimaniarqsoft.domain.Contexto;
import com.github.danimaniarqsoft.domain.MensajeError;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@Profile("vtiger")
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

    @ChangeSet(order = "02", author = "arquitectura", id = "02-AddDefaultContexto")
    public void addDefaultContexto(MongoTemplate mongoTemplate) {
        List<MensajeError> mensajes = mongoTemplate.findAll(MensajeError.class);
        mensajes.forEach(mensaje -> {
            mensaje.addContextos("RedCofidi");
            mongoTemplate.save(mensaje);
        });
    }
}
