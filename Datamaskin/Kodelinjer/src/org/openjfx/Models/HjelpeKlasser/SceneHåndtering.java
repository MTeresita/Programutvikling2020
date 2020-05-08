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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SceneHåndtering {


    public static void slideSceneFraBunn(Hyperlink link, String path, Pane parentContainer) throws IOException {
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

    public static void slideSceneFraTopp(String path, Pane pane) throws IOException {
        Parent root = FXMLLoader.load(BrukerSystemHjelpeKlasse.class.getResource("/org/openjfx/View/" + path + ".fxml"));

        root.translateYProperty().set(-1 * pane.getHeight());
        pane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
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
}
