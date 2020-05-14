package org.openjfx.Models.HjelpeKlasser;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.Produkt;

import java.util.ArrayList;
import java.util.Optional;

public class HentFilAdminThread extends Task<ObservableList<Komponent>> {
    @Override
    protected ObservableList<Komponent> call() throws Exception {
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try{
            KomponenterListe kl = new KomponenterListe();
            kl.henteFraObjectFil(true, "");
            return kl.getObservableList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
