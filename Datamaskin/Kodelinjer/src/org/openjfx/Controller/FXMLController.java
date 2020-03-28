package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.openjfx.Models.Interfaces.SceneChanger;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.ProduktListe;

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
        populateTable();
    }


    public void populateTable() {
        KomponenterTableView alleKomponenter = new KomponenterTableView();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());

        komponenter.setItems(alleKomponenter.createTableFromFile());

    }

    @FXML
    public void leggTilKomponent(ActionEvent event) {
        System.out.println("trykket på legg til komponent");
        //k.setNyttProdukt(); //her skal det valgte produktet fra tablview sendes som parameter
    }
    @FXML
    public void logOutEvent(ActionEvent event) {
        SceneChanger.routeToSite(event, "loggInnBruker");
    }
}
