package org.openjfx.Models;

import java.util.ArrayList;
import java.util.Collections;

public class ProduktListe {


    private ArrayList<Produkt> produkter = new ArrayList<Produkt>();

    //konstruktør:



    //funksjoner:
    public void leggTilProdukt(Produkt... produkter){

    }

    public void setProdukter(Produkt... produkter) {
        Collections.addAll(this.produkter, produkter);
    }

    public ArrayList<Produkt> getProdukter(Produkt... produkter) {
        ArrayList<Produkt> produkterIterator = new ArrayList<Produkt>();

        for(Produkt p : produkter){
            produkterIterator.add(p);
        }
        return produkterIterator;
    }

}
