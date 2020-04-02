package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {

    ArrayList<KomponenterTableView> konfigListe = new ArrayList<KomponenterTableView>();
    ObservableList<KomponenterTableView> konfigListeObservable; //trenger kanskje ikke denne

    private double sluttPris;

    public void setNyttKomponent(KomponenterTableView komponent) { //for bruker
        ArrayList<KomponenterTableView> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        boolean ok = true; //om false er eksisterende komponent oppdatert, om true legges nytt komponent til listen

        for(KomponenterTableView p : konfigListeIterator){ //sjekk om komponent med samme kategori er i listen fra før
            if(p.getKategori().equals(komponent.getKategori()) && !komponent.getKategori().equals("HARD DRIVE")){ //gjør unntak for HARD DRIVE, ettersom en data kan ha mange hdd-er
                konfigListeIterator.set(konfigListeIterator.indexOf(p), komponent); //if so, oppdater liste med nytt komponent

                //konfigListeObservable.set(konfigListeIterator.indexOf(p), komponent); //setter elementer i observablelist, ikke fungerende

                setKonfigListe(konfigListeIterator); //setter også sluttpris
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
    }

    public void setValgteKomponenter(ArrayList<KomponenterTableView> komponenter) { //for bruker
        konfigListe.clear();
        ArrayList<KomponenterTableView> konfigListeIterator = konfigListe;
        for(KomponenterTableView p : komponenter){
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

    public double getSluttPris() {
        return sluttPris;
    }

    public ArrayList<KomponenterTableView> getKonfigListe() {
        return konfigListe;
    }
    public ObservableList<KomponenterTableView> getKonfigListeObservable() {
        return konfigListeObservable;
    }


    //proof of concept

    public String toString(){
        String ut ="Dette er listen over valgte komponeneter: \n";

        Iterator itr = konfigListe.iterator(); //lager en iterator

        while(itr.hasNext()){ //usikker om dette funker
            KomponenterTableView p = (KomponenterTableView) itr.next();
            ut += "Navn: "+p.getNavn()+", Kategori: "+p.getKategori()+", Pris: "+p.getPris()+"\n";
        }
        ut += "Sluttpris: "+getSluttPris();
        return ut;
    }
}
