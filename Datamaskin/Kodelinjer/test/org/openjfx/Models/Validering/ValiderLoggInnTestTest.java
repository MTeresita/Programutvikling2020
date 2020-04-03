package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInn;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInn {
        assertTrue(ValiderLoggInn.valideringLoggInn("HalloHallo","HalloHallo99"));
        assertTrue(ValiderLoggInn.valideringLoggInn("aaaaAAAAA", "HalloHalloHHH88999"));
        assertTrue(ValiderLoggInn.valideringLoggInn("", ""));

    }
    @Test
    public void testLoggInnFeil() throws AvvikLoggInn{
        assertFalse(ValiderLoggInn.valideringLoggInn("Hallo23Hallo","90am"));
        assertFalse(ValiderLoggInn.valideringLoggInn("892482482948942","100000"));
    }
}