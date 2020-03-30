package org.openjfx.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

//legger til Implementering av serializable for støtting av binær fil jobj.
public class Produkt implements Serializable {

    private String navn;
    private double pris;
    private String kategori;

    //Oppretter simpleProptery for bruk av ObserableList/listener
    private transient SimpleStringProperty txtNavn;
    private transient SimpleDoubleProperty txtPris;
    private transient SimpleStringProperty txtKategori;

    public Produkt(String navn, double pris, String kategori) {
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;

        //Setter SimpleProperty.
        this.txtNavn = new SimpleStringProperty(navn);
        this.txtPris= new SimpleDoubleProperty(pris);
        this.txtKategori= new SimpleStringProperty(kategori);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    //oppretter gettere og setter for SimpleProperty
    public String getTxtNavn() {
        return txtNavn.get();
    }

    public SimpleStringProperty txtNavnProperty() {
        return txtNavn;
    }

    public void setTxtNavn(String txtNavn) {
        this.txtNavn.set(txtNavn);
    }

    public double getTxtPris() {
        return txtPris.get();
    }

    public SimpleDoubleProperty txtPrisProperty() {
        return txtPris;
    }

    public void setTxtPris(double txtPris) {
        this.txtPris.set(txtPris);
    }

    public String getTxtKategori() {
        return txtKategori.get();
    }

    public SimpleStringProperty txtKategoriProperty() {
        return txtKategori;
    }

    public void setTxtKategori(String txtKategori) {
        this.txtKategori.set(txtKategori);
    }

    //funksjoner:

    //read og writeObject til serialisering.


}
