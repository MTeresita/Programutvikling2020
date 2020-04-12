package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.Interfaces.SceneChanger;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.*;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;


public class RegistrerProduktController {
@FXML
public TextField user, pass;

@FXML
public Button registrerbtn, nyttproduktbtn;

@FXML
public ComboBox adminORuser;

@FXML
public Label lblMessage;


    public void initialize() {
        // setter inn verdier i comboBoxen
        adminORuser.getItems().setAll("Admin", "Bruker");
    }

    public void registrerbtn() throws IOException {
        // er det admin eller bruker sjekk --> hvilken fil skal den til
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

    public void nyttProdbtn(ActionEvent event) {
    }
}
