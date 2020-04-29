package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
import org.openjfx.Models.Avvik.ValidationHelper;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

public class RegistrerBrukerController {

    @FXML
    TextField txtuser, txtpass, txtpass1;

    @FXML
    Button registrerbtn, tilbakeKnapp;

    @FXML
    Label lblMessage;

    public void registerEvent(ActionEvent actionEvent) throws IOException, AvvikLoggInnBrukernavn, AvvikLoggInnPassord {

        //oppretter et objekt av klassen
        ValidationHelper validationHelper = new ValidationHelper();
        String invalidInputs = validationHelper.getInvalidInput(txtuser.getText(), txtpass.getText(), txtpass1.getText());

        try {
            //validerer brukernavn og passord
            ValiderLoggInn.valideringBrukernavn(txtuser.getText());
            ValiderLoggInn.validerPassord(txtpass.getText());
            //hvis strengen ikke er tom, sett alle feilmeldinger inn i en alertbox
            if (!invalidInputs.isEmpty()) {
                showAlertWindow(Alert.AlertType.ERROR, windowHelper(registrerbtn), "Kunne ikke registrere",
                        invalidInputs + "\nPrøv igjen");
                //hvis ikke så ...
            } else {
                BrukerRegister enBruker = new BrukerRegister(txtuser.getText(), txtpass.getText());
                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                //Pop up melding om at brukeren er registrert
                showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerbtn), "Velkommen",
                        "Bruker opprettet");

                //når du trykker ok, vil du bli sendt tilbake til logg inn siden
                routeToSite(actionEvent, "loggInn");
            }

        } catch (AvvikLoggInnBrukernavn | AvvikLoggInnPassord e) {

            if(e instanceof AvvikLoggInnBrukernavn) {
                lblMessage.setText("Feil i brukernavn!\nBrukernavn må være mellom 5-50 bokstaver langt.");
            }
            else if(e instanceof AvvikLoggInnPassord){
                lblMessage.setText("Feil i passord!\nPassord må være mellom 5-50 bokstaver langt.");
            }
        }
    }




    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }
}
