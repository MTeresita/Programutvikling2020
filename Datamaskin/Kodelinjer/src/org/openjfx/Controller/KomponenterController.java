package org.openjfx.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.openjfx.Models.Avvik.AvvikBruker;
import org.openjfx.Models.Filbehandling.FilHenting.FilHentingBruker;
import org.openjfx.Models.Filbehandling.FilSkriving.WriteTo;
import org.openjfx.Models.HjelpeKlasser.BrukerSession;
import org.openjfx.Models.Interfaces.SceneChanger;

import org.openjfx.Models.Konfigurasjon;
import org.openjfx.Models.KomponenterListe;

import org.openjfx.Models.Komponent;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.openjfx.Models.KomponenterListe.searchTableView;


public class KomponenterController {

    @FXML
    private Label label;

    @FXML
    private Label lblSluttPris;

    @FXML
    private Button newProduct, lagreKonfig, slettKonfig, hentKonfig;

    @FXML
    private ComboBox filListe;

    @FXML
    private Label lblKonfigurasjonsNavn, loggetInn;

    @FXML
    private Button leggTilProdukt;

    @FXML
    private ListView<String> listview;

    @FXML
    TextField filterData;

    @FXML
    TableView <Komponent> komponenter;

    @FXML
    TableColumn<Komponent, String> produktnavn, kategori;

    @FXML
    TableColumn<Komponent, Double> pris;

    @FXML
    private CheckBox checkCPU, checkGPU, checkRAM, checkMB, checkHDD, checkPSU, checkKAB;

    private ObservableSet<CheckBox> alleCheckboxer = FXCollections.observableSet();
    private String session = BrukerSession.getBrukerSession(); //henter brukersession/brukernavnet til den som er logget inn
    public Konfigurasjon k = new Konfigurasjon(); //lager en generell liste av konfigurasjon som brukes gjennom kontrolleren
    public KomponenterListe kl = new KomponenterListe();


    public void initialize() {
         //bruker disse for å resette jobj filen med komponenter fra csv
        /*
        kl.createTableFromFile();
        kl.lagreTilObjectFil();
        */
        populateTable();
        searchTableView(kl, filterData, komponenter);
        loggetInn.setText(session);
        setAccesibleText();
        setAlleCheckboxerTilListe();
    }

    public void populateTable() { //henter fra .csv fil
        kl.henteFraObjectFil();
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        //komponenter.setItems(kl.createTableFromFile());
        komponenter.setItems(kl.getObservableList());
        populateFilListeComboBox();
    }
    public void setAccesibleText(){
        checkCPU.setAccessibleText("CPU");
        checkGPU.setAccessibleText("GPU");
        checkRAM.setAccessibleText("RAM");
        checkMB.setAccessibleText("MOTHERBOARD");
        checkHDD.setAccessibleText("HARD DRIVE");
        checkPSU.setAccessibleText("PSU");
        checkKAB.setAccessibleText("KABINETT");
    }
    public void setAlleCheckboxerTilListe(){
        alleCheckboxer.add(checkCPU);
        alleCheckboxer.add(checkGPU);
        alleCheckboxer.add(checkRAM);
        alleCheckboxer.add(checkMB);
        alleCheckboxer.add(checkHDD);
        alleCheckboxer.add(checkPSU);
        alleCheckboxer.add(checkKAB);
        disableCheckboxes();
    }
    public void disableCheckboxes(){
        alleCheckboxer.forEach(checkBox -> checkBox.setDisable(true));
        alleCheckboxer.forEach(checkBox -> checkBox.setStyle("-fx-opacity: 1")); //gjør at .setDisable ikke gjør boksene transparente
    }

    //TODO: Bruker vi denne lenger eller kan den slettes?
    public void populateTableWithList(){ //henter observable list fra fra globale KomponeterListen "kl"
        produktnavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        kategori.setCellValueFactory(cellData -> cellData.getValue().kategoriProperty());
        pris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asObject());
        komponenter.setItems(kl.getObservableList());
    }

    @FXML
    public void leggTilKomponentEvent(ActionEvent event) throws AvvikBruker { //henter valgt komponent fra tableview fra knappetrykk på "legg til komponent"
        try {
            Komponent valgtKomponent = komponenter.getSelectionModel().getSelectedItem(); //henter valgt komponent
            //System.out.println("Dette er det valgte komponentet: "+valgtKomponent.getNavn()+", "+valgtKomponent.getAntall());
            k.setNyttKomponent(valgtKomponent); //legger til i konfigurasjon
            populateListview();
            setCheckboxes(valgtKomponent, true); //setter sjekkboks
        }catch(AvvikBruker e){
            alertBox("","",e.getMessage());
        }
    }

    @FXML
    public void slettKomponentViaListView(){
        System.out.println(listview.getSelectionModel().getSelectedIndex());
        try {
            Komponent valgtKomponent = k.getKonfigListe().get(listview.getSelectionModel().getSelectedIndex());
            k.slettKomponent(listview.getSelectionModel().getSelectedIndex());
            populateListview();
            setCheckboxes(valgtKomponent, false);
        } catch(Exception e){

        }
    }
    public void setCheckboxes(Komponent komp, boolean type){ //om boolean true, vil boks sjekkes, false vil den avsjekkes
        for (CheckBox checkBox : alleCheckboxer) {
            if(checkBox.getAccessibleText().equals(komp.getKategori())){
                if(type){
                    checkBox.setSelected(true);
                }
                else if(!type){
                    checkBox.setSelected(false);
                }
            }
        }
    }

    public void populateListview(){ //legger ut komponeter fra konfigurasjon sin ArrayList
        listview.getItems().clear();
        alleCheckboxer.forEach(checkBox -> checkBox.setSelected(false));
        for(Komponent ktv : k.getKonfigListe()){
            listview.getItems().add(ktv.getNavn() + "\n" + ktv.getKategori() + "\n" +ktv.getPris()+" NOK"+"\n.......................................");
            setCheckboxes(ktv, true); //setter sjekkbokser
        }
        listview.refresh();
        lblSluttPris.setText(k.getSluttPris() +" NOK");
    }




    private String sistValgteFil; //brukes for å oppdatere combobox med filen som faktisk er valgt etter lagring/henting
    public void setFilListeTilgjengelig(ActionEvent event) throws NullPointerException{
        try {
            if (filListe.getSelectionModel().getSelectedItem().toString().equals("Ny Fil...")) {
                slettKonfig.setDisable(true);
                hentKonfig.setDisable(true);
            } else {
                slettKonfig.setDisable(false);
                hentKonfig.setDisable(false);
            }
            sistValgteFil = filListe.getSelectionModel().getSelectedItem().toString();
            filListe.getSelectionModel().select(sistValgteFil);
        }catch (NullPointerException e){
            System.out.println("Nullpointer excec");
            //grunnet hvordan combobox er bugget, vil den alltid kalle på denne metoden med en
            // nullpointer når man kjører "populateFilListeComboBox" etter å ha lagret fil
        }
    }

    public void populateFilListeComboBox(){
        filListe.getItems().clear();
        if (!filListe.getItems().contains("Ny Fil...")){
            filListe.getItems().add("Ny Fil..."); //legger til "Ny Fil..." som førstevalg
        }

        File aDirectory = new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/" + session + "/");
        String[] filesInDir = aDirectory.list();
        if(filesInDir != null){ //om directory ikke har filer/er null, legger den ikke til filnavn
            for (String s : filesInDir) {
                filListe.getItems().add(s);
            }
        }else{
            System.out.println("Ingen filer funnet");
        }
    }


    public void lagreKonfigurasjon() {
        if(!k.getKonfigListe().isEmpty()) {
            if (filListe.getSelectionModel().getSelectedItem() == "Ny Fil...") {
                File aDirectory = new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/" +
                                            session + "/");
                String[] filesInDir = aDirectory.list();

                if (filesInDir != null) {
                    int versjon = filesInDir.length + 1;
                    WriteTo.writeToCSVFile(new WriteTo(), k,
                            "Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/" + session +
                                    "/konfigurasjon-" + versjon + ".csv", false);
                    //tar utgangspunkt i ekisterende versjoner, adderer 1

                    sistValgteFil = "konfigurasjon-" + versjon + ".csv";
                    lblKonfigurasjonsNavn.setText("konfigurasjon-" + versjon + ".csv");
                } else {
                    int versjon = 1;
                    WriteTo.writeToCSVFile(new WriteTo(), k,
                            "Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/" + session +
                                    "/konfigurasjon-" + versjon + ".csv", false); //lager første versjon (1)
                    sistValgteFil = "konfigurasjon-" + versjon + ".csv";
                    lblKonfigurasjonsNavn.setText("konfigurasjon-" + versjon + ".csv");
                }
            } else {
                boolean ok = alertBox("Bekreft overskriving", "Endringer gjort vil overskrive valgt fil!",
                                    "Er du sikker på at du vil skrive over?");
                if (ok) {
                    WriteTo.writeToCSVFile(new WriteTo(), k,
                            "Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/" + session + "/" +
                                    filListe.getSelectionModel().getSelectedItem(), false);
                }
            }
            populateFilListeComboBox();
            filListe.getSelectionModel().select(sistValgteFil);
            //alert at fil er blitt lagret!
        }else{
            boolean ok = alertBox("Feilmelding", "Ingen komponeneter er valgt!", "");
        }
    }
    public void hentKonfigurasjon() throws IOException {
        FilHentingBruker fhb = new FilHentingBruker();
        ArrayList<Komponent> kompliste;

        kompliste = fhb.lesingFraFil("Datamaskin/Kodelinjer/src/org/openjfx/Models/konfigCsv/"+session+"/"+
                                            filListe.getSelectionModel().getSelectedItem());
        k.setKonfigListe(kompliste);
        lblKonfigurasjonsNavn.setText(filListe.getSelectionModel().getSelectedItem().toString());
        populateListview();

        //alert om henting av fil!
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
    public void logOutEvent(ActionEvent event) {
        boolean ok = alertBox("Bekreft utlogging", "Endringer gjort uten å trykke lagre vil bli slettet!",
                "Er du sikker på at du vil logge ut?");
        if (ok) {
            SceneChanger.routeToSite(event, "loggInn");
        }
    }
}
