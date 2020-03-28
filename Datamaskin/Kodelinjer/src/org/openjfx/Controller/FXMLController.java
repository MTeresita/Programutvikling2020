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
        Produkt p1 = new Produkt("Intel i5", "CPU", 3000.0);
        Produkt p2 = new Produkt("AMD Ryzen", "CPU", 5000.0);
        Produkt p3 = new Produkt("Asus Z-500", "MOTHERBOARD", 2000.0);
        Produkt p4 = new Produkt("AMD X570", "MOTHERBOARD", 4000.0);
        Produkt p5 = new Produkt("Nvidia Geforce 1060", "GPU", 4000.0);
        Produkt p6 = new Produkt("Nvidia Geforece 2080", "GPU", 10000.0);
        Produkt p7 = new Produkt("Corsair 8GB 3200MHZ", "RAM", 1000.0);
        Produkt p8 = new Produkt("Kingston 16GB 3200MHZ", "RAM", 2000.0);
        Produkt p9 = new Produkt("Intel ssd 512GB", "HARD DRIVE", 500.0);
        Produkt p10 = new Produkt("Kingston ssd 1TB", "HARD DRIVE", 900.0);

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
