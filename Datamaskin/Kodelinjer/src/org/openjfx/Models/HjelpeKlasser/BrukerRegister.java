package org.openjfx.Models.HjelpeKlasser;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.Models.Komponent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BrukerRegister {

    private ObservableList<BrukerRegister> brukerListe = FXCollections.observableArrayList();

    public SimpleStringProperty brukernavn, passord;


    public BrukerRegister(String brukernavn) {
        this.brukernavn = new SimpleStringProperty(brukernavn);
    }

    public BrukerRegister(String brukernavn, String passord) {
        this.brukernavn = new SimpleStringProperty(brukernavn);
        this.passord = new SimpleStringProperty(passord);
    }

    public BrukerRegister() {

    }

    public String getBrukernavn() {
        return brukernavn.get();
    }

    public SimpleStringProperty brukernavnProperty() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn.set(brukernavn);
    }

    public String getPassord() {
        return passord.get();
    }

    public SimpleStringProperty passordProperty() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord.set(passord);
    }

    public String toString(){
        return getBrukernavn()+";"+ getPassord()+";";
    }

    public ObservableList<BrukerRegister> createTableFromFile() { //henter fra fil
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./Brukere.csv"));

            String bruker;
            while ((bruker = reader.readLine()) != null) {
                //3.
                String [] brukerFelt = bruker.split(";");
                //4.
                BrukerRegister inputRecord = new BrukerRegister(brukerFelt[0]);
                //5.
                brukerListe.add(inputRecord);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot read file");
        } catch (IOException ex) {
            System.out.println("File not found#" + ex.getCause());
        }

        return brukerListe;
    }
}
