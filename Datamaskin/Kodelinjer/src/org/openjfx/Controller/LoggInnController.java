package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import org.openjfx.Models.HjelpeKlasser.SceneHåndtering;
import org.openjfx.Models.HjelpeKlasser.BrukerSession;
import org.openjfx.Models.Validering.ValiderLoggInn;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemSjekk.*;
import static org.openjfx.Models.HjelpeKlasser.SceneHåndtering.slideSceneFraBunn;


public class LoggInnController {
    @FXML
    AnchorPane parentContainer;

    @FXML
    TextField txtuser;

    @FXML
    PasswordField txtpass;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLogin;

    @FXML
    Hyperlink registrerbruker, loginAdmin;


    public void initialize(){
        txtpass.setOnKeyPressed(e ->{
            KeyCode key = e.getCode();

            if(key.equals(KeyCode.ENTER) ){
                try {
                    loginEvent();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void loginEvent() throws Exception {

        ValiderLoggInn validerLoggInn = new ValiderLoggInn();

        String validering = validerLoggInn.sjekkUgyldigData(txtuser.getText(), txtpass.getText());

        if(!validering.isEmpty()){
            lblMessage.setText(validering);
        }
        else {
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                BrukerSession.setBrukerSession(txtuser.getText());
                SceneHåndtering.newScene(btnLogin, "scene");
            }
            else {
                if(!checkExistingBruker(txtuser.getText(), "./Brukere.csv")){
                    lblMessage.setText("Bruker eksisterer ikke, vennligst registrer deg under \n" +
                            "eller sjekk om du har skrevet inn feil");
                } else {
                    lblMessage.setText("Feil brukernavn/passord");
                }
            }
        }

    }

    public void registrerbruker(ActionEvent actionEvent) {
        //når den klikkes på, vil du bli sendt til registrer bruker siden
        registrerbruker.setOnMouseClicked(e -> {
            try {
                slideSceneFraBunn(registrerbruker, "registrerBruker", parentContainer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void loginAdmin(ActionEvent actionEvent) {
        //når den klikkes på, vil du bli sendt til logg inn admin siden
        loginAdmin.setOnMouseClicked(e -> {
            try {
                slideSceneFraBunn(loginAdmin, "loggInnAdmin", parentContainer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}
