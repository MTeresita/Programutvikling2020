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
public CheckBox checkBox;

@FXML
TableView <Komponent> komponenter;

@FXML
TableColumn<Komponent, String> produktnavn, kategori;

@FXML
TableColumn<Komponent, Double> pris;

@FXML
TableColumn<Komponent, Boolean> duplikat;

public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
        populateTableWithList();
        komponenter.getSortOrder().add(pris);
        kategoriNavn.setDisable(true);
        searchTableView(kl, filteredData, komponenter);
    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        duplikat.setCellValueFactory(cellData -> cellData.getValue().duplikatProperty());
        komponenter.setItems(kl.getObservableList());
        populateKategoriCombobox();
    }
    public void populateKategoriCombobox(){
        //kategoriCombobox.getItems().clear();

        for(Komponent k : kl.getObservableList()){
            if (!kategoriCombobox.getItems().contains("Ny Kategori...")){
                kategoriCombobox.getItems().add("Ny Kategori..."); //legger til "ny kategori..." som førstevalg
            }
            if(!kategoriCombobox.getItems().contains(k.getKategori())){ //Om kategori er lagt til fra før, legges den ikke til igjen
                kategoriCombobox.getItems().add(k.getKategori());
            }
        }
        kategoriCombobox.setValue("Velg kategori");
        kategoriCombobox.setPromptText("Velg kategori");


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
        produktNavn.clear();
        kategoriNavn.clear();
        produktPris.clear();

    }

    public void forsideBtn(ActionEvent actionEvent) {
        SceneChanger.routeToSite(actionEvent, "loggInn");
    }

    @FXML
    public void setKategoriTilgjengelig(ActionEvent event){
        System.out.println("setKategori: "+kategoriCombobox.getSelectionModel().getSelectedItem().toString());
        if(kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")){
            kategoriNavn.setDisable(false);
        }else{
            kategoriNavn.setDisable(true);
        }
    }

    @FXML
    public void registererProdukt(ActionEvent event) throws AvvikKomponentProduktnavn, AvvikKomponentPris, AvvikKomponentNyKategori {
        //System.out.println("Fra combobox: "+kategoriCombobox.getSelectionModel().getSelectedItem().toString());


        try {

            if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Velg kategori")){
                lblMessage.setText("Du må velge en eksisterende eller lage en ny kategori.");
                //LAGE AVVIK HER?
            }
            else if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")) {
                ValideringKomponent.validerProduktnavn(produktNavn.getText());
                ValideringKomponent.validerNyKategori(kategoriNavn.getText());
                ValideringKomponent.validerPris(Double.parseDouble(produktPris.getText()));

                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriNavn.getText(), Double.parseDouble(produktPris.getText()), checkBox.isSelected());
                sjekkForDuplikater(nyKomponent);

            } else {
                ValideringKomponent.validerProduktnavn(produktNavn.getText());
                ValideringKomponent.validerPris(Double.parseDouble(produktPris.getText()));

                Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriCombobox.getSelectionModel().getSelectedItem().toString(), Double.parseDouble(produktPris.getText()), checkBox.isSelected()); //HER MÅ DUPLIKAT LEGGES TIL FRA BRUKERINPUT
                sjekkForDuplikater(nyKomponent);
            }
        komponenter.refresh();
        populateKategoriCombobox();

        } catch (AvvikKomponentProduktnavn | AvvikKomponentPris | AvvikKomponentNyKategori e) {
            if (e instanceof AvvikKomponentProduktnavn) {
                lblMessage.setText("Feil i produktnavn! Produktnavn må være minst to tegn.");
            } else if (e instanceof AvvikKomponentNyKategori) {
                lblMessage.setText("Feil i kategori! Kategori må være minst to tegn.");
            } else if (e instanceof AvvikKomponentPris) {
                lblMessage.setText("Pris må være høyere enn 0 NOK og mindre enn 1 000 000 NOK");
            }
        }
    }
    public boolean sjekkForDuplikater(Komponent nyKomponent){
        if(kl.finnDuplikat(nyKomponent)){
            lblMessage.setText("Komponeneten er allerede registrert");
            return true;
        }else{
            kl.getObservableList().add(nyKomponent);
            lblMessage.setText("Komponent lagt til i liste!");
            return false;
        }
    }

    public void slettRader(ActionEvent event) {
        
        try{
            kl.slettKomponent(komponenter.getSelectionModel().getSelectedIndex());
            populateTableWithList();
            komponenter.refresh();
            lblMessage.setText("Komponent fjernet fra listen.");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
