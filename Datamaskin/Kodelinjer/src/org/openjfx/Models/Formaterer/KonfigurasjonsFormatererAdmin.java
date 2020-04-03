package org.openjfx.Models.Formaterer;

import org.openjfx.Models.KomponenterListe;

import java.text.ParseException;
import java.util.List;

public class KonfigurasjonsFormatererAdmin {
    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKomponenterListe(KomponenterListe komponent){
        return komponent.getKategori()+DELIMITER+ komponent.getNavn()+ DELIMITER+ komponent.getPris();
    }
    public static String formaterPersoner(List<KomponenterListe> plist) throws ParseException {
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(KomponenterListe p: plist){
            str.append(formaterKomponenterListe(p));
            str.append("\n");
        }
        return str.toString();
    }
}
