package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;

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


    public static boolean valideringBrukernavn(String brukernavn) throws AvvikLoggInnBrukernavn {

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && !brukernavn.isEmpty()){
            throw new AvvikLoggInnBrukernavn("Brukernavn må være mellom 5-50 bokstaver langt \n");
        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty()) {
            throw new AvvikLoggInnBrukernavn("Brukernavn kan ikke være tomt\n");
        }
        return true;
    }
    public boolean sjekkBrukerNavn(String brukernavn){
        try {
            if (valideringBrukernavn(brukernavn)){
                return true;
            }
        } catch (AvvikLoggInnBrukernavn avvikLoggInnBrukernavn) {
           ugyldigData.append(avvikLoggInnBrukernavn.getMessage());
        }
        return false;
    }

    public static boolean validerPassord(String passord) throws AvvikLoggInnPassord {

        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && (!passord.isEmpty() && !passord.isBlank())){
            throw new AvvikLoggInnPassord("Passord må være mellom 5-50 bokstaver langt\n");
        }
        else if(passord.isBlank() || passord.isEmpty()) {
            throw new AvvikLoggInnPassord("Passord feltet kan ikke være tomt\n");
        }
        return true;
    }

    public boolean validerGjentattPassord(String gjentattPassord) throws AvvikLoggInnPassord {
        if(!gjentattPassord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && (!gjentattPassord.isEmpty() && !gjentattPassord.isBlank())){
            throw new AvvikLoggInnPassord("Gjentatt passord felt må være mellom 5-50 bokstaver langt\n");
        }
        else if(gjentattPassord.isBlank() || gjentattPassord.isEmpty()) {
            throw new AvvikLoggInnPassord("Gjentatt passord feltet kan ikke være tomt\n");
        }
        return true;
    }

    public boolean sjekkPassord(String passord){
        try {
            if(validerPassord(passord)){
                return true;
            }
        } catch (AvvikLoggInnPassord avvikLoggInnPassord) {
            ugyldigData.append(avvikLoggInnPassord.getMessage());
        }
        return false;
    }
    public boolean sjekkGjentattPassord(String passord){
        try{
            if(validerGjentattPassord(passord)){
                return true;
            }
        } catch (AvvikLoggInnPassord avvikLoggInnPassord) {
            ugyldigData.append(avvikLoggInnPassord.getMessage());
        }
        return false;

    }


    /*
    public boolean validerDobbelPassord(String passord, String passord1) throws EmptyFieldsException{
        StringBuilder sb = new StringBuilder();

        if((passord1.isEmpty() || passord1.isBlank()) && (passord.isEmpty() || passord.isBlank())){
            sb.append("Passord feltene kan ikke være tomme\n");
        }

        if(!passord.equals(passord1) && !passord1.isEmpty()){
            sb.append("Passordene er ikke like \n");
        }

        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }
        return true;
    }

    public boolean sjekkTomtPassord(String passord, String passord1){
        try {
            if (validerDobbelPassord(passord, passord1)) {
                return true;
            }
        }
        catch (EmptyFieldsException e){
            ugyldigData.append(e.getMessage());
        }
        return false;
    }

     */

}
