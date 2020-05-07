package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererBruker;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.Konfigurasjon;

public class KonfigurasjonsParserBruker {


    //Parser produkt fra fil til string:
    public static Komponent parseKonfigurasjonBruker(String str){
        try {
            String[] stringArray = str.split(KonfigurasjonsFormatererBruker.DELIMITER);

            //løkke:
            System.out.println(stringArray[0] + ", " + stringArray[1] + ", " + stringArray[2]);
            String navn = stringArray[0];
            String kategori = stringArray[1];
            String pris = stringArray[2];

            double prisDouble = 0;

            try {
                prisDouble = Double.parseDouble(pris);
            } catch (Exception e) {
                e.getMessage();
            }

            Komponent enKomponent = new Komponent(navn, kategori, prisDouble, false);
            return enKomponent;
        } catch(ArrayIndexOutOfBoundsException e){
            //ettersom sluttpris er en egen value helt i bånn av csv filen, vil denne exception alltid kastes ettersom stringArray alltid vil kun ha 1 element med sluttprisen,
            // dette vil vi skal skje ettersom sluttpris ikke trenger å være medi parsingen, den lages direkte i konfigurasjonsklassen
        }
        return null;
    }

}
