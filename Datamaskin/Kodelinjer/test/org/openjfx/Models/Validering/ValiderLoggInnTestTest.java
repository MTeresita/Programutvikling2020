package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInn;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInn {
        assertTrue(ValiderLoggInn.valideringBrukernavn("camillaharic"));
        assertTrue(ValiderLoggInn.validerPassord("HalloHallo"));

    }
    @Test
    public void testLoggInnFeil() throws AvvikLoggInn{
        assertFalse(ValiderLoggInn.valideringBrukernavn("c"));
        assertFalse(ValiderLoggInn.validerPassord("c"));
    }
}