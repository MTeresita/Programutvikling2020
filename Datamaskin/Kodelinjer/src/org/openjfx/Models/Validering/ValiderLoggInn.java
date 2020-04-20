package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;

public class ValiderLoggInn {


    public static boolean valideringBrukernavn(String brukernavn) throws AvvikLoggInnBrukernavn {

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikLoggInnBrukernavn("Feil i brukernavn.");
        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty()){
           throw new AvvikLoggInnBrukernavn("Brukernavn må fylles inn");
        }
        else{
            return true;
        }

    }

    public static boolean validerPassord(String passord) throws AvvikLoggInnPassord {

        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikLoggInnPassord("Feil i passord");
        }
        else if(passord.isBlank() || passord.isEmpty()){
            throw new AvvikLoggInnPassord("Passord må fylles inn");
        }
        else{
            return true;
        }

    }
}
