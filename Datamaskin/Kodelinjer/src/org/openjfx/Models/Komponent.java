package org.openjfx.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;


public class Komponent implements Serializable {

    private SimpleStringProperty navn, kategori;
    private SimpleDoubleProperty pris;
    private boolean duplikat; //true = kan ha flere av komponenten i konfigurasjon, false = kan kun ha ett av komponenten i konfigurasjon

    public ObservableList<Komponent> komponenterTableViews = FXCollections.observableArrayList();


    public Komponent() {
    }

    public Komponent(String navn, String kategori, double pris, boolean duplikat) {
        this.navn = new SimpleStringProperty(navn);
        this.kategori = new SimpleStringProperty(kategori);
        this.pris = new SimpleDoubleProperty(pris);
        this.duplikat = duplikat;
    }

    public boolean isDuplikat() {
        return duplikat;
    }

    public void setDuplikat(boolean duplikat) {
        this.duplikat = duplikat;
    }

    public String getNavn() {
        return navn.get();
    }

    public SimpleStringProperty navnProperty() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    public String getKategori() {
        return kategori.get();
    }

    public SimpleStringProperty kategoriProperty() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori.set(kategori);
    }

    public double getPris() {
        return pris.get();
    }

    public SimpleDoubleProperty prisProperty() {
        return pris;
    }
    public SimpleBooleanProperty duplikatProperty() {
        return new SimpleBooleanProperty(duplikat);
    }

    public void setPris(double pris) {
        this.pris.set(pris);
    }


}
