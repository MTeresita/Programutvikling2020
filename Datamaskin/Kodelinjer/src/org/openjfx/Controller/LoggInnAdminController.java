package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;

public class LoggInnAdminController {
    @FXML
    StackPane parentContainer;
    @FXML
    TextField txtadminuser;

    @FXML
    PasswordField txtadminpass;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLogin, tilbakeKnapp;

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
            if(e instanceof AvvikLoggInnBrukernavn){
                lblMessage.setText("Feil i brukernavn! Brukernavn må være minst 5 bokstaver langt.");
            }
            else if(e instanceof AvvikLoggInnPassord){
                lblMessage.setText("Feil i passord! Passord må være minst 5 bokstaver langt.");
            }
        }

    }



    public void tilbakeKnapp() throws IOException {
        slideSceneFromTop("loggInn", parentContainer);
    }

}
