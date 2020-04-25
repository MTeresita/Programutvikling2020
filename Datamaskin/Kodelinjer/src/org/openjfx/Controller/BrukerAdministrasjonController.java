package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;

import java.io.IOException;

import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.slideSceneFromRight;

public class BrukerAdministrasjonController {
    @FXML
    AnchorPane parentContainer;

    @FXML
    TextField filteredData, user, pass, pass1;

    @FXML
    TableView<BrukerRegister> brukere;

    @FXML
    TableColumn<BrukerRegister, String> brukernavn;

    @FXML
    Button slettBruker, registrerBruker, loggOut,tilbake;


    public void initialize(){
        populateTable();
    }

    public void populateTable(){
        BrukerRegister brukerRegister = new BrukerRegister();
        brukernavn.setCellValueFactory(cellData -> cellData.getValue().brukernavnProperty());
        brukere.setItems(brukerRegister.createTableFromFile());
    }

    public void forsideBtn(ActionEvent event) {
    }
    
    public void slettBruker(ActionEvent event) {
    }

    public void registrerbtn(ActionEvent event) {
    }

    public void tilbake() throws IOException {
    }
}
