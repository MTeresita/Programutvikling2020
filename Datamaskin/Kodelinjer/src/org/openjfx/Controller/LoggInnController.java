package org.openjfx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import org.openjfx.Models.HjelpeKlasser.SceneHåndtering;
import org.openjfx.Models.HjelpeKlasser.BrukerSession;
import org.openjfx.Models.Validering.ValideringLoggInn;

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

    public ValideringLoggInn valideringLoggInn = new ValideringLoggInn();

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
        String validering = valideringLoggInn.sjekkUgyldigData(txtuser.getText(), txtpass.getText());

        if(!validering.isEmpty()){
            lblMessage.setText(validering);
        }
        else {
            if(verifiserLoggInn(txtuser.getText(), txtpass.getText(), "./Brukere.csv")) {
                BrukerSession.setBrukerSession(txtuser.getText());
                SceneHåndtering.nyScene(btnLogin, "scene");
            }
            else {
                if(!sjekkOmBrukerEksiterer(txtuser.getText(), "./Brukere.csv")){
                    lblMessage.setText(txtuser.getText() + ", eksisterer ikke, vennligst registrer deg under "+
                            "eller sjekk om du har skrevet inn feil brukernavn");
                } else {
                    lblMessage.setText("Feil passord");
                }
            }
        }

    }

    public void registrerbruker() {
        //når den klikkes på, vil du bli sendt til registrer bruker siden
        registrerbruker.setOnMouseClicked(e -> {
            try {
                slideSceneFraBunn(registrerbruker, "registrerBruker", parentContainer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void loginAdmin() {
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
