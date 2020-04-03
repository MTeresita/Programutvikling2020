package org.openjfx.Models.Filbehandling.FilLagring;

import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.KomponenterTableView;
import org.openjfx.Models.Produkt;

import java.io.*;
import java.util.ArrayList;

public class FilLagringAdmin {

    public void lagreTilFil(ArrayList<KomponenterTableView> liste){
        try{
            FileOutputStream f = new FileOutputStream(new File("komponentlist.jobj"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            for(KomponenterTableView ktv : liste){
                Produkt produkt = new Produkt(ktv.getNavn(), ktv.getKategori(), ktv.getPris());
                o.writeObject(produkt);
            }

            o.close();
            f.close();
        }catch(IOException e){
            System.out.println("Feil i skriving til objectfil: "+e);
        }


    }
}
