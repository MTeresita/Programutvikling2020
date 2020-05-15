package org.openjfx.Models.Avvik;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Window;


public class AlertHelper {

    /**
     * @author mariahalvorsen
     * Metode som lager feilmeldinger/informasjon
     */
    public static void visAlertVindu(Alert.AlertType alertType, Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.showAndWait();
    }

    /**
     * Vindu hjelper for de meldingene som skal vises til bruker.
     * @param button Tar inn en knapp den skal forholde seg til
     * @return Et vindu med meldinger til bruker
     */
    public static Window windowHelper(Button button){
        Window window = button.getScene().getWindow();
        return window;
    }

}
