package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;

public class ValideringLoggInn {

    private StringBuilder ugyldigData = new StringBuilder();

    public String sjekkUgyldigData(String brukernavn, String passord){
        ugyldigData.setLength(0);
        sjekkBrukerNavn(brukernavn);
            sjekkPassord(passord);

        return ugyldigData.toString();
    }

    public String sjekkUgyldigRegistrering(String brukernavn, String passord, String passord1){
        ugyldigData.setLength(0);
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
        

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{3,20}$") && !brukernavn.isEmpty()){
            throw new AvvikLoggInnBrukernavn("Brukernavn må være mellom 3-20 bokstaver langt \n");
        }
        else if(brukernavn.isBlank() || brukernavn.isEmpty()) {
            throw new AvvikLoggInnBrukernavn("Brukernavn feltet kan ikke være tomt\n");
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

        if(!passord.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{5,20}$") && (!passord.isEmpty() && !passord.isBlank())){
            throw new AvvikLoggInnPassord("Passord må være mellom 5-20 bokstaver langt\n");
        }
        else if(passord.isBlank() || passord.isEmpty()) {
            throw new AvvikLoggInnPassord("Passord feltet kan ikke være tomt\n");
        }
        return true;
    }

    public boolean validerGjentattPassord(String gjentattPassord) throws AvvikLoggInnPassord {
        if(!gjentattPassord.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{5,20}$") &&
                (!gjentattPassord.isEmpty() && !gjentattPassord.isBlank())){
            throw new AvvikLoggInnPassord("Gjentatt passord må matche\n");
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

}
