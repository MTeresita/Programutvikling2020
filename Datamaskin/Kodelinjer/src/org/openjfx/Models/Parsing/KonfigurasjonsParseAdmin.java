package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererAdmin;
import org.openjfx.Models.KomponenterListe;

public class KonfigurasjonsParseAdmin {
    public static KomponenterListe parseKomponenterListe(String str){

        String[] string = str.split(KonfigurasjonsFormatererAdmin.DELIMITER);

        String navn= string[0];
        String kategori= string[1];
        String pris= string[2];


        //trenger en kontstruktør eller en metode i komponenterListe
        KomponenterListe etProdukt= new KomponenterListe();

        return etProdukt;

    }
}
