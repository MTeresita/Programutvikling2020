package org.openjfx.Models.Filbehandling.FilLagring;

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
                Produkt produkt = new Produkt(ktv.getNavn(), ktv.getPris(), ktv.getKategori(), ktv.isDuplikat());
                o.writeObject(produkt);
            }

            o.close();
            f.close();
            System.out.println("Skrevet til object fil");

        }catch(IOException e){
            System.out.println("Feil i skriving til objectfil: "+e);
        }


    }
}
