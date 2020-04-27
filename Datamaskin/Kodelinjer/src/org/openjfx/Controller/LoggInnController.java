package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.ValideringBruker;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;

public class LoggInnController {

    @FXML
    AnchorPane anchorRoot;

    @FXML
    StackPane parentContainer;

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

        }*/

        ValideringBruker valideringBruker = new ValideringBruker();
        String invalidInputs = valideringBruker.getLogInInvalidInputs(txtuser.getText(), txtpass.getText());

        if(!invalidInputs.isEmpty()){
            lblMessage.setText(invalidInputs);
        }
        else {
            if(verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                newScene(btnLogin, "scene");
            }
            else{
                lblMessage.setText(invalidInputs);

            }
        }

    }

    public void registrerbruker(ActionEvent actionEvent) throws IOException {
        /*//når den klikkes på, vil du bli sendt til registrer bruker siden
        registrerbruker.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "registrerBruker");
        });
         */

        slideSceneFromBottom(registrerbruker, "registrerBruker", parentContainer);

    }

    public void loginAdmin(ActionEvent actionEvent) throws IOException {
        //når den klikkes på, vil du bli sendt til logg inn admin siden
        /*loginAdmin.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "loggInnAdmin");
        });
         */

        slideSceneFromBottom(loginAdmin, "loggInnAdmin", parentContainer);
    }

}
