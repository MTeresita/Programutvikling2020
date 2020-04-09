package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikProdukt;

import static org.junit.jupiter.api.Assertions.*;

class ValideringKomponentTestTest {

    @Test
    void testKomponenterTrue() throws AvvikProdukt {
        assertTrue(ValideringKomponent.validerProduktnavn("HalloHallo"));
        assertTrue(ValideringKomponent.validerNyKategori("proosessor"));
        assertTrue(ValideringKomponent.validerPris(100.0));

    }

    @Test
    void testKomponenterFalse() throws AvvikProdukt {
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerProduktnavn(""));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerNyKategori(""));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerPris(0));
    }
}