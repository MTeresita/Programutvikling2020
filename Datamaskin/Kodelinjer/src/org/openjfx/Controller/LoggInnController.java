package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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



    /**
     * @throws Exception
     * Logg inn metode som sjekker om passord og brukernavn stemmer.
     * Det er kun gjort slik at ADMIN har tilgang til programmet.
     */
    public void initialize(){
    }

    public void loginEvent(ActionEvent event) throws Exception {
        verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv", lblMessage);
        routeToSite(event, "scene");

    }

    public void registrerbruker(ActionEvent actionEvent) {
        registrerbruker.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "registrerBruker");
        });
    }

    public void loginAdmin(ActionEvent actionEvent) {
        loginAdmin.setOnMouseClicked(e -> {
            routeToSite(actionEvent, "loggInnAdmin");
        });
    }
}
