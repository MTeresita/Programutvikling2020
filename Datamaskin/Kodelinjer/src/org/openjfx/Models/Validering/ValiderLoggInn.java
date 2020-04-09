package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInn;

public class ValiderLoggInn {


    public static boolean valideringBrukernavn(String brukernavn) throws AvvikLoggInn {

        boolean validering;
        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"
        if(brukernavn.matches("^[A-ZÆØÅa-zæøå]{8,50}$")) {
            validering=true;
        }
        else{
            validering=false;
            //throw new AvvikLoggInn("Brukernavn må være minst 8 tegn langt");
        }
        if(brukernavn.isBlank() || brukernavn.isEmpty()){
            validering=false;
           // throw new AvvikLoggInn("Brukernavn må fylles inn");
        }

        return validering;
    }

    public static boolean validerPassord(String passord) throws AvvikLoggInn {
        boolean validering;

        if(passord.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$")) {
            validering=true;
        }
        else{
            validering=false;
            //throw new AvvikLoggInn("Brukernavn må være minst 8 tegn langt");
        }
        if(passord.isBlank() || passord.isEmpty()){
            validering=false;
            //throw new AvvikLoggInn("Brukernavn og passord må fylles inn.");
        }

        return validering;
    }
}
