package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererAdmin;
import org.openjfx.Models.KomponenterListe;

public class KonfigurasjonsParseAdmin {
    public static KomponenterListe parseKomponenterListe(String str){

        String[] string = str.split(KonfigurasjonsFormatererAdmin.DELIMITER);

        String navn= string[0];
        String kategori= string[1];
        String pris= string[2];


        double doublePris=0.0;

        try{
            doublePris=Double.parseDouble(pris);

        }
        catch(Exception e){
            e.getMessage();
        }


        KomponenterListe enKomponenterListe= new KomponenterListe();

        return enKomponenterListe;

    }
}
