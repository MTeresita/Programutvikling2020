package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.HjelpeKlasser.SceneHåndtering;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;
import static org.openjfx.Models.HjelpeKlasser.SceneHåndtering.slideSceneFraBunn;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

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
    
    public void loginEvent() throws Exception, AvvikLoggInnBrukernavn, FileNotFoundException {

        /*
        try{
            ValiderLoggInn.valideringBrukernavn(txtuser.getText());
            ValiderLoggInn.validerPassord(txtpass.getText());
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                newScene(btnLogin, "scene");
            }
            else{
                lblMessage.setText("Feil brukernavn eller passord.\nHvis du ikke er bruker, vennligst registrer deg nedenfor. ");
            }
        } catch (AvvikLoggInnBrukernavn | FileNotFoundException | AvvikLoggInnPassord e){

            if (e instanceof AvvikLoggInnBrukernavn){
                lblMessage.setText("Feil i brukernavn! Brukernavn må være mellom 5-50 bokstaver langt.");
            }
            else if (e instanceof FileNotFoundException){
                lblMessage.setText("Fil ikke funnet");
            }
            else if(e instanceof AvvikLoggInnPassord){
                lblMessage.setText("Feil i passord! Passord må være mellom 5-50 bokstaver langt.");
            }

        }
         */
        ValiderLoggInn validerLoggInn = new ValiderLoggInn();

        String validering = validerLoggInn.sjekkUgyldigData(txtuser.getText(), txtpass.getText());

        if(!validering.isEmpty()){
            lblMessage.setText(validering);
        }
        else {
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                SceneHåndtering.newScene(btnLogin, "scene");
            }
            else {
                if(!checkExistingBruker(txtuser.getText(), "./Brukere.csv")){
                    lblMessage.setText("Bruker eksisterer ikke, vennligst registrer deg under");
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
