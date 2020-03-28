package org.openjfx.Models;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {

    ArrayList<Produkt> konfigListe = new ArrayList<Produkt>();

    private double sluttPris;

    public void setNyttProdukt(Produkt produkt) { //for bruker
        ArrayList<Produkt> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten
        boolean ok = true; //om false er eksisterende produkt oppdatert, om true legges nytt produkt til listen
        for(Produkt p : konfigListeIterator){ //sjekk om produkt med samme kategori er i listen fra før
            if(p.getKategori() == produkt.getKategori() && produkt.getKategori() !=  "HARD DRIVE"){ //gjør unntak for HARD DRIVE, ettersom en data kan ha mange hdd-er
                konfigListeIterator.set(konfigListeIterator.indexOf(p), produkt); //if so, oppdater liste med nytt produkt
                setKonfigListe(konfigListeIterator); //setter også sluttpris
                ok = false;
            }
        }
        if(ok){
            konfigListeIterator.add(produkt);
            setKonfigListe(konfigListeIterator);//setter også sluttpris
        }
    }

    public void setValgteProdukter(ArrayList<Produkt> produkter) { //for bruker
        konfigListe.clear();
        for(Produkt p : produkter){
           konfigListe.add(p);
        }
    }

    public void lagSluttPris(){
        double sluttPrisIterator = 0.0;
        ArrayList<Produkt> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        Iterator itr = konfigListeIterator.iterator(); //lager en iterator

        while(itr.hasNext()){
            Produkt produkt = (Produkt)itr.next();
            sluttPrisIterator += produkt.getPris();
        }

        setSluttPris(sluttPrisIterator);
    }

    public void setKonfigListe(ArrayList<Produkt> konfigListe) {
        this.konfigListe = konfigListe;
        lagSluttPris(); //hver gang konfigListe endres på, kjøres lagSluttPris
    }
    public void setSluttPris(double sluttPris) {
        this.sluttPris = sluttPris;
    }
    public ArrayList<Produkt> getKonfigListe() {
        return konfigListe;
    }
    public double getSluttPris() {
        return sluttPris;
    }

    //proof of concept
    public String toString(){
        String ut ="Her er ut: \n";

        Iterator itr = konfigListe.iterator(); //lager en iterator

        while(itr.hasNext()){ //usikker om dette funker
            Produkt p = (Produkt)itr.next();
            ut += "Navn: "+p.getNavn()+", Kategori: "+p.getKategori()+", Pris: "+p.getPris()+"\n";
        }
        ut += "Sluttpris: "+getSluttPris();
        return ut;
    }
}
