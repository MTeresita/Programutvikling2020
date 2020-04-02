package org.openjfx.Models.Validering;

public class ValideringProdukt {

    public boolean validerNyttProdukt(String produktnavn, String nykategori, double pris){
        boolean validering=false;

        //matcher alle bokstaver A-ÆØÅ mellom 2-50 i lengde og tegn som !-.,
        if(produktnavn.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$")){

            if(nykategori.matches("^[A-æøå]((?![-]$)[A-æøå.,'-]?){2,50}$") && pris>0){
                validering=true;
            }

        }
        else if(produktnavn.isBlank() || produktnavn.isEmpty() || nykategori.isBlank() || nykategori.isEmpty() || pris<=0){
            validering=false;
        }

        return validering;
    }




}

