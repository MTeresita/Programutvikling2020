package org.openjfx.Models.Avvik;

public class ValideringKomponent {

    private StringBuilder invalidValues = new StringBuilder();

    public String getInvalidKomponent(String nyKategori, double pris) throws AvvikKomponentProduktnavn, AvvikKomponentPris {
        if(!sjekkForTommeFlt(nyKategori)){
            validerProduktnavn(nyKategori);
            validerPris(pris);
        }
        return invalidValues.toString();
    }

    public boolean validerProduktnavn(String produktnavn) throws AvvikKomponentProduktnavn {

        if(!produktnavn.matches("^[A-ZÆØÅa-zæøå0-9 ]{2,50}$")){
            throw new AvvikKomponentProduktnavn("Produktnavn må være mellom 2 og 50 tegn.");
        }
            return true;
    }


    public  boolean validerNyKategori(String nyKategori) throws AvvikKomponentNyKategori {

        if(!nyKategori.matches("^[A-ZÆØÅa-zæøå]{2,50}$")){
            throw new AvvikKomponentNyKategori("Produktnavn må være mellom 2 og 50 tegn.");
        }
        return true;
    }

    public boolean sjekkNyKategori(String nyKategori) {
        try {
            if(validerNyKategori(nyKategori)){
                return true;
            }
        } catch (AvvikKomponentNyKategori avvikKomponentNyKategori) {
            avvikKomponentNyKategori.printStackTrace();
        }
        return false;
    }

    public boolean validerPris(double pris) throws AvvikKomponentPris {

        if(pris <= 0 || pris > 999999){
            throw new AvvikKomponentPris("Pris må være over 0 NOK og under 1 000 000 NOK");
        }
            return true;

    }

    private boolean sjekkPris(double pris){
        try {
            if(validerPris(pris)){
                return true;
            }
        } catch (AvvikKomponentPris avvikKomponentPris) {
            avvikKomponentPris.printStackTrace();
        }
        return false;
    }

    private boolean sjekkForTommeFlt(String nyKategori){
        StringBuilder sb = new StringBuilder();
        if(nyKategori.isBlank() || nyKategori.isEmpty()){
            sb.append("Alle feltene må fylles inn");
        }
        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }

        return true;
    }

    private boolean sjekkTommeFelt(String nykategori){
        try {
            if (sjekkForTommeFlt(nykategori)) {
                return true;
            }
        } catch (EmptyFieldsException ef) {
            invalidValues.append(ef.getMessage());
        }
        return false;
    }
}
