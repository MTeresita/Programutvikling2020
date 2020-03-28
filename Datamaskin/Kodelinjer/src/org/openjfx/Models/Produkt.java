package org.openjfx.Models;

public class Produkt {
    private String navn;
    private String kategori;
    private double pris;

    public Produkt(String kategori, double pris, String navn) {
        this.navn = navn;
        this.kategori = kategori;
        this.pris = pris;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getKategori() {
        return kategori;
    }

    public double getPris() {
        return pris;
    }
    public String getNavn() {
        return navn;
    }
}
