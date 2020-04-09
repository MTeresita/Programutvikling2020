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
        //hvis passordene på begge felt er like så..
        if(txtpass.getText().equals(txtpass1.getText())) {

            //sjekker først om bruker eksisterer
            if (checkExistingBruker(txtuser.getText(), "./Brukere.csv")) {

                //eksiterer bruker, send pop up feilmelding
                showAlertWindow(Alert.AlertType.ERROR, windowHelper(registrerbtn), "Bruker eksisterer",
                        "\nBrukere eksisterer." +
                                "\nPrøv igjen!");

            }
            else {
                //eksiterer ikke bruker, blir den opprett og skrevet til Brukere.csv
                BrukerRegister enBruker = new BrukerRegister(txtuser.getText(), txtpass.getText());
                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                //Pop up melding om at brukeren er registrert
                showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerbtn), "Velkommen",
                        "Bruker opprettet");

                //når du trykker ok, vil du bli sendt tilbake til logg inn siden
                routeToSite(actionEvent, "loggInn");
            }
        }
        else {
            //send feilmelding om at passordene ikke er like
            lblMessage.setText("Passordene er ikke like");
        }
    }

    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }
}
