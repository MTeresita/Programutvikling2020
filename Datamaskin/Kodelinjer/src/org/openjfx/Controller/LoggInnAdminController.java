package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.newScene;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.verifyLogin;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

public class LoggInnAdminController {
    @FXML
    TextField txtadminuser;

    @FXML
    PasswordField txtadminpass;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLogin;

    public void initialize(){
        txtadminpass.setOnKeyPressed(e ->{
            KeyCode key = e.getCode();
            if(key.equals(KeyCode.ENTER) ){
                try {
                    loginEvent();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void loginEvent() throws IOException, AvvikLoggInnBrukernavn, AvvikLoggInnPassord {

        //Validering av brukernavn og passord:
        try {
            ValiderLoggInn.valideringBrukernavn(txtadminuser.getText());
            ValiderLoggInn.validerPassord(txtadminpass.getText());
            if (verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv")) {
                newScene(btnLogin, "registrerProdukt");
            }

        } catch (AvvikLoggInnBrukernavn | AvvikLoggInnPassord e) {
            lblMessage.setText("Feil i lengden på brukenavn eller passord");

        }

    }



    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }

}
