package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikAdministrator;

public class ValideringAdministrator {


    public boolean validerLoggInnAdmin(String brukernavn, String passord) throws AvvikAdministrator {


        boolean validering=false;
        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        if(brukernavn.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$")){

            if(passord.matches("^[A-æøå0-9-._]{8,}$")){
                validering=true;
            }
            else{
                validering=false;
                throw new AvvikAdministrator("Passord må være minst 8 tegn langt");
            }

        }
        else{
            validering=false;
            throw new AvvikAdministrator("Brukernavn må være mellom 2 og 50 tegn");
        }
        if(brukernavn.isBlank() || brukernavn.isEmpty() || passord.isBlank() || passord.isEmpty()){
            validering=false;
            throw new AvvikAdministrator("Brukernavn og passord må fylles inn.");
        }

        return validering;
    }
}
