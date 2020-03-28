package org.openjfx.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class KomponenterTableView {

    private SimpleStringProperty navn, kategori;
    private SimpleDoubleProperty pris;

    public KomponenterTableView() {
    }

    public KomponenterTableView(String navn, String kategori, double pris) {
        this.navn = new SimpleStringProperty(navn);
        this.kategori = new SimpleStringProperty(kategori);
        this.pris = new SimpleDoubleProperty(pris);
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
    public ObservableList<KomponenterTableView> komponenterTableViews = FXCollections.observableArrayList();
    public ObservableList<KomponenterTableView> createTableFromFile() {
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
                komponenterTableViews.add(inputRecord);

            }
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot read file");
        } catch (IOException ex) {
            System.out.println("File not found#" + ex.getCause());
        }

        return komponenterTableViews;
    }
}
