package org.openjfx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.openjfx.Models.HjelpeKlasser.SceneHåndtering;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemSjekk.*;
import static org.openjfx.Models.HjelpeKlasser.SceneHåndtering.slideSceneFraTopp;

public class LoggInnAdminController {
    @FXML
    AnchorPane parentContainer;
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

    public void loginEvent() throws IOException {

        ValiderLoggInn validerLoggInn = new ValiderLoggInn();

        String validering = validerLoggInn.sjekkUgyldigData(txtadminuser.getText(), txtadminpass.getText());

        if(!validering.isEmpty()){
            lblMessage.setText(validering);
        }
        else {
            if(verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv")) {
                SceneHåndtering.newScene(btnLogin, "registrerProdukt");
            }
            else {
                if(!checkExistingBruker(txtadminuser.getText(), "./Admin.csv")){
                    lblMessage.setText(txtadminuser.getText() + "eksisterer ikke");
                } else {
                    lblMessage.setText("Feil brukernavn/passord");
                }
            }
        }

    }

    public void tilbakeKnapp() throws IOException {
        slideSceneFraTopp("loggInn", parentContainer);    }

}
