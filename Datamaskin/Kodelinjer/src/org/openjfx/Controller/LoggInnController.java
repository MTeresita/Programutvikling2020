package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
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
    
    public void loginEvent() throws Exception, AvvikLoggInnBrukernavn, FileNotFoundException {

        try{
            ValiderLoggInn.valideringBrukernavn(txtuser.getText());
            ValiderLoggInn.validerPassord(txtpass.getText());
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                newScene(btnLogin, "scene");
            }
        } catch (AvvikLoggInnBrukernavn | FileNotFoundException | AvvikLoggInnPassord e){

            if (e instanceof AvvikLoggInnBrukernavn){
                lblMessage.setText("Feil i brukernavn! Brukernavn må være minst 5 bokstaver langt.");
            }
            else if (e instanceof FileNotFoundException){
                lblMessage.setText("Fil ikke funnet");
            }
            else if(e instanceof AvvikLoggInnPassord){
                lblMessage.setText("Feil i passord! Passord må være minst fem bokstaver langt.");
            }

        }
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
