package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.openjfx.Model.Interfaces.scenebytte;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.Produkt;
import org.openjfx.Models.ProduktListe;

import java.util.ArrayList;

import org.openjfx.Models.KomponenterTableView;


public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private Button newProduct;


    //proof of concept
    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste som brukes gjennom kontrolleren
    public ProduktListe pl = new ProduktListe();



    @FXML
    TableView <KomponenterTableView> komponenter;

    @FXML
    TableColumn<KomponenterTableView, String> produktnavn, kategori;

    @FXML
    TableColumn<KomponenterTableView, Double> pris;


    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");


        //proof of concept
        Produkt p1 = new Produkt("CPU", 3000.0, "Intel i5");
        Produkt p2 = new Produkt("CPU", 5000.0, "AMD Ryzen");
        Produkt p3 = new Produkt("MOTHERBOARD", 2000.0, "Asus Z-500");
        Produkt p4 = new Produkt("MOTHERBOARD", 4000.0, "AMD X570");
        Produkt p5 = new Produkt("GPU", 4000.0, "Nvidia Geforce 1060");
        Produkt p6 = new Produkt("GPU", 10000.0, "Nvidia Geforece 2080");
        Produkt p7 = new Produkt("RAM", 1000.0, "Corsair 8GB 3200MHZ");
        Produkt p8 = new Produkt("RAM", 2000.0, "Kingston 16GB 3200MHZ");
        Produkt p9 = new Produkt("HARD DRIVE", 500.0, "Intel ssd 512GB");
        Produkt p10 = new Produkt("HARD DRIVE", 900.0, "Kingston ssd 1TB");

        pl.setProdukter(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);


        ArrayList<Produkt> valgteProdukter = pl.getProdukter(p1, p3, p5, p7, p10); //lager utvalg av valgte produkter
        k.setValgteProdukter(valgteProdukter);

        System.out.println(k.toString());

        k.setNyttProdukt(p4);
        System.out.println(k.toString());
        //proof of concept


        populateTable();
    }

    public void populateTable() {
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());

    }

    @FXML
    public void logInEvent(ActionEvent event) {
        scenebytte.routeToSite(event, "loggInnAdmin");
    }
}
