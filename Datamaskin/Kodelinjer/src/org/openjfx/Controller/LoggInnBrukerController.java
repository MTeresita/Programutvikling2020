package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openjfx.Model.Interfaces.scenebytte;

import java.io.*;
import java.util.Scanner;

public class LoggInnBrukerController {

    @FXML
    TextField txtuser;

    @FXML
    PasswordField txtpass;

    @FXML
    Label lblMessage;

    @FXML
    Button btnLoginBruker, btnLoginAdmin, btnRegistrer;

    @FXML

    public void loginEventBruker() throws Exception {
        Stage stage = (Stage) btnLoginBruker.getScene().getWindow();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader("./Brukere.csv"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (reader != null) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] split = line.split(";");
                    if (txtuser.getText().equals(split[0]) && txtpass.getText().equals(split[1])) {
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/org/openjfx/View/scene.fxml"));
                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        stage.close();
                    }
                }
            }

    }

    public void loginEventAdmin(ActionEvent actionEvent) {
    }

    public void registerEvent(ActionEvent actionEvent) {
    }
}
