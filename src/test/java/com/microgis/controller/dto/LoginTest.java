package com.microgis.controller.dto;

import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class LoginTest {

    @Test
    void testEquals() {
        EqualsVerifier.forClass(Login.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .usingGetClass()
                .verify();
    }

    @Test
    void testToString() {
        ToStringVerifier.forClass(Login.class).verify();
    }
}