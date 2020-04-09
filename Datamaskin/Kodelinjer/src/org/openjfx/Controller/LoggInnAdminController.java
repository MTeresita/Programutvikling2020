package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    public void loginEvent(ActionEvent actionEvent) throws IOException {
        //bruker verifyLogin metoden, går gjennom fil og sender ut feilmeldinger til lblMessage.
        verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv", lblMessage);
        routeToSite(actionEvent, "registrerProdukt");
    }


    public void tilbakeKnapp(ActionEvent actionEvent) {
        routeToSite(actionEvent, "loggInn");
    }
}
