package org.openjfx.Models.Formaterer;

import org.openjfx.Models.KomponenterTableView;
import org.openjfx.Models.Konfigurasjon;

public class KonfigurasjonsFormatererBruker {

    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKonfigurasjon(KomponenterTableView komponent, Konfigurasjon konfig){
        return komponent.getKategori()+DELIMITER+ komponent.getNavn()+ DELIMITER+ komponent.getPris()+DELIMITER+konfig.getSluttPris();
    }

    //formaterer liste. Vet ikke om denne trengs?
   /* public static String formaterProduktListe(List<Konfigurasjon> liste){
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Konfigurasjon p: liste){
            str.append(formater(p));
            str.append("\n");
        }
        return str.toString();
    }*/
}
