package org.openjfx.Models.Validering;

public class ValideringKomponent {



    public static boolean validerProduktnavn(String produktnavn)  {
        boolean validering;

        if(produktnavn.matches("^[A-ZÆØÅa-zæøå]{2,50}$")) {
            validering=true;
        }
        else{
            validering=false;
            //throw new AvvikProdukt("Produktnavn må være mellom 2 og 50 tegn.");
        }
        if(produktnavn.isBlank() || produktnavn.isEmpty()){
            validering=false;
            //throw new AvvikProdukt("Alle feltene må fylles inn");

        }
        return validering;
    }

    public static boolean validerNyKategori(String nyKategori){
        boolean validering;
        if(nyKategori.matches("^[A-ZÆØÅa-zæøå]{2,50}$")){
            validering=true;

        }
        else{
            validering=false;
            //throw new AvvikProdukt("Alle feltene må fylles inn");
        }
        if(nyKategori.isBlank() || nyKategori.isEmpty()){
            validering=false;
            //throw new AvvikProdukt("Alle felter må fylles inn");
        }
        return validering;
    }

    public static boolean validerPris(double pris)  {
        boolean validering;

        if(pris>0){
            validering =true;
        }
        else{
            validering=false;
           // throw new AvvikProdukt("Pris må være over 0 kr");
        }
        return validering;
    }
}

