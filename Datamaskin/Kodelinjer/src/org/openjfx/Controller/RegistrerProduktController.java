package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.openjfx.Models.Avvik.*;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerRegister;
import org.openjfx.Models.Interfaces.SceneChanger;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.Validering.ValiderLoggInn;
import org.openjfx.Models.Validering.ValideringKomponent;

import java.io.IOException;
import java.util.Optional;

import static org.openjfx.Models.Avvik.AlertHelper.*;
import static org.openjfx.Models.HjelpeKlasser.BrukerSystemHjelpeKlasse.checkExistingBruker;
import static org.openjfx.Models.KomponenterListe.endringITableView;


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

@FXML
ImageView tilbakemeldingImg;

public KomponenterListe kl = new KomponenterListe();

    public void initialize() {
        populateTableWithJobj();
        komponenter.getSortOrder().add(pris);
        komponenter.setEditable(true);
        endringITableView(produktnavn,kategori, pris);
        lblMessage.setWrapText(true);
    }

    public void populateTableWithJobj(){ //henter jobj fil fra fra globale KomponeterListen "kl"
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        duplikat.setCellValueFactory(cellData -> cellData.getValue().duplikatProperty());
        komponenter.setItems(kl.getObservableList());
        populateKategoriCombobox();
    }
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        kl.getObservableList();
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
        kategoriNavn.setDisable(true);


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
                        setLabelTekst("alert", "Administrator eksisterer");
                        //lblMessage.setText("Administrator eksisterer");
                    }
                }
                catch(AvvikLoggInnBrukernavn e){
                    //lblMessage.setText("Feil Brukernavn eller passord");
                    setLabelTekst("alert", "Feil Brukernavn eller passord");
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
                        setLabelTekst("alert", "Bruker eksisterer.");
                    }
                }
                catch(AvvikLoggInnBrukernavn e){
                    setLabelTekst("alert", "Feil i brukernavn eller passord.");
                }
               break;

            default:
                setLabelTekst("alert", "Bruker eksisterer");
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekreft utlogging");
        alert.setHeaderText("Endringer gjort uten å trykke lagre vil bli slettet!");
        alert.setContentText("Er du sikker på at du vil avslutte?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            SceneChanger.routeToSite(actionEvent, "loggInn");
        } else {
           alert.close();
        }
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
                setLabelTekst("alert", "Du må velge en eksisterende eller lage en ny kategori.");
                //lblMessage.setText("Du må velge en eksisterende eller lage en ny kategori.");
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
            clear();
            komponenter.refresh();
            populateKategoriCombobox();

        } catch (AvvikKomponentProduktnavn | AvvikKomponentPris | AvvikKomponentNyKategori e) {
            if (e instanceof AvvikKomponentProduktnavn) {
                setLabelTekst("alert", "Feil i produktnavn! Produktnavn må være minst to tegn.");
                //lblMessage.setText("Feil i produktnavn! Produktnavn må være minst to tegn.");
            } else if (e instanceof AvvikKomponentNyKategori) {
                setLabelTekst("alert", "Feil i kategori! Kategori må være minst to tegn.");
                //lblMessage.setText("Feil i kategori! Kategori må være minst to tegn.");
            } else if (e instanceof AvvikKomponentPris) {
                setLabelTekst("alert", "Pris må være høyere enn 0 NOK og mindre enn 1 000 000 NOK.");
                //lblMessage.setText("Pris må være høyere enn 0 NOK og mindre enn 1 000 000 NOK");
            }
        }
    }
    public boolean sjekkForDuplikater(Komponent nyKomponent){
        if(kl.finnDuplikat(nyKomponent)){
            setLabelTekst("alert", "Komponeneten er allerede registrert");
            //lblMessage.setText("Komponeneten er allerede registrert");
            return true;
        }else{
            //kl.getObservableList().add(nyKomponent);
            kl.setKomponenter(nyKomponent);
            setLabelTekst("success", "Komponent lagt til i liste!");
            //lblMessage.setText("Komponent lagt til i liste!");
            return false;
        }
    }

    @FXML
    public void endreTableViewDataString(TableColumn.CellEditEvent<Komponent, String> event) throws AvvikKomponentProduktnavn, AvvikKomponentNyKategori{ //Fra henrik
        try{
            if(event.getTableColumn().getText().equals("Produktnavn")){
                ValideringKomponent.validerProduktnavn(event.getNewValue());
                event.getRowValue().setNavn(event.getNewValue());
            }else{
                ValideringKomponent.validerNyKategori(event.getNewValue());
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
    public void endreTableViewDataDouble(TableColumn.CellEditEvent<Komponent, Double> event){ //Fra henrik
        event.getRowValue().setPris(event.getNewValue());
    }
    @FXML
    public void endreTableViewDataBool(TableColumn.CellEditEvent<Komponent, Boolean> event){ //Fra henrik
        event.getRowValue().setDuplikat(event.getNewValue());
    }

    public void slettRader(ActionEvent event) {
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

    public void lagreTilFil(ActionEvent event){
        try {
            kl.lagreTilObjectFil();
            setLabelTekst("success", "Lagring var vellykket!");
        }catch (Exception e){
            setLabelTekst("alert", "Noe gikk galt. Kunne ikke lagre fil.");
        }
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
}
