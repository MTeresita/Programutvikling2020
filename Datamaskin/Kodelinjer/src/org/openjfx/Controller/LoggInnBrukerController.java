package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openjfx.Models.BrukerRegister;
import org.openjfx.Models.Filbehandling.WriteTo;
import org.openjfx.Models.VerifyLogin;

import java.io.*;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.VerifyLogin.checkExistingBruker;
import static org.openjfx.Models.VerifyLogin.verifyLogin;

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
                            "/org/openjfx/View/scene.fxml", btnLoginBruker);
    }



    public void loginEventAdmin(ActionEvent actionEvent) throws IOException {
        verifyLogin(txtadminuser.getText(), txtadminpass.getText(), "./Admin.csv",
                "/org/openjfx/View/scene.fxml" , btnLoginAdmin);
    }

    public void registerEvent(ActionEvent actionEvent) throws IOException {
        if (checkExistingBruker(txtuser1.getText(), "./Brukere.csv")){
            showAlertWindow(Alert.AlertType.ERROR,windowHelper(btnRegistrer), "Bruker eksisterer",
                    "\nPrøv igjen!");
            System.out.println("Bruker eksisterer");
        }
        else {
            BrukerRegister enBruker = new BrukerRegister(txtuser1.getText(), txtpass1.getText());
            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

            showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(btnRegistrer), "Velkommen",
                    "Bruker opprrettet");
            System.out.println("Bruker eksisterer");
            txtuser1.clear();
            txtpass1.clear();

            Stage stage = (Stage) btnRegistrer.getScene().getWindow();

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(VerifyLogin.class.getResource("/org/openjfx/View/loggInnBruker.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            stage.close();
        }
    }
}
