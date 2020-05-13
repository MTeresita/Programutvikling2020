package org.openjfx.Models.Parsing;

import javafx.scene.control.Alert;
import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererBruker;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.Konfigurasjon;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;

public class KonfigurasjonsParserBruker {


    //Parser produkt fra fil til string:
    public static Komponent parseKonfigurasjonBruker(String str){
        try {
            String[] stringArray = str.split(KonfigurasjonsFormatererBruker.DELIMITER);

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
            if(navn.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{2,50}$") && kategori.matches("^[A-ZÆØÅa-zæøå _@./#&+-]{2,50}$") && prisDouble >= 0 && prisDouble < 1000000){
                Komponent enKomponent = new Komponent(navn, kategori, prisDouble, false);
                return enKomponent;
            }else{
                System.out.println("Feil i komponent! : " + navn);
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("En komponent hadde ugylige verdier og er fjernet fra listen! "+navn);
                a.show();
                return null;
            }

        } catch(ArrayIndexOutOfBoundsException e){
            //ettersom sluttpris er en egen value helt i bånn av csv filen, vil denne exception alltid kastes ettersom stringArray alltid vil kun ha 1 element når dne skal parse sluttprisen,
            // dette vil vi skal skje ettersom sluttpris ikke trenger å være med i parsingen, den lages direkte i konfigurasjonsklassen
        }
        return null;
    }

}
