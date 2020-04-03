package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormaterer;
import org.openjfx.Models.KomponenterListe;

public class KonfigurasjonsParseAdmin {
    public static KomponenterListe parseKomponenterListe(String str){

        String[] string = str.split(KonfigurasjonsFormaterer.DELIMITER);

        String navn= string[0];
        String pris= string[1];
        String kategori= string[2];


        KomponenterListe etProdukt= new KomponenterListe();
        return etProdukt;

    }
}
