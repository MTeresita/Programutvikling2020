package org.openjfx.Models.Formaterer;

import org.openjfx.Models.Produkt;

import java.util.List;

public class KonfigurasjonsFormaterer {

    public static String DELIMITER=";";

    //formaterer produkt-objekt med ; i mellom.
    public static String formater(Produkt produkt){
        return produkt.getKategori()+DELIMITER+ produkt.getNavn()+ DELIMITER+ produkt.getPris();
    }

    //formaterer liste. Vet ikke om denne trengs?
    public static String formaterProduktListe(List<Produkt> liste){
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Produkt p: liste){
            str.append(formater(p));
            str.append("\n");
        }
        return str.toString();
    }
}
