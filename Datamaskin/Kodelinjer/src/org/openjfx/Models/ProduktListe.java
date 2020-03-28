package org.openjfx.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProduktListe {


    private ArrayList<Produkt> valgteProdukter = new ArrayList<Produkt>();

    //konstruktør:



    //funksjoner:
    public void leggTilProdukt(Produkt produkt){
        valgteProdukter.add(produkt);
    }

    public String hentProduktliste(){
        String listing="";
        for(Produkt liste : valgteProdukter){
            listing+= liste+ " ";
        }
        return listing;
    }
}
