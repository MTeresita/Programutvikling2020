package org.openjfx.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;


public class Komponent implements Serializable {

    private SimpleStringProperty navn, kategori;
    private SimpleDoubleProperty pris;
    private SimpleIntegerProperty antall;

    public ObservableList<Komponent> komponenterTableViews = FXCollections.observableArrayList();


    public Komponent() {
    }

    public Komponent(String navn, String kategori, double pris, int antall) {
        this.navn = new SimpleStringProperty(navn);
        this.kategori = new SimpleStringProperty(kategori);
        this.pris = new SimpleDoubleProperty(pris);
        this.antall = new SimpleIntegerProperty(antall);
    }

    public int getAntall() {
        return antall.get();
    }
    public void setAntall(int antall) {
        this.antall.set(antall);
    }
    public SimpleIntegerProperty antallProperty() {
        return antall;
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
    public void setPris(double pris) {
        this.pris.set(pris);
    }

    @Override
    public String toString() {
        return "Komponent{" +
                "navn=" + navn +
                ", kategori=" + kategori +
                ", pris=" + pris +
                ", duplikat=" + antall +
                '}';
    }
}
