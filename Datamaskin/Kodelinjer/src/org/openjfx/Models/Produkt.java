package org.openjfx.Models;

import java.io.Serializable;

public class Produkt extends Object implements Serializable {

    private String navn;
    private double pris;
    private String kategori;
    private boolean duplikat;

    public Produkt() {
    }

    public Produkt(String navn, double pris, String kategori, boolean duplikat){
            this.navn = navn;
            this.pris = pris;
            this.kategori = kategori;
            this.duplikat = duplikat;
        }

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

        public boolean isDuplikat() {
            return duplikat;
        }

        public void setDuplikat(boolean duplikat) {
            this.duplikat = duplikat;
        }
}
