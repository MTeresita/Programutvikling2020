package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInn;
import org.openjfx.Models.Avvik.AvvikProdukt;

import static org.junit.jupiter.api.Assertions.*;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInn {
        assertTrue(ValiderLoggInn.valideringBrukernavn("camillaharic"));
        assertTrue(ValiderLoggInn.validerPassord("HalloHallo"));

    }
    @Test
    public void testLoggInnFeil() throws AvvikLoggInn{
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn(""));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord(""));
    }
}