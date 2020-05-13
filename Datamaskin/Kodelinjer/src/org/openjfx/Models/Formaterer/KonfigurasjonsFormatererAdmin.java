package org.openjfx.Models.Formaterer;

import org.openjfx.Models.Komponent;

public class KonfigurasjonsFormatererAdmin {
    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKomponenterListe(Komponent komponent){
        return komponent.getKategori()+DELIMITER+ komponent.getNavn()+ DELIMITER+ komponent.getPris();
    }
}
