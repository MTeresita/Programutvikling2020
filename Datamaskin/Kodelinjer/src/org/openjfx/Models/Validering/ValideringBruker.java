package org.openjfx.Models.Validering;
//regex for opprettelse av bruker og innlogging bruker.

public class ValideringBruker {

    public boolean validerInnloggingBruker(String brukernavn, String passord){

        boolean validering=false;

        if(brukernavn.matches("^[A-æøå]((?![-]$)[A-æøå .,'-]?){2,50}$")){
            if(passord.matches("^[A-æøå0-9-._]{8,}$")){
                validering=true;
            }

        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty() || passord.isBlank() || passord.isEmpty()){
            validering=false;
        }

        return validering;
    }

}
