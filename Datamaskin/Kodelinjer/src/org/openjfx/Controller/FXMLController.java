package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.openjfx.Model.Interfaces.scenebytte;
import org.openjfx.Models.KomponenterTableView;

public class FXMLController {
    
    @FXML
    private Label label;

    @FXML
    private Button newProduct;

    @FXML
    TableView <KomponenterTableView> komponenter;

    @FXML
    TableColumn<KomponenterTableView, String> produktnavn, kategori;

    @FXML
    TableColumn<KomponenterTableView, Double> pris;
    
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
        populateTable();
    }

    public void populateTable() {
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
    }

    @FXML
    public void logInEvent(ActionEvent event) {
        scenebytte.routeToSite(event, "loggInnAdmin");
    }
}
