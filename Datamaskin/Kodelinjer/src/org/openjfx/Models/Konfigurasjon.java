package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.Models.Avvik.AvvikBruker;

import java.util.ArrayList;
import java.util.Iterator;

public class Konfigurasjon {

    ArrayList<Komponent> konfigListe = new ArrayList<Komponent>();
    ObservableList<Komponent> konfigListeObservable; //trenger kanskje ikke denne

    private double sluttPris;

    public void setNyttKomponent(Komponent komponent) throws AvvikBruker { //for bruker
        ArrayList<Komponent> konfigListeIterator = konfigListe; //lager en midlertidig kopi av hovedlisten

        boolean ok = true; //om false oppdateres listen med nytt valgte komponent, om true legges nytt komponent til listen
        int maksAntall = komponent.getAntall(); //maks antall av dette komponentet i en konfigurasjon
        int teller = 0; //antall av valgt komponeneter allerede i konfigurasjonen
        for(Komponent k : konfigListeIterator){ //sjekk om komponent med samme kategori er i listen fra før
            if (k.getKategori().equals(komponent.getKategori()) && maksAntall <= 1) {
                konfigListeIterator.set(konfigListeIterator.indexOf(k), komponent);
                setKonfigListe(konfigListeIterator);
                ok = false;
            }else if(k.getKategori().equals(komponent.getKategori())){
                teller++;
            }
        }
        if(ok){
            if(teller < maksAntall){
                konfigListeIterator.add(komponent);
                setKonfigListe(konfigListeIterator);//setter også sluttpris
            }else{
                throw new AvvikBruker("Du kan kun ha "+maksAntall+" stykk per konfigurasjon av dette produktet!" +
                        "\n For å endre konfigurasjon, slett et produkt fra din konfigurasjon med kategori: "+komponent.getKategori());
            }
        }
    }

    public void slettKomponent(int index){
        konfigListe.remove(index);
        lagSluttPris();
    }

    //TODO denne blir ikke brukt ?
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

    public void setKonfigListe(ArrayList<Komponent> konfigListe) throws NullPointerException {
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
        StringBuilder ut = new StringBuilder();

        //lager en iterator
        for (Komponent p : konfigListe) {
            ut.append(p.getNavn()).append(";").append(p.getKategori()).append(";").append(p.getPris()).append(";").append("\n");
        }
        ut.append(sluttPris+";");
        return ut.toString();
    }
}
