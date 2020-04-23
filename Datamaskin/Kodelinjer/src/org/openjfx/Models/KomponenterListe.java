package org.openjfx.Models;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.openjfx.Models.Filbehandling.FilHenting.FilHentingAdministrator;
import org.openjfx.Models.Filbehandling.FilLagring.FilLagringAdmin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KomponenterListe {


    private ArrayList<Komponent> komponenter = new ArrayList<Komponent>();
    private ObservableList<Komponent> komponenterListeObservable = FXCollections.observableArrayList();;



   /* //funksjoner:
    public void leggTilProdukt(Produkt... produkter){

    }*/

    public void lagreTilObjectFil(){
        FilLagringAdmin fla = new FilLagringAdmin();
        fla.lagreTilFil(this.komponenter);
    }
    public void henteFraObjectFil(){
        FilHentingAdministrator fha = new FilHentingAdministrator();
        ArrayList<Produkt> produktListe = fha.hentFraFil();
        setKomponenterFraFil(produktListe);
    }
    public void setKomponenterFraFil(ArrayList<Produkt> liste){
        komponenter.clear();
        komponenterListeObservable.clear();
        for(Produkt p : liste){
            Komponent k = new Komponent(p.getNavn(), p.getKategori(), p.getPris(), p.isDuplikat());
            komponenter.add(k);
            komponenterListeObservable.add(k);
        }
    }

    public void setKomponenter(Komponent... komponenter) {
        Collections.addAll(this.komponenter, komponenter);
        komponenterListeObservable.addAll(komponenter);
    }

    public ArrayList<Komponent> getProdukter(Komponent... produkter) {
        ArrayList<Komponent> produkterIterator = new ArrayList<Komponent>();

        for(Komponent p : produkter){
            produkterIterator.add(p);
        }
        return produkterIterator;
    }

    public ObservableList<Komponent> getObservableList(){ //henter den globale oservablelist
        return komponenterListeObservable;
    }

    public ArrayList<Komponent> getList(){
        return komponenter;
    }

    //brukes ikke lengre
    public ObservableList<Komponent> createTableFromFile() { //henter fra fil og skriver til global observablelist
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./komponenter.csv"));

            String komponenter;
            while ((komponenter = reader.readLine()) != null) {
                //3.
                String [] komponentfields = komponenter.split(";");
                double pris = Double. parseDouble(komponentfields[2]);
                //4.
                Komponent inputRecord = new Komponent(komponentfields[0], komponentfields[1], pris, Boolean.parseBoolean(komponentfields[3]));
                //5.
                komponenterListeObservable.add(inputRecord);
                this.komponenter.add(inputRecord);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Cannot read file");
        } catch (IOException ex) {
            System.out.println("File not found#" + ex.getCause());
        }

        return komponenterListeObservable;
    }

    public static void searchTableView(KomponenterListe kl, TextField sokefelt, TableView tableView){
        // setter som default at hvis den ikke finner noe, skal Ingen treff dukke opp i tableviewet
        tableView.setPlaceholder(new Label("Ingen treff"));

        //En obersable list i en filteredlist --> denne har all data
        FilteredList<Komponent> filteredData = new FilteredList<>
                (kl.getObservableList(), p -> true);

        // Setter filter predicate for når bruker søker eller endre inout i skrivefeltet
        sokefelt.textProperty().addListener((observableValue, oldValue, newValue) ->{
            filteredData.setPredicate(searchTableView -> {

                // Hvis filterlisten er tom, hvis alle komponeneter
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                //Sammelign inputtext med det som ligger i listen.
                String lowerCaseFilter = newValue.toLowerCase();

                if(searchTableView.getNavn().toLowerCase().contains(lowerCaseFilter) ||
                        searchTableView.getKategori().toLowerCase().contains(lowerCaseFilter)
                || Double.toString(searchTableView.getPris()).toLowerCase().contains(lowerCaseFilter)){
                    return true; // fant en match
                }
                return false; // ingen match
            });
        } );

        // Setter den filtrerte listen i en sortert liste
        SortedList<Komponent> sortedList =  new SortedList<>(filteredData);

        //binder den sortete listen til tableviewr
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());

        //legger til sortert og filtrert liste data i tableviewet
        tableView.setItems(sortedList);

    }

    public boolean finnDuplikat(Komponent k) { //sjekker om komponent som registreres finnes fra før
        for(Komponent a : komponenterListeObservable){
            System.out.println(a.getNavn() + "\n");
        }

        for(Komponent komponent : komponenterListeObservable){
            if(k.getNavn().equals(komponent.getNavn())){
                return true;
            }
        }
        return false;
    }
    public static void endringITableView(TableColumn produktnavn, TableColumn kategori, TableColumn pris){
        produktnavn.setCellFactory(TextFieldTableCell.forTableColumn());
        kategori.setCellFactory(TextFieldTableCell.forTableColumn());
        pris.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    /*
    public static void doChange(TableColumn produktnavn, TableColumn kategori, TableColumn pris){
        produktnavn.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Komponent, String>>) t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setNavn(t.getNewValue()));

        kategori.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Komponent, String>>) t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setKategori(t.getNewValue()));

        pris.setOnEditCommit(
                (EventHandler<TableColumn.CellEditEvent<Komponent, String>>) t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setPris(Double.parseDouble(t.getNewValue())));

    }

     */

}
