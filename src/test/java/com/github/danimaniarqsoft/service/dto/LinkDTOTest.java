package com.github.danimaniarqsoft.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.danimaniarqsoft.web.rest.TestUtil;

public class LinkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinkDTO.class);
        LinkDTO linkDTO1 = new LinkDTO();
        linkDTO1.setId("id1");
        LinkDTO linkDTO2 = new LinkDTO();
        assertThat(linkDTO1).isNotEqualTo(linkDTO2);
        linkDTO2.setId(linkDTO1.getId());
        assertThat(linkDTO1).isEqualTo(linkDTO2);
        linkDTO2.setId("id2");
        assertThat(linkDTO1).isNotEqualTo(linkDTO2);
        linkDTO1.setId(null);
        assertThat(linkDTO1).isNotEqualTo(linkDTO2);
    }
}
