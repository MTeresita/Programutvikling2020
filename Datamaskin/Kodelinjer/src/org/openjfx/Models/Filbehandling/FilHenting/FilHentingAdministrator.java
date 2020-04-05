package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.KomponenterTableView;
import org.openjfx.Models.Produkt;

import java.io.*;
import java.util.ArrayList;

public class FilHentingAdministrator {

    public ArrayList<Produkt> list = new ArrayList<Produkt>();

    public ArrayList<Produkt> hentFraFil() {
        try {
            FileInputStream f = new FileInputStream(new File("komponentlist.jobj"));
            ObjectInputStream o = new ObjectInputStream(f);

            boolean ok = true;
            while (ok) {
                if(o != null){
                    Object produktObject = (Produkt) o.readObject();
                    Produkt produkt = new Produkt();
                    produkt = (Produkt)produktObject;
                    System.out.println(produkt.getNavn() + ", "+produkt.isDuplikat());
                    list.add(produkt);
                }else{
                    ok = false;
                }
            }
            o.close();
            f.close();

        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Feil i lesing av objectfil: " + e);
            //kaster visst exception uansett hva man gjør, men så lenge man handler den gpr det fint https://stackoverflow.com/questions/27409718/java-reading-multiple-objects-from-a-file-as-they-were-in-an-array
        }
        return list;
    }
}
