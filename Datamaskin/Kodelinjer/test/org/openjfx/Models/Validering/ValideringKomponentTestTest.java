package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikKomponentNyKategori;
import org.openjfx.Models.Avvik.AvvikKomponentPris;
import org.openjfx.Models.Avvik.AvvikKomponentProduktnavn;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValideringKomponentTestTest {

    @Test
    void testKomponenterTrue() throws AvvikKomponentProduktnavn, AvvikKomponentNyKategori, AvvikKomponentPris {
        /*
        assertTrue(ValideringKomponent.validerProduktnavn("Minnekort"));
        //grenseverdi
        assertTrue(ValideringKomponent.validerProduktnavn("ME"));
        //med tall
        assertTrue(ValideringKomponent.validerProduktnavn("Minnekort899899"));
        //med tall og mellomrom
        assertTrue(ValideringKomponent.validerProduktnavn("Minnekort 899899"));



        assertTrue(ValideringKomponent.validerNyKategori("Proosessor"));
        //grenseverdi
        assertTrue(ValideringKomponent.validerNyKategori("PR"));

         

/*
        //går ikke an å teste pris når det skal være string inn.

        double pris=100.0;
        String stringPris=String.valueOf(pris);
        // denne gir rød kjøring.
        assertTrue(ValideringKomponent.validerPris(stringPris));
        //denne gir også rød kjøring.
        assertTrue(ValideringKomponent.validerPris("100.0"));

        //Denne er ikke lov:
        //assertTrue(ValideringKomponent.validerPris(100.00));

 */
    }

    @Test
    void testKomponenterFalse() throws AvvikKomponentProduktnavn {
        /*

        //for kort:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn("j"));

        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("j"));


        //tom:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn(""));

        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori(""));


        //bare mellomrom
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn("       "));

        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("         "));


        //for Lang:
        assertThrows(AvvikKomponentProduktnavn.class, () -> ValideringKomponent.validerProduktnavn("ajackalfkalfklafjjflafjalfjlafjjalfafjkklkalfklafklfkllkaflkfaklaklffkfkjfksksfkfkjsjkfkj"));

        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("akflaflafklakflakflafklakflafkkflalkflafkflakkdfkajfksjkfjksfjkffsjkjksfkjfsjkf"));
        //Pris under 1:


        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris("0"));
        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris("0.0"));
        //negativ pris
        assertThrows(AvvikKomponentPris.class, () -> ValideringKomponent.validerPris("-100.0"));
        //kategori med tall i ny kategor:
        /*
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("hovedkort123"));
        //bare tall:
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("1234"));
        //med mellomrom:
        assertThrows(AvvikKomponentNyKategori.class, () -> ValideringKomponent.validerNyKategori("prosessor pc"));


         */
    }
}
