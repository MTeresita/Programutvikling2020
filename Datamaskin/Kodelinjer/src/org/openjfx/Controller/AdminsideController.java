package org.openjfx.Controller;

import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.openjfx.Models.Avvik.*;
import org.openjfx.Models.Filbehandling.FilHenting.FilHentingAdministrator;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.HjelpeKlasser.HentFilAdminThread;
import org.openjfx.Models.Interfaces.SceneBytte;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.Validering.ValideringLoggInn;
import org.openjfx.Models.Validering.ValideringKomponent;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.openjfx.Models.Avvik.AlertHelper.visAlertVindu;
import static org.openjfx.Models.Avvik.AlertHelper.windowHelper;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemSjekk.sjekkOmBrukerEksiterer;
import static org.openjfx.Models.KomponenterListe.*;


public class AdminsideController {
@FXML
public TextField user, pass, produktNavn, kategoriNavn, produktPris, filteredData, produktAntall;

@FXML
public Button registrerBruker,  registrerProduktBtn, slettRader, setMasterFil, lagreTilFil, hentFraMaster, hentFraFil;

@FXML
public ComboBox adminORuser, kategoriCombobox, filListe;

@FXML
public Label lblMessage, lblFilNavn,lblMaster;

@FXML
TableView <Komponent> komponenter;

@FXML
TableColumn<Komponent, String> produktnavn, kategori;

@FXML
TableColumn<Komponent, Double> pris;

@FXML
TableColumn<Komponent, Integer> duplikat;


public KomponenterListe kl = new KomponenterListe();
public ValideringKomponent valideringKomponent = new ValideringKomponent();
private HentFilAdminThread task;
private void threadFailed(WorkerStateEvent event) {
        setAlleKnapperState(false);
    }

    public void initialize() {
        populateTableWithJobj();
        komponenter.getSortOrder().add(kategori);
        komponenter.setEditable(true);
        searchTableView(kl,filteredData,komponenter);
        endringITableView(produktnavn,kategori, pris, duplikat);
        lblMessage.setWrapText(true);
        populateFilListe();
    }

    public void populateTableWithJobj(){ //henter jobj fil fra fra globale KomponeterListen "kl"
        task = new HentFilAdminThread();
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        setAlleKnapperState(true);
        th.start();

        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        duplikat.setCellValueFactory(cellData -> cellData.getValue().antallProperty().asObject());
        komponenter.setItems(kl.getObservableList());

    }
    private void threadDone(WorkerStateEvent e) {
        ObservableList<Komponent> resultat = task.getValue();
        kl.getObservableList().setAll(resultat);

        populateKategoriCombobox();
        FilHentingAdministrator fha = new FilHentingAdministrator();
        lblMaster.setText(fha.getMasterFil());
        lblFilNavn.setText(fha.getMasterFil());
        setAlleKnapperState(false);
    }


    //setter alle knappene til å være disabled under en tråd
    private void setAlleKnapperState(boolean state){
        setMasterFil.setDisable(state);
        registrerBruker.setDisable(state);
        registrerProduktBtn.setDisable(state);
        slettRader.setDisable(state);
        lagreTilFil.setDisable(state);
        hentFraMaster.setDisable(state);
        hentFraFil.setDisable(state);


    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        kl.getObservableList();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        duplikat.setCellValueFactory(cellData -> cellData.getValue().antallProperty().asObject());
        komponenter.setItems(kl.getObservableList());
        populateKategoriCombobox();
    }
    public void populateKategoriCombobox(){
        //kategoriCombobox.getItems().clear();
        for(Komponent k : kl.getObservableList()){
            if (!kategoriCombobox.getItems().contains("Ny Kategori...")){
                kategoriCombobox.getItems().add("Ny Kategori..."); //legger til "ny kategori..." som førstevalg
            }
            //Om kategori er lagt til fra før, legges den ikke til igjen
            if(!kategoriCombobox.getItems().contains(k.getKategori())){
                kategoriCombobox.getItems().add(k.getKategori());
            }
        }
        kategoriCombobox.setValue("Velg kategori");
        kategoriCombobox.setPromptText("Velg kategori");
        kategoriNavn.setDisable(true);
    }


    public void registrerbtn() throws IOException {
        ValideringLoggInn valideringLoggInn = new ValideringLoggInn();
        String ugyldigRegistreering =
                valideringLoggInn.sjekkUgyldigData(user.getText(), pass.getText());

        // er det admin eller bruker sjek --> hvilken fil skal den til
        String value = String.valueOf(adminORuser.getValue());

        if(adminORuser.getSelectionModel().isEmpty()){

            setLabelTekst("alert", "Bruker type har ikke blitt valgt");
        }
        else {
            switch (value) {
                case "Admin":

                    if (!ugyldigRegistreering.isEmpty()) {
                        lblMessage.setText(ugyldigRegistreering);
                    } else {
                        if (!sjekkOmBrukerEksiterer(user.getText(), "./Admin.csv")) {
                            lblMessage.setText("");
                            //en ny bruker blir registrer
                            BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                            //skrives til Admin.csv
                            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Admin.csv", true);
                            visAlertVindu(Alert.AlertType.INFORMATION, windowHelper(registrerBruker), "Velkommen",
                                    "Administrator opprettet");
                            //resetter inputs for registrering
                            clear();
                        } else {
                            //eksisterer bruker, send feilmelding
                            setLabelTekst("alert", "Administrator eksisterer");

                        }
                    }
                    break;

                case "Bruker":

                    if (!ugyldigRegistreering.isEmpty()) {
                        lblMessage.setText(ugyldigRegistreering);
                    } else {
                        if (!sjekkOmBrukerEksiterer(user.getText(), "./Brukere.csv")) {
                            lblMessage.setText("");
                            //opprett ny bruker
                            BrukerRegister enBruker = new BrukerRegister(user.getText(), pass.getText());

                            //skriver til Brukere.csv
                            WriteTo.writeToCSVFile(new WriteTo(), enBruker, "./Brukere.csv", true);

                            //popup vindu som bekrefter at en ny bruker har blitt opprettet
                            visAlertVindu(Alert.AlertType.INFORMATION, windowHelper(registrerBruker),
                                    "Ny bruker opprettet",
                                    "Bruker opprettet");
                            //resetter inputs for registrering
                            clear();

                        } else {
                            //eksisterer bruker, send feilmelding
                            setLabelTekst("alert", "Bruker eksisterer.");
                        }
                    }
                    break;

                default:
                    setLabelTekst("alert", "Bruker eksisterer");
            }
        }

    }

    public void clear(){
        //resetter inputfeltene
        user.clear();
        pass.clear();
        produktNavn.clear();
        kategoriNavn.clear();
        produktPris.clear();
        produktAntall.clear();
    }

    public void forsideBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekreft utlogging");
        alert.setHeaderText("Endringer gjort uten å trykke lagre vil bli slettet!");
        alert.setContentText("Er du sikker på at du vil avslutte?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneBytte.routeToSite(actionEvent, "loggInn");
        } else {
           alert.close();
        }
    }

    @FXML
    //Ved ny kategori, blir tekstfeltet for ny kategorinavn disabled
    public void setKategoriTilgjengelig(){
        System.out.println("setKategori: "+kategoriCombobox.getSelectionModel().getSelectedItem().toString());
        if(kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")){
            kategoriNavn.setDisable(false);
        }else{
            kategoriNavn.setDisable(true);
        }
    }

    @FXML
    public void registererProdukt() throws NumberFormatException {
        String validering =
                valideringKomponent.sjekkUgyldigKomponent(produktNavn.getText(), kategoriNavn.getText(),
                        (produktPris.getText()), kategoriCombobox, produktAntall.getText());


            if (!validering.isEmpty()) {
                setLabelTekst("alert", validering);
            } else {
                if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Velg kategori")) {
                    setLabelTekst("alert", "Du må velge en eksisterende eller lage en ny kategori.");
                } else if (kategoriCombobox.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")) {
                    Komponent nyKomponent = new Komponent(produktNavn.getText(), kategoriNavn.getText(),
                            Double.parseDouble(produktPris.getText()), Integer.parseInt(produktAntall.getText()));
                    sjekkForDuplikater(nyKomponent);
                } else {
                    Komponent nyKomponent = new Komponent(produktNavn.getText(),
                            kategoriCombobox.getSelectionModel().getSelectedItem().toString(),
                            Double.parseDouble(produktPris.getText()), Integer.parseInt(produktAntall.getText()));
                    sjekkForDuplikater(nyKomponent);
                }

                clear();
                komponenter.refresh();
                populateKategoriCombobox();
            }

    }
    public boolean sjekkForDuplikater(Komponent nyKomponent){
        if(kl.finnDuplikat(nyKomponent)){
            setLabelTekst("alert", "Komponeneten er allerede registrert");
            return true;
        }
        else{
            kl.setKomponenter(nyKomponent);
            setLabelTekst("success", "Komponent lagt til i liste!");
            return false;
        }
    }

    @FXML
    public void endreTableViewDataString(TableColumn.CellEditEvent<Komponent, String> event) {

        try{
            if(event.getTableColumn().getText().equals("Produktnavn")){
                ValideringKomponent.validerProduktnavn(event.getNewValue());
                event.getRowValue().setNavn(event.getNewValue());
            }else{
                ValideringKomponent.validerNyKategori(event.getNewValue(), kategoriCombobox);
                event.getRowValue().setKategori(event.getNewValue());

            }
        }catch (AvvikKomponentProduktnavn | AvvikKomponentNyKategori e) {
            komponenter.refresh();
            if (e instanceof AvvikKomponentProduktnavn) {
                setLabelTekst("alert", "Feil i produktnavn! Produktnavn må være minst to tegn.");
                //lblMessage.setText("Feil i produktnavn! Produktnavn må være minst to tegn.");

            } else if (e instanceof AvvikKomponentNyKategori) {
                setLabelTekst("alert", "Feil i kategori! Kategori må være minst to tegn.");
                //lblMessage.setText("Feil i kategori! Kategori må være minst to tegn.");
            }
        }



    }
    @FXML
    public void endreTableViewDataDouble(TableColumn.CellEditEvent<Komponent, Double> event){
                if(event.getNewValue().isNaN()){
                    setLabelTekst("alert", "Pris må skrives som tall\n");
                    komponenter.refresh();
                }
                else if(event.getNewValue() <= 0){
                    setLabelTekst("alert", "Pris kan ikke være mindre enn null\n");
                    komponenter.refresh();
                }
                else if(event.getNewValue() > 999999){
                    setLabelTekst("alert", "Pris kan ikke være høyere enn\n 999 999 NOK\n");
                    komponenter.refresh();
                }
                else {
                    event.getRowValue().setPris(event.getNewValue());
                }
    }
    
    @FXML
    public void endreTableViewDataBool(TableColumn.CellEditEvent<Komponent, Integer> event){

        if(event.getNewValue() == 0){
            setLabelTekst("alert", "Antall må skrives som tall\n");
            komponenter.refresh();
        }
        else if(event.getNewValue() <= 0){
            setLabelTekst("alert", "Antall kan ikke være mindre enn null\n");
            komponenter.refresh();
        }
        else if(event.getNewValue() > 3){
            setLabelTekst("alert", "Antall kan ikke være høyere enn 3\n");
            komponenter.refresh();
        }
        else {
            event.getRowValue().setAntall(event.getNewValue());
        }

    }

    public void slettRader() {
        try {
            Komponent toDelete = komponenter.getSelectionModel().getSelectedItem();
            if (kl.slettKomponentFraListe(toDelete)) {
                setLabelTekst("success", "Komponent slettet.");
            } else {
                setLabelTekst("alert", "Noe gikk galt. Kunne ikke slette komponent.");
            }
            populateTableWithList();
        }catch(NullPointerException e){
            setLabelTekst("alert", "Noe gikk galt. Kunne ikke slette komponent.");
        }
    }

    public void setMasterFil(){
        boolean ok = alertBox("Set masterfil","Masterfil styrer hvilke komponenter bruker har å " +
                "velge mellom.\nDette kan alltid reverseres til ønsket fil.","Ønsker du å fortsette?");
        if(ok){
            try {
                kl.setMasterObjectFil(filListe.getSelectionModel().getSelectedItem().toString());
                setLabelTekst("success", "Setting av masterfil var vellykket!");
            }catch (Exception e){
                setLabelTekst("alert", "Noe gikk galt. Kunne ikke sette masterfil.");
            }
        }
        initialize();

    }
    public void hentMasterFil(){
        initialize();
    }
    public void lagreTilFil() throws Exception {
            String valgtFil = filListe.getSelectionModel().getSelectedItem().toString();
            boolean nyFil = false;
            if(valgtFil.equals("Ny Fil...")){
                TextInputDialog td = new TextInputDialog();
                td.setHeaderText("Skriv navn på fil");
                Optional<String> resultat = td.showAndWait();
                if(!resultat.isPresent()){
                    throw new Exception();
                }
                nyFil = true;
                valgtFil = td.getEditor().getText();
            }
            try {
                kl.lagreTilObjectFil(nyFil, false,valgtFil);
                setLabelTekst("success", "Lagring var vellykket!");
            }catch (Exception e){
                setLabelTekst("alert", "Noe gikk galt. Kunne ikke lagre fil.");
            }
    }
    public void hentFraFil(){
        String valgtFil = filListe.getSelectionModel().getSelectedItem().toString();
        if(!valgtFil.equals("Ny Fil...")){
            kl.henteFraObjectFil(false, valgtFil);
            lblFilNavn.setText(valgtFil);
        }
    }

    public void populateFilListe(){
        filListe.getItems().clear();
        if (!filListe.getItems().contains("Ny Fil...")){
            filListe.getItems().add("Ny Fil..."); //legger til "Ny Fil..." som førstevalg
        }
        File aDirectory = new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin");
        String[] filesInDir = aDirectory.list();
        if(filesInDir != null){ //om directory ikke har filer/er null, legger den ikke til filnavn
            for (String s : filesInDir) {
                filListe.getItems().add(s);
            }
        }else{
            System.out.println("Ingen filer funnet");
            filListe.getSelectionModel().select(0);
        }
        filListe.getSelectionModel().select(0);
    }

    public void setLabelTekst(String type, String msg){

        switch (type){
            case "alert":
                lblMessage.setTextFill(Color.web("#e3345a"));
                lblMessage.setText(msg);
                break;
            case "success" :
                lblMessage.setTextFill(Color.web("#27ba6c"));
                lblMessage.setText(msg);
                break;
            default:
                lblMessage.setTextFill(Color.web("000"));
                lblMessage.setText(msg);
                break;
        }

    }

    public boolean alertBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            alert.close();
            return false;
        }
    }
}
