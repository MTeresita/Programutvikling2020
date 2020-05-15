package org.openjfx.Models.HjelpeKlasser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BrukerSystemSjekk {

    // generell metode som går gjennom filene for å finne bruker.
    public static boolean verifiserLoggInn(String brukernavn, String passord, String fil) throws IOException {

        boolean funnet = false;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fil));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            String line;

            while ((line = reader.readLine()) != null) {
                // splitter filen med ;
                String[] split = line.split(";");
                //hvis brukernavnet og passordet er like --> vil du kunne logge inn
                if (brukernavn.equals(split[0]) && passord.equals(split[1])) {
                    funnet = true;
                }

            }
        }
        return funnet;
    }

    public static boolean sjekkOmBrukerEksiterer(String brukernavn, String fil) throws IOException {
        boolean funnet = false;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fil));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                //hvis brukernavnet eksisterer --> er den funnet
                if (brukernavn.equals(split[0])) {
                    funnet = true;
                }
            }
        }
        //ikke funnet, returnerer false
        return funnet;

    }

}
