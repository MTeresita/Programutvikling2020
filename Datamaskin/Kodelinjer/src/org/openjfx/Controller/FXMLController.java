package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.openjfx.Model.Interfaces.scenebytte;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.KomponenterListe;

import java.util.ArrayList;

import org.openjfx.Models.KomponenterTableView;


public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private Label lblSluttPris;

    @FXML
    private Button newProduct;


    @FXML
    private Button leggTilProdukt;

    @FXML
    private ListView<String> listview;

    @FXML
    TableView <KomponenterTableView> komponenter;

    @FXML
    TableColumn<KomponenterTableView, String> produktnavn, kategori;

    @FXML
    TableColumn<KomponenterTableView, Double> pris;



    //proof of concept
    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste av konfigurasjon som brukes gjennom kontrolleren
    public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        /*
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


        //ArrayList<KomponenterTableView> valgteProdukter = kl.getProdukter(p1, p3, p5, p7, p10); //lager utvalg av valgte produkter
        //k.setValgteKomponenter(valgteProdukter);

        //System.out.println(k.toString());

        //k.setNyttKomponent(p4);
        //System.out.println(k.toString());
        //proof of concept
        */
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
    public void leggTilKomponentEvent(ActionEvent event){ //henter valgt komponent fra tableview fra knappetrykk på "legg til komponent"
        KomponenterTableView valgtKomponent = komponenter.getSelectionModel().getSelectedItem(); //henter valgt komponent
        System.out.println("Dette er det valgte komponentet: "+valgtKomponent.getNavn());
        k.setNyttKomponent(valgtKomponent); //legger til i konfigurasjon
        System.out.println(k.toString());
        populateListview();
    }

    public void populateListview(){ //legger ut komponeter fra konfigurasjon sin ArrayList
        listview.getItems().clear();
        for(KomponenterTableView ktv : k.getKonfigListe()){
            listview.getItems().add(ktv.getNavn() + "\n" +ktv.getPris()+" NOK");
        }
        listview.refresh();

        lblSluttPris.setText(Double.toString(k.getSluttPris())+" NOK");
    }

    @FXML
    public void slettKomponentViaListView(){
        System.out.println(listview.getSelectionModel().getSelectedIndex());
        k.slettKomponent(listview.getSelectionModel().getSelectedIndex());
        populateListview();
    }

    @FXML
    public void logOutEvent(ActionEvent event) {
        scenebytte.routeToSite(event, "loggInnBruker");
    }
}
