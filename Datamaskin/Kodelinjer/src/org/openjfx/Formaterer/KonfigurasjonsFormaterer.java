package org.openjfx.Formaterer;

import org.openjfx.Models.Produkt;

import java.security.PrivateKey;
import java.text.ParseException;
import java.util.List;

public class KonfigurasjonsFormaterer {

    public static String DELIMITER=";";

    //formaterer String.
    public static String formater(Produkt produkt){
        return produkt.getKategori()+DELIMITER+ produkt.getNavn()+ DELIMITER+ produkt.getPris();
    }

    //formaterer liste.
    public static String formaterProduktListe(List<Produkt> liste){
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Produkt p: liste){
            str.append(formater(p));
            str.append("\n");
        }
        return str.toString();
    }


    //Fra oblig1
    /*
    class PersonFormaterer
    public static String formater(Person person) throws ParseException { //formaterer et personobjekt og legger dette i en string
        return person.getNavn()+ DELIMITER+ person.getAlder()+ DELIMITER+ person.getEpost()+ DELIMITER+
                person.getFdato()+ DELIMITER+ person.getTlf();

    }
    public static String formaterPersoner(List<Person> plist) throws ParseException{
        //type string klasse som kan dynamsik utvides på en god måte.
        StringBuffer str =  new StringBuffer();
        for(Person p: plist){
            str.append(formaterPerson(p));
            str.append("\n");
        }
        return str.toString();
    }*/
}
