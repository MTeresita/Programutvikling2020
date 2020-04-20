package org.openjfx.Models.Validering;

import org.openjfx.Models.Avvik.AvvikKomponent;
import org.openjfx.Models.Avvik.AvvikKomponentPris;
import org.openjfx.Models.Avvik.AvvikLoggInn;

public class ValideringKomponent {



    public static boolean validerProduktnavn(String produktnavn) throws AvvikKomponent {

        if(!produktnavn.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikKomponent("Produktnavn må være mellom 2 og 50 tegn.");
        }
        else if(produktnavn.isBlank() || produktnavn.isEmpty()){
            throw new AvvikKomponent("Alle feltene må fylles inn");
        }
        else{
            return true;
        }

    }

    public static boolean validerNyKategori(String nyKategori) throws AvvikKomponent{

        if(!nyKategori.matches("^[A-ZÆØÅa-zæøå]{5,50}$")){
            throw new AvvikKomponent("Produktnavn må være mellom 2 og 50 tegn.");
        }
        else if(nyKategori.isBlank() || nyKategori.isEmpty()){
            throw new AvvikKomponent("Alle feltene må fylles inn");
        }
        else{
            return true;
        }
    }

    public static boolean validerPris(double pris) throws AvvikKomponentPris {

        if(pris <= 0 || pris > 999999){
            throw new AvvikKomponentPris("Pris må være over 0 NOK og under 1 000 000 NOK");
        }
        else{
            return true;
        }

    }
}

