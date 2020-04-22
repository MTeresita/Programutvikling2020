package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikKomponentNyKategori;
import org.openjfx.Models.Avvik.AvvikKomponentPris;
import org.openjfx.Models.Avvik.AvvikKomponentProduktnavn;

import static org.junit.jupiter.api.Assertions.*;

class ValideringKomponentTestTest {

    @Test
    void testKomponenterTrue() throws AvvikKomponentProduktnavn, AvvikKomponentNyKategori, AvvikKomponentPris {
        assertTrue(ValideringKomponent.validerProduktnavn("Minnekort"));
        assertTrue(ValideringKomponent.validerNyKategori("Proosessor"));
        assertTrue(ValideringKomponent.validerPris(100.0));

    }

    @Test
    void testKomponenterFalse() throws AvvikKomponentProduktnavn {

        //for kort:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn("j"));
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("j"));
        //tom:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn(""));
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori(""));
        //for Lang:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn("ajackalfkalfklafjjflafjalfjlafjjalfafjkklkalfklafklfkllkaflkfaklaklffkfkjfksksfkfkjsjkfkj"));
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("akflaflafklakflakflafklakflafkkflalkflafkflakkdfkajfksjkfjksfjkffsjkjksfkjfsjkf"));
        //Pris under 1:
        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris(0));
        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris(0.0));
        //negativ pris
        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris(-100.0));


    }
}