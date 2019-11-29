package com.github.danimaniarqsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.danimaniarqsoft.web.rest.TestUtil;

public class InstruccionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstruccionDTO.class);
        InstruccionDTO instruccionDTO1 = new InstruccionDTO();
        instruccionDTO1.setId("id1");
        InstruccionDTO instruccionDTO2 = new InstruccionDTO();
        assertThat(instruccionDTO1).isNotEqualTo(instruccionDTO2);
        instruccionDTO2.setId(instruccionDTO1.getId());
        assertThat(instruccionDTO1).isEqualTo(instruccionDTO2);
        instruccionDTO2.setId("id2");
        assertThat(instruccionDTO1).isNotEqualTo(instruccionDTO2);
        instruccionDTO1.setId(null);
        assertThat(instruccionDTO1).isNotEqualTo(instruccionDTO2);
    }
}
