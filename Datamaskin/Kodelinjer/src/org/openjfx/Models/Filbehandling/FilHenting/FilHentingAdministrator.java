package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Models.Produkt;
import org.openjfx.Models.Parsing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import java.io.*;
import java.util.ArrayList;

public class FilHentingAdministrator {

    public ArrayList<Produkt> list = new ArrayList<Produkt>();

    public ArrayList<Produkt> hentFraFil() {
        try {
            FileInputStream fis = new FileInputStream(new File("komponentlist.jobj"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            boolean ok = true;
            while (ok) {
                if(ois != null){
                    Object produktObject = (Produkt) ois.readObject();
                    Produkt produkt = new Produkt();
                    produkt = (Produkt)produktObject;

                    list.add(produkt);
                }else{
                    ok = false;
                }
            }
            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("Feil i lesing av objectfil: " + e);
            //kaster visst exception uansett hva man gjør, men så lenge man handler den gpr det fint https://stackoverflow.com/questions/27409718/java-reading-multiple-objects-from-a-file-as-they-were-in-an-array
        }
        return list;
    }
}
