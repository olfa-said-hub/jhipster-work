package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class UsineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usine.class);
        Usine usine1 = new Usine();
        usine1.setId(1L);
        Usine usine2 = new Usine();
        usine2.setId(usine1.getId());
        assertThat(usine1).isEqualTo(usine2);
        usine2.setId(2L);
        assertThat(usine1).isNotEqualTo(usine2);
        usine1.setId(null);
        assertThat(usine1).isNotEqualTo(usine2);
    }
}
