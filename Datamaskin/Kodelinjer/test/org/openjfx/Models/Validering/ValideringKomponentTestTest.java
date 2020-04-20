package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikKomponent;

import static org.junit.jupiter.api.Assertions.*;

class ValideringKomponentTestTest {

    @Test
    void testKomponenterTrue() throws AvvikKomponent {
        assertTrue(ValideringKomponent.validerProduktnavn("Minnekort"));
        assertTrue(ValideringKomponent.validerNyKategori("Proosessor"));
        //assertTrue(ValideringKomponent.validerPris(100.0));

    }

    @Test
    void testKomponenterFalse() throws AvvikKomponent {
        /*
        //for kort:
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerProduktnavn("j"));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerNyKategori("j"));
        //tom:
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerProduktnavn(""));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerNyKategori(""));
        //for Lang:
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerProduktnavn("ajackalfkalfklafjjflafjalfjlafjjalfafjkklkalfklafklfkllkaflkfaklaklffkfkjfksksfkfkjsjkfkj"));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerNyKategori("akflaflafklakflakflafklakflafkkflalkflafkflakkdfkajfksjkfjksfjkffsjkjksfkjfsjkf"));
        //Pris under 1:
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerPris(0));
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerPris(0.0));
        //negativ pris
        assertThrows(AvvikProdukt.class, () -> ValideringKomponent.validerPris(-100.0));

         */
    }
}