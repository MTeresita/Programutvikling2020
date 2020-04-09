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
public Button registrerbtn;

@FXML
public ComboBox adminORuser;

@FXML
public Label lblMessage;


    public void initialize() {
        adminORuser.getItems().setAll("Admin", "Bruker");


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
