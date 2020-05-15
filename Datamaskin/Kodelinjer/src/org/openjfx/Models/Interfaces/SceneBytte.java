package org.openjfx.Models.Interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public interface SceneBytte {

    static void routeToSite(ActionEvent event, String path) {
        //generell oppsett for pathing
        String BASE_PATH = "/org/openjfx/View/";
        String FILE_ENDING = ".fxml";


        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = SceneBytte.class.getResource(BASE_PATH + path + FILE_ENDING);
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
        }

        catch (IOException io){
            io.printStackTrace();
        }
        //ikke sikkert denne er nødvending
        System.out.println("Du blir sendt til: " + path);
    }

}
