package org.openjfx.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Produkt extends Object implements Serializable {
  // DENNE ER DROPPET, ERSTATTET MED KOMPONENETERTABLEVIEW
    private String navn;
    private double pris;
    private String kategori;

    public Produkt() {
    }

    public Produkt(String navn, String kategori, double pris){

            this.navn = navn;
            this.pris = pris;
            this.kategori = kategori;

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

        //oppretter gettere og setter for SimpleProperty


        //funksjoner:

        //read og writeObject til serialisering.


}
