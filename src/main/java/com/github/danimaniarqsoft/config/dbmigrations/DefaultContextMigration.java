package com.github.danimaniarqsoft.config.dbmigrations;

import com.github.danimaniarqsoft.domain.Contexto;
import com.github.danimaniarqsoft.domain.Mensaje;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import java.util.ArrayList;
import java.util.List;

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
        contexto.setNombre("default context");
        contexto.setOrganizacion("Kbase");
        contexto.setMensajes(defaultMessageList());
        mongoTemplate.save(contexto);
    }

    private static List<Mensaje> defaultMessageList(){
        List<Mensaje> messages = new ArrayList<>();
        messages.add(createMessage("Bienvenido a $ORGANIZACION","Welcome to $ORGANIZACION", 1));
        messages.add(createMessage("Este es un flujo demo para el contexto por default","This is a demo flow", 2));
        messages.add(createMessage("Configura estos flujos desde la consola del kbase","Config this dialog into the kbase", 3));
        return messages;
    }

    private static Mensaje createMessage(String desc, String descEn, Integer order){
        Mensaje message = new Mensaje();
        message.setDesc(desc);
        message.setDescEn(descEn);
        message.setOrden(order);
        return message;
    }
}
