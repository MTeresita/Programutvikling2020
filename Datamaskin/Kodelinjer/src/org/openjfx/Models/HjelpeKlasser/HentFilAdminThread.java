package org.openjfx.Models.HjelpeKlasser;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.KomponenterListe;

public class HentFilAdminThread extends Task<ObservableList<Komponent>> {
    @Override
    protected ObservableList<Komponent> call() {
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        try{
            KomponenterListe kl = new KomponenterListe();
            kl.henteFraObjectFil(true, "");
            return kl.getObservableList();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
