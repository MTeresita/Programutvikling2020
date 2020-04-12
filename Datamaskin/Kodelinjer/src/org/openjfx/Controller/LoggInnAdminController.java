package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

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

    public void loginEvent() throws IOException {
        //bruker verifyLogin metoden, går gjennom fil og sender ut feilmeldinger til lblMessage.
        if(verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv")) {
            newScene(btnLogin, "scene");
        }
        else{
            lblMessage.setText("Feil brukernavn eller passord");
        }
    }


    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }

}
