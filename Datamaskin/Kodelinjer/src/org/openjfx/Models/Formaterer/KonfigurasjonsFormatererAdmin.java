package org.openjfx.Models.Formaterer;

import org.openjfx.Models.KomponenterTableView;

public class KonfigurasjonsFormatererAdmin {
    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKomponenterListe(KomponenterTableView komponent){
        return komponent.getKategori()+DELIMITER+ komponent.getNavn()+ DELIMITER+ komponent.getPris();
    }
}
