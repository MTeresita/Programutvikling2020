package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.Models.Avvik.*;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Interfaces.SceneChanger;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.Validering.ValiderLoggInn;
import org.openjfx.Models.Validering.ValideringKomponent;

import java.io.IOException;

import static org.openjfx.Models.Avvik.AlertHelper.*;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.checkExistingBruker;
import static org.openjfx.Models.KomponenterListe.searchTableView;


public class RegistrerProduktController {
@FXML
public TextField user, pass, produktNavn, kategoriNavn, produktPris, filteredData;

@FXML
public Button registrerBruker,  registrerProduktBtn, slettRader, lagreTilFil;

@FXML
public ComboBox adminORuser, kategoriCombobox;

@FXML
public Label lblMessage;

@FXML
TableView <Komponent> komponenter;

@FXML
TableColumn<Komponent, String> produktnavn, kategori;

@FXML
TableColumn<Komponent, Double> pris;

public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
        populateTableWithList();
        komponenter.getSortOrder().add(pris);
        searchTableView(kl, filteredData, komponenter);
    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.getObservableList());
        populateKategoriCombobox();
    }
    public void populateKategoriCombobox(){
        kategoriCombobox.getItems().clear();
        kategoriCombobox.getItems().add("Ny Kategori..."); //legger til "ny kategori..." som førstevalg
        for(Komponent k : kl.getObservableList()){
            if(!kategoriCombobox.getItems().contains(k.getKategori())){ //Om kategori er lagt til fra før, legges den ikke til igjen
                kategoriCombobox.getItems().add(k.getKategori());
            }

        }

    }

    public void registrerbtn(ActionEvent actionEvent) throws IOException {
        ValidationHelper validationHelper = new ValidationHelper();
        String invalidInputs = validationHelper.getInvalidInput(user.getText(), pass.getText(), pass.getText());

        // er det admin eller bruker sjek --> hvilken fil skal den til
        String value = String.valueOf(adminORuser.getValue());

        switch (value){
            case "Admin":
                try{
                    ValiderLoggInn.valideringBrukernavn(user.getText());
                    ValiderLoggInn.validerPassord(pass.getText());
                    if(!checkExistingBruker(user.getText(), "./Admin.csv")) {
                        lblMessage.setText("");
                        //en ny bruker blir registrer
                        BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                        //skrives til Admin.csv
                        WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Admin.csv");
                        showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerBruker), "Velkommen",
                                "Administrator opprettet");
                        //resetter inputs for registrering
                        clear();
                    }
                    else{
                        //eksisterer bruker, send feilmelding
                        lblMessage.setText("Administrator eksisterer");
                    }
                }
                catch(AvvikLoggInnBrukernavn e){
                    lblMessage.setText("Feil Brukernavn eller passord");
                }
                break;

            case "Bruker":
                try{
                    ValiderLoggInn.valideringBrukernavn(user.getText());
                    ValiderLoggInn.validerPassord(pass.getText());
                    if (!checkExistingBruker(user.getText(), "./Brukere.csv")) {
                        lblMessage.setText("");
                        //opprett ny bruker
                        BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                        //skriver til Brukere.csv
                        WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv");

                        //popup vindu som bekrefter at en ny bruker har blitt opprettet
                        showAlertWindow(Alert.AlertType.INFORMATION, windowHelper(registrerBruker), "Ny bruker opprettet",
                                "Bruker opprettet");
                        //showAlertBox(Alert.AlertType.CONFIRMATION, "Ny bruker opprettet", "Ny bruker");
                        //resetter inputs for registrering
                        clear();

                    }
                    else {
                        //eksisterer bruker, send f eilmelding
                        lblMessage.setText("Bruker eksisterer");
                    }
                }
                catch(AvvikLoggInnBrukernavn e){
                    lblMessage.setText("Feil i brukernavn eller passord");
                }
               break;

            default:
                lblMessage.setText("Bruker eksiterer");
        }

    }
    public void clear(){
        //resetter inputfeltene
        user.clear();
        pass.clear();

    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "loggInn");
    }

    @FXML
    public void registererProdukt(ActionEvent event) throws AvvikKomponentProduktnavn, AvvikKomponentPris, AvvikKomponentNyKategori {


        try {
            if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")) {
                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriNavn.getText(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT

                ValideringKomponent.validerProduktnavn(produktNavn.getText());
                ValideringKomponent.validerNyKategori(kategoriNavn.getText());
                ValideringKomponent.validerPris(Double.parseDouble(produktPris.getText()));

                kl.getObservableList().add(nyKomponent);
            } else {
                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriCombobox.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
                ValideringKomponent.validerProduktnavn(produktNavn.getText());
                ValideringKomponent.validerPris(Double.parseDouble(produktPris.getText()));
                kl.getObservableList().add(nyKomponent);
            }

        } catch (AvvikKomponentProduktnavn | AvvikKomponentPris | AvvikKomponentNyKategori e) {
            if (e instanceof AvvikKomponentProduktnavn) {
                lblMessage.setText("Feil i produktnavn! Produktnavn må være minst to bokstaver.");
            } else if (e instanceof AvvikKomponentNyKategori) {
                lblMessage.setText("Feil i kategori! Kategori må være minst to bokstaver langt.");
            } else if (e instanceof AvvikKomponentPris) {
                lblMessage.setText("Pris må være høyere enn 0 NOK og mindre enn 1000 000 NOK");
            }


            if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")) {
                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriNavn.getText(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
                kl.getObservableList().add(nyKomponent);
            } else {
                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriCombobox.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(produktPris.getText()), false); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
                kl.getObservableList().add(nyKomponent);
            }

            komponenter.refresh();
            populateKategoriCombobox();
        }
    }

    public void slettRader(ActionEvent event) {
    }
}
