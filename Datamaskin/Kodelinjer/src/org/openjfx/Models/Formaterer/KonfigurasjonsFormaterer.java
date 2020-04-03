package org.openjfx.Models.Formaterer;

import org.openjfx.Models.KomponenterTableView;

import java.util.List;

public class KonfigurasjonsFormaterer {

    public static String DELIMITER=";";

    //formaterer produkt-objekt med ; i mellom.
    public static String formater(KomponenterTableView produkt){
        return produkt.getKategori()+DELIMITER+ produkt.getNavn()+ DELIMITER+ produkt.getPris();
    }

    //formaterer liste. Vet ikke om denne trengs?
    public static String formaterProduktListe(List<KomponenterTableView> liste){
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(KomponenterTableView p: liste){
            str.append(formater(p));
            str.append("\n");
        }
        return str.toString();
    }
}
