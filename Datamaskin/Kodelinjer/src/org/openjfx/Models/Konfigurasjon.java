package org.openjfx.Models;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {
    ArrayList<Produkt> konfigListe = new ArrayList<Produkt>();
    private int sluttPris;

    public void setNyttProdukt(Produkt produkt) {
        ArrayList<Produkt> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten
        boolean ok = true; //om false er eksisterende produkt oppdatert, om true legges nytt produkt til listen
        for(Produkt p : konfigListeIterator){ //sjekk om produkt med samme kategori er i listen fra før
            if(p.getKategori() == produkt.getKategori()){
                konfigListeIterator.set(konfigListeIterator.indexOf(p), produkt); //if so, oppdater liste med nytt produkt
                setKonfigListe(konfigListeIterator);

                ok = false;
            }
        }
        if(ok){
            konfigListeIterator.add(produkt);
            setKonfigListe(konfigListeIterator);
        }
    }
    public void lagSluttPris(){
        int sluttPrisIterator = 0;
        ArrayList<Produkt> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten
        Iterator itr = konfigListeIterator.iterator(); //lager en iterator

        while(itr.hasNext()){ //usikker om dette funker
            Produkt produkt = (Produkt)itr.next();
            sluttPrisIterator += produkt.getPris();
        }

        setSluttPris(sluttPrisIterator);
    }

    public void setKonfigListe(ArrayList<Produkt> konfigListe) {
        this.konfigListe = konfigListe;
        lagSluttPris(); //hver gang konfigListe endres på, kjøres lagSluttPris
    }
    public void setSluttPris(int sluttPris) {
        this.sluttPris = sluttPris;
    }
    public ArrayList<Produkt> getKonfigListe() {
        return konfigListe;
    }
    public int getSluttPris() {
        return sluttPris;
    }
}
