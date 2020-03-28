package org.openjfx.Models;

public class Produkt {

    private String navn;
    private Double pris;
    private String kategori;

    public Produkt(String navn, Double pris, String kategori) {
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;
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

    //funksjoner:




}
