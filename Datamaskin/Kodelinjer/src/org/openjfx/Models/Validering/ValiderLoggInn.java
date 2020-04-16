package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


public class ValiderLoggInn {


    public static boolean valideringBrukernavn(String brukernavn)  {

        boolean validering;
        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"
        if(brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$")) {
            validering=true;
        }
        else{
            validering=false;
            //throw new AvvikLoggInn("Brukernavn må være minst 8 tegn langt og ikke lenger enn 50 tegn");
        }
        if(brukernavn.isBlank() || brukernavn.isEmpty()){
            validering=false;
           //throw new AvvikLoggInn("Brukernavn må fylles inn");
        }

        return validering;
    }

    public static boolean validerPassord(String passord)  {
        boolean validering;
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"
        if(passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$")) {
            validering=true;
        }
        else{
            validering=false;
            //throw new AvvikLoggInn("Passord må være minst 5 bokstaver langt");
        }
        if(passord.isBlank() || passord.isEmpty()){
            validering=false;
            //throw new AvvikLoggInn("Brukernavn og passord må fylles inn.");
        }

        return validering;
    }
}
