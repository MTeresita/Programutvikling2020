package org.openjfx.Models.Formaterer;

import org.openjfx.Models.Komponent;

import java.text.ParseException;
import java.util.List;

public class KonfigurasjonsFormatererAdmin {
    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKomponenterListe(Komponent komponent){
        return komponent.getKategori()+DELIMITER+ komponent.getNavn()+ DELIMITER+ komponent.getPris();
    }
    public static String formaterPersoner(List<Komponent> plist) throws ParseException {
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Komponent p: plist){
            str.append(formaterKomponenterListe(p));
            str.append("\n");
        }
        return str.toString();
    }
}
