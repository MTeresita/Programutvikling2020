package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {

    ArrayList<Komponent> konfigListe = new ArrayList<Komponent>();
    ObservableList<Komponent> konfigListeObservable; //trenger kanskje ikke denne

    private double sluttPris;

    public void setNyttKomponent(Komponent komponent) { //for bruker
        ArrayList<Komponent> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        boolean ok = true; //om false er eksisterende komponent oppdatert, om true legges nytt komponent til listen

        for(Komponent p : konfigListeIterator){ //sjekk om komponent med samme kategori er i listen fra før
            if (p.getKategori().equals(komponent.getKategori()) && !komponent.isDuplikat()) {
                konfigListeIterator.set(konfigListeIterator.indexOf(p), komponent);
                setKonfigListe(konfigListeIterator);
                ok = false;
            }
        }
        if(ok){
            konfigListeIterator.add(komponent);
            setKonfigListe(konfigListeIterator);//setter også sluttpris
        }
    }

    public void slettKomponent(int index){
        konfigListe.remove(index);
        lagSluttPris();
    }

    public void setValgteKomponenter(ArrayList<Komponent> komponenter) { //for bruker
        konfigListe.clear();
        ArrayList<Komponent> konfigListeIterator = konfigListe;
        for(Komponent p : komponenter){
           konfigListeIterator.add(p);
        }
        setKonfigListe(konfigListeIterator);

    }

    public void lagSluttPris(){
        double sluttPrisIterator = 0.0;
        ArrayList<Komponent> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        Iterator itr = konfigListeIterator.iterator(); //lager en iterator

        while(itr.hasNext()){
            Komponent produkt = (Komponent) itr.next();
            sluttPrisIterator += produkt.getPris();
        }

        setSluttPris(sluttPrisIterator);
    }

    public void setKonfigListe(ArrayList<Komponent> konfigListe) {
        this.konfigListe = konfigListe;
        konfigListeObservable = FXCollections.observableArrayList(konfigListe);
        lagSluttPris(); //hver gang konfigListe endres på, kjøres lagSluttPris
    }


    public void setSluttPris(double sluttPris) {
        this.sluttPris = sluttPris;
    }

    public double getSluttPris() {
        return sluttPris;
    }

    public ArrayList<Komponent> getKonfigListe() {
        return konfigListe;
    }
    public ObservableList<Komponent> getKonfigListeObservable() {
        return konfigListeObservable;
    }


    //proof of concept

    public String toString(){
        String ut ="Dette er listen over valgte komponeneter: \n";

        Iterator itr = konfigListe.iterator(); //lager en iterator

        while(itr.hasNext()){ //usikker om dette funker
            Komponent p = (Komponent) itr.next();
            ut += "Navn: "+p.getNavn()+", Kategori: "+p.getKategori()+", Pris: "+p.getPris()+"\n";
        }
        ut += "Sluttpris: "+getSluttPris();
        return ut;
    }
}
