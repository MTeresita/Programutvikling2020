package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.Model.Interfaces.scenebytte;
import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.Produkt;

import java.util.ArrayList;

public class FXMLController {
    
    @FXML
    private Label label;

    @FXML
    private Button newProduct;

    //proof of concept
    public Konfigurasjon k = new Konfigurasjon(); //lager en generel liste som brukes gjennom kontrolleren

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        //proof of concept
        Produkt p1 = new Produkt("CPU", 3000.0, "Intel i5");
        Produkt p2 = new Produkt("MOTHERBOARD", 1000.0, "Asus z150");
        Produkt p3 = new Produkt("MOTHERBOARD", 2000.0, "Asus a500");


        k.setNyttProdukt(p1);
        k.setNyttProdukt(p2);

        System.out.println(k.toString());

        k.setNyttProdukt(p3);
        System.out.println(k.toString());
        //proof of concept

    }

    @FXML
    public void logInEvent(ActionEvent event) {
        scenebytte.routeToSite(event, "loggInnAdmin");
    }
}
