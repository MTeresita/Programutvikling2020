package org.openjfx.Controller;

import javafx.event.ActionEvent;
import org.openjfx.Models.Interfaces.SceneChanger;

public class RegistrerProduktController {

    public void registrerbtn(ActionEvent actionEvent) {
    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "scene");
    }
}
