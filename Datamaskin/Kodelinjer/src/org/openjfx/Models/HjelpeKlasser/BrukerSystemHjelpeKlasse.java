package org.openjfx.Models.HjelpeKlasser;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;


public class BrukerSystemHjelpeKlasse {

    // generell metode som går gjennom filene for å finne bruker.
    public static boolean verifyLogin(String user, String pass, String file) throws IOException {

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
                // splitter filen med ;
                String[] split = line.split(";");
                //hvis brukernavnet og passordet er like --> vil du kunne logge inn
                if (user.equals(split[0]) && pass.equals(split[1])) {
                    found = true;
                }
            }
        }
        return found;
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
                //hvis brukernavnet eksisterer --> er den funnet
                if (username.equals(split[0])) {
                    found = true;
                }

            }
        }
        //ikke funnet, returnerer false
        return found;

    }

    public static void newScene(Button button, String path) throws IOException {
        String BASE_PATH = "/org/openjfx/View/";
        String FILE_ENDING = ".fxml";

        Stage stage = (Stage) button.getScene().getWindow();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(BrukerSystemHjelpeKlasse.class.getResource(BASE_PATH + path + FILE_ENDING));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage.close();
        stage.centerOnScreen();
    }

    public static void slideSceneFromBottom(Hyperlink link, String path, StackPane parentContainer) throws IOException {
        Parent root = FXMLLoader.load(BrukerSystemHjelpeKlasse.class.getResource("/org/openjfx/View/" + path + ".fxml"));
        Scene scene = link.getScene();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}
