package org.openjfx.Controller;

import javafx.event.ActionEvent;
import org.openjfx.Model.Interfaces.scenebytte;

public class RegistrerProduktController {

    public void registrerbtn(ActionEvent actionEvent) {
    }

    public void forsideBtn(ActionEvent actionEvent) {
        scenebytte.routeToSite(actionEvent, "scene");
    }
}
