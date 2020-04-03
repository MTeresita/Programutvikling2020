package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KomponenterListe {


    private ArrayList<KomponenterTableView> komponenter = new ArrayList<KomponenterTableView>();
    private ObservableList<KomponenterTableView> komponenterListeObservable = FXCollections.observableArrayList();;

    //konstruktør:

    public KomponenterListe(){

    }

   /* //funksjoner:
    public void leggTilProdukt(Produkt... produkter){

    }*/

    public void setKomponenter(KomponenterTableView... komponenter) {
        Collections.addAll(this.komponenter, komponenter);
        komponenterListeObservable.addAll(komponenter);
    }

    public ArrayList<KomponenterTableView> getProdukter(KomponenterTableView... produkter) {
        ArrayList<KomponenterTableView> produkterIterator = new ArrayList<KomponenterTableView>();

        for(KomponenterTableView p : produkter){
            produkterIterator.add(p);
        }
        return produkterIterator;
    }

    public ObservableList<KomponenterTableView> getObservableList(){ //henter den globale oservablelist
        return komponenterListeObservable;
    }

    public ObservableList<KomponenterTableView> createTableFromFile() { //henter fra fil og skriver til global observablelist
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./komponenter.csv"));

            String komponenter;
            while ((komponenter = reader.readLine()) != null) {
                //3.
                String [] komponentfields = komponenter.split(";");
                double pris = Double. parseDouble(komponentfields[2]);
                //4.
                KomponenterTableView inputRecord = new KomponenterTableView(komponentfields[0], komponentfields[1],
                        pris);
                //5.
                komponenterListeObservable.add(inputRecord);

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot read file");
        } catch (IOException ex) {
            System.out.println("File not found#" + ex.getCause());
        }

        return komponenterListeObservable;
    }

}
