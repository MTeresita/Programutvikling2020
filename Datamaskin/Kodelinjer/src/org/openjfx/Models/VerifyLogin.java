package org.openjfx.Models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;

public class VerifyLogin {
    public static  void verifyLogin(String user, String pass, String file, String path, Button button) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                if (user.equals(split[0]) && pass.equals(split[1])) {
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(VerifyLogin.class.getResource(path));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    stage.close();
                }
                /*else {
                    showAlertWindow(Alert.AlertType.ERROR,windowHelper(button), "Kunne ikke logge inn",
                            "\n Feil brukernavn eller passord" +
                                    "\nPrøv igjen!");
                }

                 */
            }
        }
    }

    public static boolean checkExistingBruker(String user, String file) throws IOException {
        boolean found = false;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                if (user.equals(split[0])) {
                    found = true;
                }

            }
        }
        return found;

    }
}
