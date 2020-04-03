package org.openjfx.Models.Formaterer;

import org.openjfx.Models.Konfigurasjon;

import java.text.ParseException;
import java.util.List;

public class KonfigurasjonsFormatererBruker {

    public static String DELIMITER=";";

    //formaterer Komponenter-objekt med ; i mellom.
    public static String formaterKonfigurasjon(Konfigurasjon valg){
        return valg.getKategori()+DELIMITER+ valg.getNavn()+ DELIMITER+ valg.getPris()+DELIMITER+valg.getSluttPris();
    }

    public static String formaterPersoner(List<Konfigurasjon> plist) throws ParseException {
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Konfigurasjon p: plist){
            str.append(formaterKonfigurasjon(p));
            str.append("\n");
        }
        return str.toString();
    }
}
