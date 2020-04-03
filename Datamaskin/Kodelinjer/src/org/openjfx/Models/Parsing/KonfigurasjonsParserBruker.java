package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormaterer;
import org.openjfx.Models.Konfigurasjon;

public class KonfigurasjonsParserBruker {


    //Parser produkt fra fil til string:
    public static Konfigurasjon parseKonfigurasjonBruker(String str){

        String[] string = str.split(KonfigurasjonsFormaterer.DELIMITER);

        String navn= string[0];
        String pris= string[1];
        String kategori= string[2];


        Konfigurasjon etProdukt= new Konfigurasjon();
        return etProdukt;

    }

}
