package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import org.openjfx.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;

import java.io.*;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.HjelpeKlasser.BrukerSystemHjelpeKlasse.*;

public class LoggInnBrukerController {

    @FXML
    TextField txtuser, txtadminuser, txtuser1;

    @FXML
    PasswordField txtpass, txtadminpass, txtpass1;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLoginBruker, btnLoginAdmin, btnRegistrer;

    @FXML

    public void loginEventBruker() throws Exception {
        verifyLogin(txtuser.getText(), txtpass.getText(), "./Brukere.csv",
                            "/org/openjfx/View/scene.fxml", btnLoginBruker, lblMessage);
    }



    public void loginEventAdmin(ActionEvent actionEvent) throws IOException {
        verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv",
                "/org/openjfx/View/registrerProdukt.fxml" , btnLoginAdmin, lblMessage);

    }

    public void registerEvent(ActionEvent actionEvent) throws IOException {
        if (checkExistingBruker(txtuser1.getText(), "./Brukere.csv")){
            showAlertWindow(Alert.AlertType.ERROR,windowHelper(btnRegistrer), "Bruker eksisterer",
                    "\nBrukere eksisterer." +
                            "\nPrøv igjen!");
            txtuser1.clear();
            txtpass1.clear();
        }
        else {
            BrukerRegister enBruker = new BrukerRegister(txtuser1.getText(), txtpass1.getText());
            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

            showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(btnRegistrer), "Velkommen",
                    "Bruker opprrettet");
            System.out.println("Bruker eksisterer");
            reloadPage(btnRegistrer, "/org/openjfx/View/loggInnBruker.fxml");


        }
    }
}
