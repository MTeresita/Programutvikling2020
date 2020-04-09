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

    
    public void loginEvent(ActionEvent event) throws Exception {
        //bruker verifyLogin metoden, går gjennom fil og sender ut feilmeldinger til lblMessage.
        verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv", lblMessage);
        routeToSite(event, "scene");

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
