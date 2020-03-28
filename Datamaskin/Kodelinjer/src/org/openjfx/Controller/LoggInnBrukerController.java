package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.openjfx.Model.Interfaces.scenebytte;

public class LoggInnBrukerController {

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
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        if (txtuser.getText().equals("ADMIN") && txtpass.getText().equals("ADMIN")) {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/org/openjfx/View/scene.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            stage.close();
        } else {
            lblMessage.setText("Feil brukernavn eller passord");
        }

    }

    public void forsideEvent(ActionEvent actionEvent) {
        scenebytte.routeToSite(actionEvent, "scene");
    }
}
