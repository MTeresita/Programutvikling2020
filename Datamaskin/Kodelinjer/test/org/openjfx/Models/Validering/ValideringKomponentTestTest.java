package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikProdukt;

import static org.junit.jupiter.api.Assertions.*;

class ValideringKomponentTestTest {

    @Test
    void testKomponenterTrue() throws AvvikProdukt {
        assertTrue(ValideringKomponent.validerNyKomponent("HalloHallo","HalloHallo99", 23));
        assertTrue(ValideringKomponent.validerNyKomponent("aaaaAAAAA", "HalloHalloHHH88999", 100));
       // assertTrue(ValideringKomponent.validerNyKomponent("", "", 0));

    }

    @Test
    void testKomponenterFalse() throws AvvikProdukt {
        assertFalse(ValideringKomponent.validerNyKomponent("Hallo23Hallo","90am", -90));
        assertFalse(ValideringKomponent.validerNyKomponent("892482482948942","100000", 0));
    }
}