package org.openjfx.Models.Filbehandling.FilLagring;

import org.openjfx.Models.Komponent;
import org.openjfx.Models.Produkt;

import java.io.*;
import java.util.ArrayList;

public class FilLagringAdmin {

    public void lagreTilFil(ArrayList<Komponent> liste, boolean master, boolean nyFil, String filnavn){

            try{
                FileOutputStream f ;
                if(master){
                    f = new FileOutputStream(new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin/MASTER.jobj"));
                }else{
                    if(nyFil){
                        f = new FileOutputStream(new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin/"+filnavn+".jobj"));
                    }else{
                        f = new FileOutputStream(new File("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin/"+filnavn));
                    }
                }
                ObjectOutputStream o = new ObjectOutputStream(f);

                for(Komponent ktv : liste){
                    Produkt produkt = new Produkt(ktv.getNavn(), ktv.getPris(), ktv.getKategori(), ktv.getAntall());
                    o.writeObject(produkt);
                }

                o.close();
                f.close();
                System.out.println("Skrevet til object fil");

            }catch(IOException e){
                System.out.println("Feil i skriving til objectfil: "+e);
            }

    }
    public void setMasterFil(String filnavn) throws IOException {
        FileWriter fw = new FileWriter("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponentMasterlisteAdmin/masterlist");
        fw.write(filnavn);
        fw.close();
        System.out.println("Successfully wrote to the file.");
    }
}
