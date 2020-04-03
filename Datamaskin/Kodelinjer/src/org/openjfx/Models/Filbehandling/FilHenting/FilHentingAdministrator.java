package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Models.KomponenterTableView;
import org.openjfx.Models.Produkt;

import java.io.*;
import java.util.ArrayList;

public class FilHentingAdministrator {
    public void hentFraFil() {
        try {
            ArrayList<Produkt> list = new ArrayList<>();

            FileInputStream f = new FileInputStream(new File("komponentlist.jobj"));
            ObjectInputStream o = new ObjectInputStream(f);

            while ((o.readObject()) != null) {
                Object produkt = (Produkt) o.readObject();
                Produkt prod = new Produkt();
                prod = (Produkt)produkt;
                System.out.println(prod.getNavn());
            }

            o.close();
            f.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Feil i lesing av objectfil: " + e);
        }
    }
}
