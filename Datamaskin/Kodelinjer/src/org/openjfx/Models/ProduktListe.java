package org.openjfx.Models;

import java.util.ArrayList;

public class ProduktListe {


    private ArrayList<Produkt> produkter = new ArrayList<Produkt>();

    //konstruktør:



    //funksjoner:
    public void leggTilProdukt(Produkt produkt){
        produkter.add(produkt);
    }

    public ArrayList<Produkt> getProdukter() {
        return produkter;
    }

}
