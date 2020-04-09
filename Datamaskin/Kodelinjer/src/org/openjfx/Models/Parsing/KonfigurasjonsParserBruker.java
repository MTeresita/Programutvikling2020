package org.openjfx.Models.Parsing;

import org.openjfx.Models.Formaterer.KonfigurasjonsFormatererBruker;
import org.openjfx.Models.Konfigurasjon;

public class KonfigurasjonsParserBruker {


    //Parser produkt fra fil til string:
    public static Konfigurasjon parseKonfigurasjonBruker(String str){

        String[] string = str.split(KonfigurasjonsFormatererBruker.DELIMITER);

        //løkke:


        String navn= string[0];
        String kategori= string[1];
        String pris= string[2];
        String sluttPris=string[3];

        double prisDouble=0;
        double sluttprisDouble=0;

        try{
            prisDouble=Double.parseDouble(pris);
            sluttprisDouble=Double.parseDouble(sluttPris);
        }
        catch(Exception e){
            e.getMessage();
        }


        //Skal returnere en liste med komponenterTableView
        Konfigurasjon enKonfigurasjon= new Konfigurasjon();
        return enKonfigurasjon;

    }

}
