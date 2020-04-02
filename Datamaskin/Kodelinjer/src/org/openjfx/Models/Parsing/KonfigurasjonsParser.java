package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.*;
import org.openjfx.Models.Produkt;

public class KonfigurasjonsParser {


    //Parser produkt fra fil til string:
    public static Produkt parseProdukt(String str){

        String[] string = str.split(KonfigurasjonsFormaterer.DELIMITER);

        String navn= string[0];
        String pris= string[1];
        String kategori= string[2];

        System.out.println("String produkt: "+ navn+ " "+pris+ " "+ kategori);


        //parser pris, for å kunne lage et nytt objekt.
        Double prisFormatert=0.0;
        try{
            prisFormatert= Double.parseDouble(pris);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        Produkt etProdukt= new Produkt(navn,prisFormatert,kategori);
        return etProdukt;

    }

}
