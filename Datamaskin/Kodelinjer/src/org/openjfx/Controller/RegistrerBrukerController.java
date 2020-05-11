package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.HjelpeKlasser.SceneHåndtering.slideSceneFraTopp;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

public class RegistrerBrukerController {
    @FXML
    AnchorPane parentContainer;

    @FXML
    TextField txtuser, txtpass, txtpass1;

    @FXML
    Button registrerbtn, tilbakeKnapp;

    @FXML
    Label lblMessage;


    public void registerEvent(ActionEvent actionEvent)  {

        ValiderLoggInn validerLoggInn = new ValiderLoggInn();
        String ugyldigRegistreering =
                validerLoggInn.sjekkUgyldigRegistrering(txtuser.getText(), txtpass.getText(), txtpass1.getText());

        if(!ugyldigRegistreering.isEmpty()){
            lblMessage.setText(ugyldigRegistreering);
        }
        else {
            BrukerRegister enBruker = new BrukerRegister(txtuser.getText(), txtpass.getText());
            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv", true);

            //Pop up melding om at brukeren er registrert
            showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerbtn), "Velkommen",
                    "Bruker opprettet");

            //når du trykker ok, vil du bli sendt tilbake til logg inn siden
            routeToSite(actionEvent, "loggInn");
        }

    }



    public void tilbakeKnapp(ActionEvent actionEvent) throws IOException {
        slideSceneFraTopp("loggInn", parentContainer);
    }
}
