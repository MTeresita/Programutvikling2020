package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.openjfx.Model.Interfaces.scenebytte;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.KomponenterListe;

import java.util.ArrayList;

import org.openjfx.Models.KomponenterTableView;


public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private Button newProduct;


    //proof of concept
    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste som brukes gjennom kontrolleren
    public KomponenterListe kl = new KomponenterListe();




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
        KomponenterTableView p1 = new KomponenterTableView("Intel i5", "CPU", 3000.0);
        KomponenterTableView p2 = new KomponenterTableView("AMD Ryzen", "CPU", 5000.0);
        KomponenterTableView p3 = new KomponenterTableView("Asus Z-500", "MOTHERBOARD", 2000.0);
        KomponenterTableView p4 = new KomponenterTableView("AMD X570", "MOTHERBOARD", 4000.0);
        KomponenterTableView p5 = new KomponenterTableView("Nvidia Geforce 1060", "GPU", 4000.0);
        KomponenterTableView p6 = new KomponenterTableView("Nvidia Geforece 2080", "GPU", 10000.0);
        KomponenterTableView p7 = new KomponenterTableView("Corsair 8GB 3200MHZ", "RAM", 1000.0);
        KomponenterTableView p8 = new KomponenterTableView("Kingston 16GB 3200MHZ", "RAM", 2000.0);
        KomponenterTableView p9 = new KomponenterTableView("Intel ssd 512GB", "HARD DRIVE", 500.0);
        KomponenterTableView p10 = new KomponenterTableView("Kingston ssd 1TB", "HARD DRIVE", 900.0);

        kl.setKomponenter(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10);


        ArrayList<KomponenterTableView> valgteProdukter = kl.getProdukter(p1, p3, p5, p7, p10); //lager utvalg av valgte produkter
        k.setValgteProdukter(valgteProdukter);

        System.out.println(k.toString());

        k.setNyttProdukt(p4);
        System.out.println(k.toString());
        //proof of concept

        populateTable();
    }


    public void populateTable() { //henter fra .csv fil

        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.createTableFromFile());
    }

    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.getObservableList());
    }

    @FXML
    public void leggTilKomponent(ActionEvent event) {
        System.out.println("trykket på legg til komponent");
        //k.setNyttProdukt(); //her skal det valgte produktet fra tablview sendes som parameter
    }
    @FXML
    public void logOutEvent(ActionEvent event) {
        scenebytte.routeToSite(event, "loggInnBruker");
    }
}
