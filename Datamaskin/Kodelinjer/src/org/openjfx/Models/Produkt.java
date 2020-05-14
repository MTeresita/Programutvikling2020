package org.openjfx.Models;

import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Produkt extends Object implements Serializable { //ER EN HJELPEKLASSE FOR KOMPONENTER

    private String navn;
    private double pris;
    private String kategori;
    private int antall;

    public Produkt() {
    }

    public Produkt(String navn, double pris, String kategori, int duplikat){
            this.navn = navn;
            this.pris = pris;
            this.kategori = kategori;
            this.antall = duplikat;
        }

        //TODO en del set metoder blir ikke brukt
        public String getNavn () {
            return navn;
        }

        public void setNavn (String navn){
            this.navn = navn;
        }

        public Double getPris () {
            return pris;
        }

        public void setPris (Double pris){
            this.pris = pris;
        }

        public String getKategori () {
            return kategori;
        }

        public void setKategori (String kategori){
            this.kategori = kategori;
        }

        public int getAntall() {
            return antall;
        }

        public void setAntall(int duplikat) {
            this.antall = duplikat;
        }
}
