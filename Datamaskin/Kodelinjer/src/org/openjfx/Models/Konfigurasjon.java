package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {

    ArrayList<KomponenterTableView> konfigListe = new ArrayList<KomponenterTableView>();
    ObservableList<KomponenterTableView> konfigListeObservable;

    private double sluttPris;

    public void setNyttProdukt(KomponenterTableView produkt) { //for bruker
        ArrayList<KomponenterTableView> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        boolean ok = true; //om false er eksisterende produkt oppdatert, om true legges nytt produkt til listen

        for(KomponenterTableView p : konfigListeIterator){ //sjekk om produkt med samme kategori er i listen fra før
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

    public void setValgteProdukter(ArrayList<KomponenterTableView> produkter) { //for bruker
        konfigListe.clear();
        ArrayList<KomponenterTableView> konfigListeIterator = konfigListe;
        for(KomponenterTableView p : produkter){
           konfigListeIterator.add(p);
        }
        setKonfigListe(konfigListeIterator);

    }

    public void lagSluttPris(){
        double sluttPrisIterator = 0.0;
        ArrayList<KomponenterTableView> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        Iterator itr = konfigListeIterator.iterator(); //lager en iterator

        while(itr.hasNext()){
            KomponenterTableView produkt = (KomponenterTableView) itr.next();
            sluttPrisIterator += produkt.getPris();
        }

        setSluttPris(sluttPrisIterator);
    }

    public void setKonfigListe(ArrayList<KomponenterTableView> konfigListe) {
        this.konfigListe = konfigListe;
        konfigListeObservable = FXCollections.observableArrayList(konfigListe);
        lagSluttPris(); //hver gang konfigListe endres på, kjøres lagSluttPris
    }


    public void setSluttPris(double sluttPris) {
        this.sluttPris = sluttPris;
    }
    public ArrayList<KomponenterTableView> getKonfigListe() {
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
            KomponenterTableView p = (KomponenterTableView) itr.next();
            ut += "Navn: "+p.getNavn()+", Kategori: "+p.getKategori()+", Pris: "+p.getPris()+"\n";
        }
        ut += "Sluttpris: "+getSluttPris();
        return ut;
    }
}
