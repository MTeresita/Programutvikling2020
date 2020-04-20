package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.openjfx.Models.Avvik.AvvikLoggInn;
import org.openjfx.Models.Validering.ValiderLoggInn;

import java.io.FileNotFoundException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.newScene;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.verifyLogin;
import static org.openjfx.Models.Interfaces.SceneChanger.routeToSite;

public class LoggInnController {

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
    
    public void loginEvent() throws Exception, AvvikLoggInn, FileNotFoundException {

        try{
            ValiderLoggInn.valideringBrukernavn(txtuser.getText());
            ValiderLoggInn.validerPassord(txtpass.getText());
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                newScene(btnLogin, "scene");
            }
        } catch (AvvikLoggInn | FileNotFoundException e){
            lblMessage.setText("Feil brukernavn eller passord");
        }

        /*
        if(ValiderLoggInn.valideringBrukernavn(txtuser.getText())){
            if(ValiderLoggInn.validerPassord(txtpass.getText())){
                if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                    newScene(btnLogin, "scene");
                }
                else{
                    lblMessage.setText("Feil brukernavn eller passord");
                }
            }
            else{
                lblMessage.setText("Passordet må være minst 5 bokstaver langt.");
                throw new AvvikLoggInn("Feil i lengde på passordet");
            }
        }
        else{
            lblMessage.setText("Brukernavn må være minst 5 bokstaver langt.");
            throw new AvvikLoggInn("Feil i lengde på brukernavn");
        }*/

    }

    public void registrerbruker(ActionEvent actionEvent) {
        //når den klikkes på, vil du bli sendt til registrer bruker siden
        registrerbruker.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "registrerBruker");
        });
    }

    public void loginAdmin(ActionEvent actionEvent) {
        //når den klikkes på, vil du bli sendt til logg inn admin siden
        loginAdmin.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "loggInnAdmin");
        });
    }

}
