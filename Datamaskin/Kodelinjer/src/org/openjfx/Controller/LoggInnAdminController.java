package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.openjfx.Models.Avvik.AvvikLoggInn;
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

    public void loginEvent() throws IOException, AvvikLoggInn {

        //Validering av brukernavn og passord:
        if(ValiderLoggInn.valideringBrukernavn(txtadminuser.getText()) == true){


            if(ValiderLoggInn.validerPassord(txtadminpass.getText()) == true){
                //sjekker om brukerinformasjon passer med det som er i filen.
                //bruker verifyLogin metoden, går gjennom fil og sender ut feilmeldinger til lblMessage.
                if(verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv")) {
                    newScene(btnLogin, "registrerProdukt");
                }
                else{
                    lblMessage.setText("Feil brukernavn eller passord");

                }
            }
            else{
                lblMessage.setText("Passord må være minst 5 bokstaver langt.");
                throw new AvvikLoggInn("Feil i lengden på passord.");
            }

        }
        else{
            lblMessage.setText("Brukernavn må være minst 5 bokstaver");
            throw new AvvikLoggInn("Feil i lengden på brukernavn.");
        }


    }


    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }

}
