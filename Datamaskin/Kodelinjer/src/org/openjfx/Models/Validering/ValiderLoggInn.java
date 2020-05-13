package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.ExceptionLogInUsername;
import org.openjfx.Models.Avvik.ExceptionLogInPassword;

public class ValiderLoggInn {

    private StringBuilder ugyldigData = new StringBuilder();

    public String sjekkUgyldigData(String brukernavn, String passord){
            sjekkBrukerNavn(brukernavn);
            sjekkPassord(passord);

        return ugyldigData.toString();
    }

    public String sjekkUgyldigRegistrering(String brukernavn, String passord, String passord1){
        sjekkBrukerNavn(brukernavn);
        if((passord.isEmpty() || passord1.isBlank()) && (passord1.isEmpty() || passord1.isBlank())){
            ugyldigData.append("Et eller flere passord felt er tomme \n");
        }
        else{
            sjekkPassord(passord);
            sjekkGjentattPassord(passord1);
        }
        if(!passord.equals(passord1)){
                ugyldigData.append("Passordene er ikke like\n");
        }

        return ugyldigData.toString();
    }


    public static boolean valideringBrukernavn(String brukernavn) throws ExceptionLogInUsername {

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && !brukernavn.isEmpty()){
            throw new ExceptionLogInUsername("Brukernavn må være mellom 5-50 bokstaver langt \n");
        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty()) {
            throw new ExceptionLogInUsername("Brukernavn kan ikke være tomt\n");
        }
        return true;
    }
    public boolean sjekkBrukerNavn(String brukernavn){
        try {
            if (valideringBrukernavn(brukernavn)){
                return true;
            }
        } catch (ExceptionLogInUsername exceptionLogInUsername) {
           ugyldigData.append(exceptionLogInUsername.getMessage());
        }
        return false;
    }

    public static boolean validerPassord(String passord) throws ExceptionLogInPassword {

        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && (!passord.isEmpty() && !passord.isBlank())){
            throw new ExceptionLogInPassword("Passord må være mellom 5-50 bokstaver langt\n");
        }
        else if(passord.isBlank() || passord.isEmpty()) {
            throw new ExceptionLogInPassword("Passord feltet kan ikke være tomt\n");
        }
        return true;
    }

    public boolean validerGjentattPassord(String gjentattPassord) throws ExceptionLogInPassword {
        if(!gjentattPassord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && (!gjentattPassord.isEmpty() && !gjentattPassord.isBlank())){
            throw new ExceptionLogInPassword("Gjentatt passord felt må være mellom 5-50 bokstaver langt\n");
        }
        else if(gjentattPassord.isBlank() || gjentattPassord.isEmpty()) {
            throw new ExceptionLogInPassword("Gjentatt passord feltet kan ikke være tomt\n");
        }
        return true;
    }

    public boolean sjekkPassord(String passord){
        try {
            if(validerPassord(passord)){
                return true;
            }
        } catch (ExceptionLogInPassword exceptionLogInPassword) {
            ugyldigData.append(exceptionLogInPassword.getMessage());
        }
        return false;
    }
    public boolean sjekkGjentattPassord(String passord){
        try{
            if(validerGjentattPassord(passord)){
                return true;
            }
        } catch (ExceptionLogInPassword exceptionLogInPassword) {
            ugyldigData.append(exceptionLogInPassword.getMessage());
        }
        return false;

    }

}
