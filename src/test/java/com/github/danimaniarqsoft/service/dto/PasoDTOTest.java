package com.github.danimaniarqsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.danimaniarqsoft.web.rest.TestUtil;

public class PasoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PasoDTO.class);
        PasoDTO pasoDTO1 = new PasoDTO();
        pasoDTO1.setId("id1");
        PasoDTO pasoDTO2 = new PasoDTO();
        assertThat(pasoDTO1).isNotEqualTo(pasoDTO2);
        pasoDTO2.setId(pasoDTO1.getId());
        assertThat(pasoDTO1).isEqualTo(pasoDTO2);
        pasoDTO2.setId("id2");
        assertThat(pasoDTO1).isNotEqualTo(pasoDTO2);
        pasoDTO1.setId(null);
        assertThat(pasoDTO1).isNotEqualTo(pasoDTO2);
    }
}
