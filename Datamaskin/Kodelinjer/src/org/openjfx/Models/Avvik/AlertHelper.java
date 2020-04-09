package org.openjfx.Models.Avvik;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.util.Optional;

public class AlertHelper {

    /**
     * @author mariahalvorsen
     * Metode som lager feilmeldinger/informasjon
     */
    public static void showAlertWindow(Alert.AlertType alertType, Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.showAndWait();
    }

    /**
     * Oppretter en alertBox for de feilmeldingene/informasjon som ikke tilhører en knapp.
     * @return
     */
    public static Alert showAlertBox(Alert.AlertType type, String message, String header) {
        Alert confirmationAlert = new Alert(type);
        confirmationAlert.setContentText(message);
        confirmationAlert.setHeaderText(header);
        return confirmationAlert;
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

    /**
     * Denne metoden tar in valgene som kommer inn i alertBoksene.
     * Slik at når man har en confirmation alertbox, skal man kunne konfigurere hva som skal skje
     * om bruker trykker OK eller cancel.
     * @param alert Tar inn en alert
     * @return resultatet.
     */
    public static Optional<ButtonType> okOrCancelOption(Alert alert){
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
}
