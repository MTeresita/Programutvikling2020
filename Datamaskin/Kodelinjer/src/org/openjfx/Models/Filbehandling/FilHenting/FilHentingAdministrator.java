package org.openjfx.Models.Filbehandling.FilHenting;

import javafx.scene.control.Alert;
import org.openjfx.Models.Avvik.AvvikBruker;
import org.openjfx.Models.Produkt;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


import java.io.*;

public class FilHentingAdministrator {

    public ArrayList<Produkt> list = new ArrayList<>();

    public ArrayList<Produkt> hentFraFil(boolean master, String filnavn) {
        try {
            FileInputStream fis;
            if(master){
                String mastername = getMasterFil();
                try{
                    fis = new FileInputStream(new File
                            ("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin/"+mastername));
                }catch (FileNotFoundException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Fant ikke en masterfil!\nKomponentlisten vil derfor være tom.");
                    alert.setContentText("Dette løses ved å sette en ny masterfil under " +
                            "'Filbehandling for masterliste' på Administratorsiden.");
                    alert.showAndWait();
                    throw new AvvikBruker("Fant ikke masterfil!");
                }

            }else{
                fis = new FileInputStream(new File
                        ("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponenterAdmin/"+filnavn));
            }

            ObjectInputStream ois = new ObjectInputStream(fis);

            boolean ok = true;
            while (ok) {
                if(ois != null){
                    Object produktObject = ois.readObject();
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
            //kaster visst exception uansett hva man gjør, men så lenge man handler den
            // går det fint
            //https://stackoverflow.com/questions/27409718/java-reading-multiple-objects-from-a-file-as-they-were-in-an-array
        }
        return list;
    }
    public String getMasterFil(){
        try (BufferedReader reader = Files.newBufferedReader(Paths.get
                ("Datamaskin/Kodelinjer/src/org/openjfx/Models/KomponentMasterlisteAdmin/masterlist"))) {
            String ut = "";
            String linje;
            while ((linje = reader.readLine()) != null) {
                  ut = linje;
            }
            return ut;
        }
        catch(FileNotFoundException fe){
            fe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
