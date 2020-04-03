package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererBruker;
import org.openjfx.Models.Konfigurasjon;

public class KonfigurasjonsParserBruker {


    //Parser produkt fra fil til string:
    public static Konfigurasjon parseKonfigurasjonBruker(String str){

        String[] string = str.split(KonfigurasjonsFormatererBruker.DELIMITER);
        //Dette skal kanskje ikke splittes på dette.
        String navn= string[0];
        String kategori= string[1];
        String pris= string[2];
        String sluttPris=string[3];


        //trenger en konstruktør i Konfigurasjon.
        Konfigurasjon etProdukt= new Konfigurasjon();
        return etProdukt;

    }

}
