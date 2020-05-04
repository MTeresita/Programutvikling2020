package org.openjfx.Models.Validering;
//regEx for loggInnAdmin, og registrering av ny admin.


import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;
import org.openjfx.Models.Avvik.EmptyFieldsException;

public class ValiderLoggInn {

    private StringBuilder ugyldigData = new StringBuilder();

    public String sjekkUgyldigData(String brukernavn, String passord){
        if(!sjekkTommeFelt(brukernavn, passord)){
            sjekkBrukerNavn(brukernavn);
            sjekkPassord(passord);
        }

        return ugyldigData.toString();
    }

    public String sjekkUgyldigRegistrering(String brukernavn, String passord, String passord1){
        sjekkUgyldigData(brukernavn, passord);
        sjekkTomtPassord(passord, passord1);
        return ugyldigData.toString();
    }


    public boolean valideringBrukernavn(String brukernavn) throws AvvikLoggInnBrukernavn {

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        //"^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$"

        if(!brukernavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && !brukernavn.isEmpty()){
            throw new AvvikLoggInnBrukernavn("Brukernavn må være mellom 5-50 bokstaver langt \n");
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

    public boolean validerPassord(String passord) throws AvvikLoggInnPassord {

        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && !passord.isEmpty()){
            throw new AvvikLoggInnPassord("Passord må være mellom 5-50 bokstaver langt\n");
        }
        return true;
    }

    public boolean validerGjentattPassord(String passord) throws AvvikLoggInnPassord {
        if(!passord.matches("^[A-ZÆØÅa-zæøå]{5,50}$") && !passord.isEmpty()){
            throw new AvvikLoggInnPassord("Passord må være mellom 5-50 bokstaver langt\n");
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
        try {
            if(validerGjentattPassord(passord)){
                return true;
            }
        } catch (AvvikLoggInnPassord avvikLoggInnPassord) {
            ugyldigData.append(avvikLoggInnPassord.getMessage());
        }
        return false;
    }

    public boolean validerTommeFelt(String brukernavn, String passord) throws EmptyFieldsException {
        StringBuilder sb = new StringBuilder();

        if(brukernavn.isEmpty() || brukernavn.isBlank()){
            sb.append("Brukernavnet kan ikke være tomt\n");
        }
        if(!brukernavn.isEmpty()){
            sjekkBrukerNavn(brukernavn);
        }

        if(passord.isEmpty() || passord.isBlank()){
            sb.append("Passord feltet kan ikke være tomt\n");
        }
        if(!passord.isEmpty()){
            sjekkPassord(passord);
        }

        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }

        return true;
    }

    public boolean sjekkTommeFelt(String brukernavn, String passord){
        try {
            if (validerTommeFelt(brukernavn,passord)) {
                return true;
            }
        } catch (EmptyFieldsException ef) {
            ugyldigData.append(ef.getMessage());
        }
        return false;
    }

    public boolean validerTomtPassord(String passord, String passord1) throws EmptyFieldsException{
        StringBuilder sb = new StringBuilder();


        if(passord1.isEmpty() || passord1.isBlank()){
            sb.append("Passord feltene kan ikke være tomme\n");
        }
        if(!passord1.isEmpty()){
            sjekkPassord(passord);
        }
        if(!passord.equals(passord1)){
            sb.append("Passordene er ikke like \n");
        }

        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }
        return true;
    }

    public boolean sjekkTomtPassord(String passord, String passord1){
        try {
            if (validerTomtPassord(passord, passord1)) {
                return true;
            }
        }
        catch (EmptyFieldsException e){
            ugyldigData.append(e.getMessage());
        }
        return false;
    }

}
