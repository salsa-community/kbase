package com.github.danimaniarqsoft.config.dbmigrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.danimaniarqsoft.domain.Authority;
import com.github.danimaniarqsoft.domain.User;
import com.github.danimaniarqsoft.migration.ErrorMigration;
import com.github.danimaniarqsoft.security.AuthoritiesConstants;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "002")
public class MensajeErroresMigration {

    //@ChangeSet(order = "01", author = "arquitectura", id = "01-addMensajeErrores")
    public void addErrors(MongoTemplate mongoTemplate) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(src, ErrorMigration.class);
        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthoritiesConstants.ADMIN);
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthoritiesConstants.USER);
        mongoTemplate.save(adminAuthority);
        mongoTemplate.save(userAuthority);
    }
}
