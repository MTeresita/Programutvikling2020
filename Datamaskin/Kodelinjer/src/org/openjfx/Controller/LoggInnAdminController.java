package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.Models.Interfaces.SceneChanger;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.reloadPage;

public class LoggInnAdminController {

    @FXML
    TextField txtuser;

    @FXML
    PasswordField txtpass;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLogin;

    /**
     * @throws Exception
     * Logg inn metode som sjekker om passord og brukernavn stemmer.
     * Det er kun gjort slik at ADMIN har tilgang til programmet.
     */
    @FXML

    public void loginEvent() throws Exception {
        if (txtuser.getText().equals("ADMIN") && txtpass.getText().equals("ADMIN")) {
            reloadPage(btnLogin, "/org/openjfx/View/registrerProdukt.fxml");
        } else {
            lblMessage.setText("Feil brukernavn eller passord");
        }

    }

    public void forsideEvent(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "scene");
    }
}
