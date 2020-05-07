package org.openjfx.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.openjfx.Models.Avvik.AlertHelper.*;
import org.openjfx.Models.Filbehandling.FilHenting.FilHentingBruker;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.HjelpeKlasser.BrukerSession;
import org.openjfx.Models.Interfaces.SceneChanger;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.KomponenterListe;

import org.openjfx.Models.Komponent;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.openjfx.Models.KomponenterListe.searchTableView;


public class FXMLController {

    @FXML
    private Label label;

    @FXML
    private Label lblSluttPris;

    @FXML
    private Button newProduct;

    @FXML
    public ComboBox filListe;

    @FXML
    private Button leggTilProdukt;

    @FXML
    private ListView<String> listview;

    @FXML
    TextField filterData;

    @FXML
    TableView <Komponent> komponenter;

    @FXML
    TableColumn<Komponent, String> produktnavn, kategori;

    @FXML
    TableColumn<Komponent, Double> pris;



    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste av konfigurasjon som brukes gjennom kontrolleren
    public KomponenterListe kl = new KomponenterListe();


    public void initialize() {
         //bruker disse for å resette jobj filen med komponenter fra csv
        /*
        kl.createTableFromFile();
        kl.lagreTilObjectFil();
        */
        populateTable();
        searchTableView(kl, filterData, komponenter);
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
        Komponent valgtKomponent = komponenter.getSelectionModel().getSelectedItem(); //henter valgt komponent
        System.out.println("Dette er det valgte komponentet: "+valgtKomponent.getNavn()+", "+valgtKomponent.isDuplikat());
        k.setNyttKomponent(valgtKomponent); //legger til i konfigurasjon
        System.out.println(k.toString());
        populateListview();
    }

    @FXML
    public void slettKomponentViaListView(){
        System.out.println(listview.getSelectionModel().getSelectedIndex());
        try {
            k.slettKomponent(listview.getSelectionModel().getSelectedIndex());
            populateListview();
        } catch(Exception e){

        }
    }

    public void populateListview(){ //legger ut komponeter fra konfigurasjon sin ArrayList
        listview.getItems().clear();
        for(Komponent ktv : k.getKonfigListe()){
            listview.getItems().add(ktv.getNavn() + "\n" +ktv.getPris()+" NOK");
        }
        listview.refresh();

        lblSluttPris.setText(Double.toString(k.getSluttPris())+" NOK");
    }

    public void logOutEvent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekreft utlogging");
        alert.setHeaderText("Endringer gjort uten å trykke lagre vil bli slettet!");
        alert.setContentText("Er du sikker på at du vil logge ut?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneChanger.routeToSite(event, "loggInn");
        } else {
            alert.close();
        }

    }

    public void populateFilListeComboBox(){
        filListe.getItems().add("Ny Fil..."); //legger til "Ny Fil..." som førstevalg

    }
    private String session = BrukerSession.getBrukerSession(); //henter brukersession/brukernavnet til den som er logget inn
    public void lagreKonfigurasjon() throws IOException {

        WriteTo.writeToCSVFile(new WriteTo(), k, "Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/"+session+"/konfigtest.csv", false);

    }
    public void hentKonfigurasjon() throws IOException {
        //gjør parsing, sett som en kompoentliste
        FilHentingBruker fhb = new FilHentingBruker();
        ArrayList<Komponent> kompliste;
        
        kompliste = fhb.lesingFraFil("Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/"+session+"/konfigtest.csv");

        k.setKonfigListe(kompliste);
        populateListview();
        //k.setKonfigListe(komponentlisten);
        //k.setKonfigListeOvservable(komponentlisten);
    }
}
