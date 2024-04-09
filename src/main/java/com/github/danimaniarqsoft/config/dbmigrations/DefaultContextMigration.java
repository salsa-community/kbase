package com.github.danimaniarqsoft.config.dbmigrations;

import com.github.danimaniarqsoft.domain.Contexto;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "010")
public class DefaultContextMigration {

    @ChangeSet(order = "01", author = "arquitectura", id = "default-context")
    public void addContext(MongoTemplate mongoTemplate) {
        Contexto contexto = new Contexto();
        contexto.setId("default");
        contexto.clave("default");
        contexto.setDesc("Default context");
        contexto.setDescEn("Default context");
        contexto.setLoginMessage("Por favor, inicie ses");
        contexto.setLoginMessageEn("Please login");
        contexto.setNombre("default context");
        contexto.setObjetivo("Objetivo por default");
        contexto.setObjetivoEn("Default objetive");
        contexto.setOrganizacion("Kbase");
        contexto.setWelcomeMessage("Bienvenido");
        contexto.setWelcomeMessageEn("Welcomo to the chatbot");
        mongoTemplate.save(contexto);
    }
}
