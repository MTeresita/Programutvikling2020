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
public TextField user, pass, produktNavn, kategoriNavn;

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
        adminORuser.getItems().setAll("Admin", "Bruker");
        populateTableWithList();
    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.getObservableList());
    }

    public void registrerbtn(ActionEvent actionEvent) throws IOException {
        // er det admin eller bruker sjek --> hvilken fil skal den til

        if(adminORuser.getSelectionModel().getSelectedItem().equals("Admin")){
            if(!checkExistingBruker(user.getText(), "./Admin.csv")) {
                lblMessage.setText("Ny admin bruker opprettet");

                BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Admin.csv");

                showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(registrerbtn), "Ny admin",
                        "Bruker opprrettet");
                reloadPage(registrerbtn,"/org/openjfx/View/registrerProdukt.fxml" );
            }
            else{
                lblMessage.setText("Bruker eksisterer");
            }

        }
            else {
                if (!checkExistingBruker(user.getText(), "./Brukere.csv")) {
                    BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                    WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                    showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(registrerbtn), "Ny bruker",
                            "Bruker opprrettet");

                    reloadPage(registrerbtn, "/org/openjfx/View/registrerProdukt.fxml");
                } else {
                    lblMessage.setText("Bruker eksisterer");
                }
        }

    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "loggInnBruker");
    }
}
