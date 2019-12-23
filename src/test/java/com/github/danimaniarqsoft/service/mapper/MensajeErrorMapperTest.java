package com.github.danimaniarqsoft.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.danimaniarqsoft.service.mapper.migration.ErrorMigrationMapper;

public class MensajeErrorMapperTest {

    private ErrorMigrationMapper mensajeErrorMigrationMapper;

    @BeforeEach
    public void setUp() {
        //mensajeErrorMigrationMapper = new mensajeErrorMigrationMapper();
    }

    @Test
    public void testEntityFromId() {
        String id = "id2";
       // assertThat(usuarioMapper.fromId(id).getId()).isEqualTo(id);
        //assertThat(usuarioMapper.fromId(null)).isNull();
    }
}
