package org.openjfx.Models.Validering;

import javafx.scene.control.ComboBox;
import org.openjfx.Models.Avvik.ExceptionComponentNewCategory;
import org.openjfx.Models.Avvik.ExceptionComponentPrice;
import org.openjfx.Models.Avvik.ExceptionComponentProductName;

import java.util.regex.Pattern;

public class ValideringKomponent {

    StringBuilder ugyldigData = new StringBuilder();

    public String sjekkUgyldigKomponent(String produktnavn, String nyKategori, String pris, ComboBox box){
        sjekkProduktNavn(produktnavn);
        sjekkNyKatergori(nyKategori, box);


        if(!pris.isEmpty() || !pris.isBlank()) {
            try {
                Double innPris = Double.parseDouble(pris);
                if (innPris > 999999) {
                    ugyldigData.append("Pris kan ikke være høyere enn\n 999 999 NOK\n");
                }
                if (innPris <= 0) {
                    ugyldigData.append("Pris kan ikke være mindre enn 0\n");
                } else {
                    sjekkPris(pris);
                }

            } catch (NumberFormatException e) {
                ugyldigData.append("Pris må skrives inn som tall");
            }
        }
        else{
            sjekkPris(pris);
        }


        return ugyldigData.toString();
    }

    public static boolean validerProduktnavn(String produktnavn) throws ExceptionComponentProductName {

        if(!produktnavn.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{2,50}$") && !produktnavn.isEmpty()){
            throw new ExceptionComponentProductName("Produktnavn må være mellom 2 og 50 tegn. \n");
        }
        else if(produktnavn.isBlank() || produktnavn.isEmpty()){
            throw new ExceptionComponentProductName("Produktnavn kan ikke være tomt\n");
        }

            return true;


    }

    public boolean sjekkProduktNavn(String produktnavn){
        try {
            if (validerProduktnavn(produktnavn)){
                return true;
            }
        } catch (ExceptionComponentProductName komponentProduktnavn) {
            ugyldigData.append(komponentProduktnavn.getMessage());
        }
        return false;
    }


    public static boolean validerNyKategori(String nyKategori, ComboBox box) throws ExceptionComponentNewCategory {

        if(!nyKategori.matches("^[A-ZÆØÅa-zæøå _@./#&+-]{2,50}$") && !nyKategori.isEmpty()){
            throw new ExceptionComponentNewCategory("Kategori må være mellom 2 og 50 tegn.\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) && box.getSelectionModel().isEmpty()){
            throw new ExceptionComponentNewCategory("Kategori er ikke valgt\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) &&
                box.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")){
            throw new ExceptionComponentNewCategory("Kategori kan ikke være tomt\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) && !box.getSelectionModel().isEmpty()){
            throw  new ExceptionComponentNewCategory("");
        }


        return true;
    }

    public boolean sjekkNyKatergori(String nykategori, ComboBox box) {
        try {
            if (validerNyKategori(nykategori, box)){
                return true;
            }
        } catch (ExceptionComponentNewCategory exceptionComponentNewCategory) {
            ugyldigData.append(exceptionComponentNewCategory.getMessage());
        }
        return false;
    }

    public static boolean validerPris(String pris) throws ExceptionComponentPrice {

        if((!Pattern.matches("[0-9]+", pris) || !pris.equals(""))){
            throw new ExceptionComponentPrice("Pris må skrives inn som tall\n");
        }
        if(pris.isBlank() || pris.isEmpty()){
            throw new ExceptionComponentPrice("Pris feltet kan ikke være tomt\n");
        }

        return true;
    }

    public boolean sjekkPris(String pris) {
        try {
            if(validerPris(pris)){
                return true;
            }
        } catch (ExceptionComponentPrice exceptionComponentPrice){
            ugyldigData.append(exceptionComponentPrice.getMessage());
        }
        return false;
    }

}
