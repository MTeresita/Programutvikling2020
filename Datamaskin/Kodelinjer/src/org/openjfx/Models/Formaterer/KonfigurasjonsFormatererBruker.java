package org.openjfx.Models.Formaterer;

import org.openjfx.Models.Komponent;

import java.text.ParseException;
import java.util.List;

public class KonfigurasjonsFormatererBruker {

    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKonfigurasjon(Komponent valg){
        return valg.getNavn()+DELIMITER+ valg.getKategori()+ DELIMITER+ valg.getPris();
    }

}
