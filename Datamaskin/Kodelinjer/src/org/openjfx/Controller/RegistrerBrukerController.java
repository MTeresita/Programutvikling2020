package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.checkExistingBruker;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

public class RegistrerBrukerController {

    @FXML
    TextField txtuser, txtpass, txtpass1;

    @FXML
    Button registrerbtn, tilbakeKnapp;

    @FXML
    Label lblMessage;

    public void registerEvent(ActionEvent actionEvent) throws IOException {
        if(txtpass.getText().equals(txtpass1.getText())) {
            if (checkExistingBruker(txtuser.getText(), "./Brukere.csv")) {
                showAlertWindow(Alert.AlertType.ERROR, windowHelper(registrerbtn), "Bruker eksisterer",
                        "\nBrukere eksisterer." +
                                "\nPrøv igjen!");

            } else {
                BrukerRegister enBruker = new BrukerRegister(txtuser.getText(), txtpass.getText());
                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerbtn), "Velkommen",
                        "Bruker opprettet");
                routeToSite(actionEvent, "loggInn");
            }
        }
        else {
            lblMessage.setText("Passordene er ikke like");

        }
    }

    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }
}
