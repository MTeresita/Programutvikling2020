package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.Interfaces.SceneChanger;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.*;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;


public class RegistrerProduktController {
@FXML
public TextField user, pass, produktNavn, kategoriNavn, produktPris;

@FXML
public Button registrerbtn, registrerProduktBtn;

@FXML
public ComboBox adminORuser, kategoriCombobox;

@FXML
public Label lblMessage;

@FXML
TableView <Komponent> komponenter;

@FXML
TableColumn<Komponent, String> produktnavn, kategori;

@FXML
TableColumn<Komponent, Double> pris;

public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
        // setter inn verdier i comboBoxen
        adminORuser.getItems().setAll("Admin", "Bruker");
        populateTableWithList();
    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.getObservableList());
        populateKategoriCombobox();
    }
    public void populateKategoriCombobox(){
        kategoriCombobox.getItems().clear();
        kategoriCombobox.getItems().add("Ny Kategori..."); //legger til "ny kategori..." som førstevalg
        for(Komponent k : kl.getObservableList()){
            if(!kategoriCombobox.getItems().contains(k.getKategori())){ //Om kategori er lagt til fra før, legges den ikke til igjen
                kategoriCombobox.getItems().add(k.getKategori());
            }

        }

    }

    public void registrerbtn(ActionEvent actionEvent) throws IOException {
        // er det admin eller bruker sjek --> hvilken fil skal den til
        if(adminORuser.getSelectionModel().getSelectedItem().equals("Admin")){
            if(!checkExistingBruker(user.getText(), "./Admin.csv")) {

                //en ny bruker blir registrer
                BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                //skrives til Admin.csv
                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Admin.csv");

                //pop up vindu som bekrefter at bruker har blitt opprettet
                showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(registrerbtn), "Ny admin",
                        "Bruker opprrettet");

                //resetter inputs for registrering
                clear();
            }
            else{
                //eksisterer bruker, send feilmelding
                lblMessage.setText("Bruker eksisterer");
            }

        }
            //Er bruker valgt
            else {
                if (!checkExistingBruker(user.getText(), "./Brukere.csv")) {
                    //opprett ny bruker
                    BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                    //skriver til Brukere.csv
                    WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                    //popup vindu som bekrefter at en ny bruker har blitt opprettet
                    showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(registrerbtn), "Ny bruker",
                            "Bruker opprrettet");

                    //resetter inputs for registrering
                    clear();

                }
                else {
                    //eksisterer bruker, send feilmelding
                    lblMessage.setText("Bruker eksisterer");
                }
        }

    }
    public void clear(){
        //resetter inputfeltene
        user.clear();
        pass.clear();
    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "loggInn");
    }

    @FXML
    public void registererProdukt(ActionEvent event){

        if(kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")){
            Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriNavn.getText(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
            kl.getObservableList().add(nyKomponent);
        }
        else{
            Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriCombobox.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
            kl.getObservableList().add(nyKomponent);
        }

        komponenter.refresh();
        populateKategoriCombobox();
    }
}
