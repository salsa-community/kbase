package com.github.danimaniarqsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.danimaniarqsoft.web.rest.TestUtil;

public class MensajeErrorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MensajeErrorDTO.class);
        MensajeErrorDTO mensajeErrorDTO1 = new MensajeErrorDTO();
        mensajeErrorDTO1.setId("id1");
        MensajeErrorDTO mensajeErrorDTO2 = new MensajeErrorDTO();
        assertThat(mensajeErrorDTO1).isNotEqualTo(mensajeErrorDTO2);
        mensajeErrorDTO2.setId(mensajeErrorDTO1.getId());
        assertThat(mensajeErrorDTO1).isEqualTo(mensajeErrorDTO2);
        mensajeErrorDTO2.setId("id2");
        assertThat(mensajeErrorDTO1).isNotEqualTo(mensajeErrorDTO2);
        mensajeErrorDTO1.setId(null);
        assertThat(mensajeErrorDTO1).isNotEqualTo(mensajeErrorDTO2);
    }
}
