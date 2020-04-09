package org.openjfx.HjelpeKlasser;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BrukerSystemHjelpeKlasse {
    public static  void verifyLogin(String user, String pass, String file, String path,
                                    Button button, Label label) throws IOException {

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
                    reloadPage(button, path);
                }
                else {
                    label.setText("Feil brukernavn eller passord");
                }

            }
        }
    }

    public static boolean checkExistingBruker(String username, String file) throws IOException {
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
                if (username.equals(split[0])) {
                    found = true;
                }

            }
        }
        return found;

    }
    public static void reloadPage(Button button, String path) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(BrukerSystemHjelpeKlasse.class.getResource(path));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage.close();
    }
}
