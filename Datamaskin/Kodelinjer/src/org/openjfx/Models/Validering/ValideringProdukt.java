package org.openjfx.Models.Validering;

import org.openjfx.Models.Avvik.AvvikProdukt;

public class ValideringProdukt {

    public boolean validerNyttProdukt(String produktnavn, String nykategori, double pris) throws AvvikProdukt {
        boolean validering=false;

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        if(produktnavn.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$")){

            if(nykategori.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$") && pris>0) {
                validering=true;
            }
            else{
                validering=false;
                throw new AvvikProdukt("Kategori må være mellom 2 og 50 tegn.");
            }

        }
        else{
            validering=false;
            throw new AvvikProdukt("Produktnavn må være mellom 2 og 50 tegn.");
        }
        if(produktnavn.isBlank() || produktnavn.isEmpty() || nykategori.isBlank() || nykategori.isEmpty() || pris<=0){
            validering=false;
            throw new AvvikProdukt("Pris må være over 0 og kan ikke være lik 0. Alle feltene må fylles inn");

        }
        return validering;
    }




}

