package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.Models.Filbehandling.FilHenting.FilHentingAdministrator;
import org.openjfx.Models.Filbehandling.FilLagring.FilLagringAdmin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KomponenterListe {


    private ArrayList<Komponent> komponenter = new ArrayList<Komponent>();
    private ObservableList<Komponent> komponenterListeObservable = FXCollections.observableArrayList();;


    //funksjoner:
    public void leggTilProdukt(Produkt... produkter){

    }

    public void lagreTilObjectFil(){
        FilLagringAdmin fla = new FilLagringAdmin();
        fla.lagreTilFil(this.komponenter);
    }
    public void henteFraObjectFil(){
        FilHentingAdministrator fha = new FilHentingAdministrator();
        ArrayList<Produkt> produktListe = fha.hentFraFil();
        setKomponenterFraFil(produktListe);
    }
    public void setKomponenterFraFil(ArrayList<Produkt> liste){
        komponenter.clear();
        komponenterListeObservable.clear();
        for(Produkt p : liste){
            Komponent k = new Komponent(p.getNavn(), p.getKategori(), p.getPris(), p.isDuplikat());
            komponenter.add(k);
            komponenterListeObservable.add(k);
        }
    }

    public void setKomponenter(Komponent... komponenter) {
        Collections.addAll(this.komponenter, komponenter);
        komponenterListeObservable.addAll(komponenter);
    }

    public ArrayList<Komponent> getProdukter(Komponent... produkter) {
        ArrayList<Komponent> produkterIterator = new ArrayList<Komponent>();

        for(Komponent p : produkter){
            produkterIterator.add(p);
        }
        return produkterIterator;
    }

    public ObservableList<Komponent> getObservableList(){ //henter den globale oservablelist
        return komponenterListeObservable;
    }

    public ArrayList<Komponent> getList(){
        return komponenter;
    }

    //brukes ikke lengre
    public ObservableList<Komponent> createTableFromFile() { //henter fra fil og skriver til global observablelist
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./komponenter.csv"));

            String komponenter;
            while ((komponenter = reader.readLine()) != null) {
                //3.
                String [] komponentfields = komponenter.split(";");
                double pris = Double. parseDouble(komponentfields[2]);
                //4.
                Komponent inputRecord = new Komponent(komponentfields[0], komponentfields[1], pris, Boolean.parseBoolean(komponentfields[3]));
                //5.
                komponenterListeObservable.add(inputRecord);
                this.komponenter.add(inputRecord);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot read file");
        } catch (IOException ex) {
            System.out.println("File not found#" + ex.getCause());
        }

        return komponenterListeObservable;
    }

}
