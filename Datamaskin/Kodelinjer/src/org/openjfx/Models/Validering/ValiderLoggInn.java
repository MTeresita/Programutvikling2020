package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInn;

public class ValiderLoggInn {


    public static boolean valideringBrukernavn(String brukernavn) throws AvvikLoggInn  {

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikLoggInn("Feil i brukernavn.");
        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty()){
           throw new AvvikLoggInn("Brukernavn må fylles inn");
        }
        else{
            return true;
        }

    }

    public static boolean validerPassord(String passord) throws AvvikLoggInn{

        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikLoggInn("Feil i passord");
        }
        else if(passord.isBlank() || passord.isEmpty()){
            throw new AvvikLoggInn("Passord må fylles inn");
        }
        else{
            return true;
        }

    }
}
