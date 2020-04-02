package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


public class ValideringAdministrator {


    public boolean validerLoggInnAdmin(String brukernavn, String passord){


        boolean validering=false;
        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        if(brukernavn.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$")){

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
