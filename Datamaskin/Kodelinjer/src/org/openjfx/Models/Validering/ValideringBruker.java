package org.openjfx.Models.Validering;
//regex for opprettelse av bruker og innlogging bruker.

import org.openjfx.Models.Avvik.AvvikBruker;

public class ValideringBruker {

    public boolean validerInnloggingBruker(String brukernavn, String passord)throws AvvikBruker {

        boolean validering=false;

        if(brukernavn.matches("^[A-æøå]((?![-]$)[A-æøå .,'-]?){2,50}$")){
            if(passord.matches("^[A-æøå0-9-._]{8,}$")){
                validering=true;
            }
            else{
                throw new AvvikBruker("Passord må være minst 8 tegn langt");
            }

        }
        else {
            validering=false;
            throw new AvvikBruker("Brukernavnet må være mellom 2 og 50 tegn");

        }
        if(brukernavn.isBlank() || brukernavn.isEmpty() || passord.isBlank() || passord.isEmpty()){
            validering=false;
            throw new AvvikBruker("Brukernavn og passord må være fylles inn");
        }

        return validering;
    }

}
