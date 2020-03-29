package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openjfx.Models.BrukerRegister;
import org.openjfx.Models.Filbehandling.WriteTo;
import org.openjfx.Models.Interfaces.SceneChanger;
import org.openjfx.Models.VerifyLogin;

import java.io.BufferedReader;
import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.showAlertWindow;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.VerifyLogin.*;


public class RegistrerProduktController {
@FXML
public TextField user, pass;

@FXML
public Button registrerbtn;

@FXML
public ComboBox adminORuser;

@FXML
public Label lblMessage;


    public void initialize() {
        adminORuser.getItems().setAll("Admin", "Bruker");


    }

    public void registrerbtn(ActionEvent actionEvent) throws IOException {
        // er det admin eller bruker sjek --> hvilken fil skal den til
        /*if (checkExistingBruker(txtuser1.getText(), "./Brukere.csv")){
            showAlertWindow(Alert.AlertType.ERROR,windowHelper(btnRegistrer), "Bruker eksisterer",
                    "\nBrukere eksisterer." +
                            "\nPrøv igjen!");
            System.out.println("Bruker eksisterer");
        }
        else {
            BrukerRegister enBruker = new BrukerRegister(txtuser1.getText(), txtpass1.getText());
            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

            showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(btnRegistrer), "Velkommen",
                    "Bruker opprrettet");
            System.out.println("Bruker eksisterer");
            txtuser1.clear();
            txtpass1.clear();*/
        if(adminORuser.getSelectionModel().getSelectedItem().equals("Admin")){
            if(!checkExistingBruker(user.getText(), "./Admin.csv")) {
                lblMessage.setText("Ny admin bruker opprettet");

                BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());
                WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Admin.csv");
                user.clear();
                pass.clear();
                showAlertWindow(Alert.AlertType.INFORMATION,windowHelper(registrerbtn), "Velkommen",
                        "Bruker opprrettet");
                reloadPage(registrerbtn,"/org/openjfx/View/registrerProdukt.fxml" );
            }
            else{
                lblMessage.setText("Bruker eksisterer");
            }

        }
        if(adminORuser.getSelectionModel().getSelectedItem().equals("Bruker")){
            if(!checkExistingBruker(user.getText(), "./Brukere.csv")){
            lblMessage.setText("Ny bruker opprettet");
            BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());
            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Bruker.csv");
            user.clear();
            pass.clear();
            reloadPage(registrerbtn, "/org/openjfx/View/registrerProdukt.fxml");
            }
            else{
                lblMessage.setText("Bruker eksisterer");
            }
        }

        //sjekk om bruker eksiterer fra før

        //opprett bruker
    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "loggInnBruker");
    }
}
