package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.openjfx.Models.Interfaces.SceneChanger;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.KomponenterListe;

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



    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste av konfigurasjon som brukes gjennom kontrolleren
    public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
         //bruker disse for å resette jobj filen med komponenter fra csv
        /*
        kl.createTableFromFile();
        kl.lagreTilObjectFil();
        */
        
        populateTable();

    }

    public void populateTable() { //henter fra .csv fil
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        //komponenter.setItems(kl.createTableFromFile());
        komponenter.setItems(kl.getObservableList());
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
        System.out.println("Dette er det valgte komponentet: "+valgtKomponent.getNavn()+", "+valgtKomponent.isDuplikat());
        k.setNyttKomponent(valgtKomponent); //legger til i konfigurasjon
        System.out.println(k.toString());
        populateListview();
    }

    @FXML
    public void slettKomponentViaListView(){
        System.out.println(listview.getSelectionModel().getSelectedIndex());

        k.slettKomponent(listview.getSelectionModel().getSelectedIndex());
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
    public void logOutEvent(ActionEvent event) {
        SceneChanger.routeToSite(event, "loggInnBruker");
    }
}
