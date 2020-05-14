package org.openjfx.Models.Validering;

import javafx.scene.control.ComboBox;
import org.openjfx.Models.Avvik.AvvikAntallKomponenter;
import org.openjfx.Models.Avvik.AvvikKomponentNyKategori;
import org.openjfx.Models.Avvik.AvvikKomponentPris;
import org.openjfx.Models.Avvik.AvvikKomponentProduktnavn;

import java.util.regex.Pattern;

public class ValideringKomponent {

    StringBuffer ugyldigData = new StringBuffer();

    public String sjekkUgyldigKomponent(String produktnavn, String nyKategori, String pris, ComboBox box, String antall){

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
                ugyldigData.append("Pris må skrives inn som tall \n");
            }
        }
        else{
            sjekkPris(pris);
        }


        if(!antall.isEmpty() || !antall.isBlank()){
            try {
                Integer innAntall = Integer.parseInt(antall);

                if(innAntall <= 0){
                    ugyldigData.append("Antall kan ikke være mindre enn 0");
                }
                if (innAntall > 3){
                    ugyldigData.append("Antall kan ikke være større enn 3");
                }
            } catch (NumberFormatException e){
                ugyldigData.append("Antall må skrives inn som tall");
            }
        }
        else {
            sjekkAntall(antall);
        }



        return ugyldigData.toString();


    }


    public static boolean validerProduktnavn(String produktnavn) throws AvvikKomponentProduktnavn {

        if(!produktnavn.matches("^[A-ZÆØÅa-zæøå0-9 _@./#&+-]{2,50}$") && !produktnavn.isEmpty()){
            throw new AvvikKomponentProduktnavn("Produktnavn må være mellom 2 og 50 tegn. \n");
        }
        else if(produktnavn.isBlank() || produktnavn.isEmpty()){
            throw new AvvikKomponentProduktnavn("Produktnavn kan ikke være tomt\n");
        }

            return true;


    }

    public boolean sjekkProduktNavn(String produktnavn){
        try {
            if (validerProduktnavn(produktnavn)){
                return true;
            }
        } catch (AvvikKomponentProduktnavn komponentProduktnavn) {
            ugyldigData.append(komponentProduktnavn.getMessage());
        }
        return false;
    }


    public static boolean validerNyKategori(String nyKategori, ComboBox box) throws AvvikKomponentNyKategori {

        if(!nyKategori.matches("^[A-ZÆØÅa-zæøå _@./#&+-]{2,50}$") && !nyKategori.isEmpty()){
            throw new AvvikKomponentNyKategori("Kategori må være mellom 2 og 50 tegn.\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) && box.getSelectionModel().isEmpty()){
            throw new AvvikKomponentNyKategori("Kategori er ikke valgt\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) &&
                box.getSelectionModel().getSelectedItem().toString().equals("Ny Kategori...")){
            throw new AvvikKomponentNyKategori("Kategori kan ikke være tomt\n");
        }
        else if((nyKategori.isBlank() || nyKategori.isEmpty()) && !box.getSelectionModel().isEmpty()){
            throw  new AvvikKomponentNyKategori("");
        }


        return true;
    }

    public boolean sjekkNyKatergori(String nykategori, ComboBox box) {
        try {
            if (validerNyKategori(nykategori, box)){
                return true;
            }
        } catch (AvvikKomponentNyKategori avvikKomponentNyKategori) {
            ugyldigData.append(avvikKomponentNyKategori.getMessage());
        }
        return false;
    }

    public static boolean validerAntKonf(String antall) throws AvvikAntallKomponenter{
        if((!Pattern.matches("[0-9]+", antall) && !antall.equals(""))){
            throw new AvvikAntallKomponenter("Antall må skrives inn som tall\n");
        }
        if(antall.isBlank() || antall.isEmpty()){
            throw new AvvikAntallKomponenter("Antall feltet kan ikke være tomt\n");
        }

        return true;
    }

    public boolean sjekkAntall (String antall) {
        try{
            if(validerAntKonf(antall)){
                return true;
            }
        } catch (AvvikAntallKomponenter e ) {
            ugyldigData.append(e.getMessage());
        }
        return false;
    }

    public static boolean validerPris(String pris) throws AvvikKomponentPris {

        if((!Pattern.matches("[0-9]+", pris) && !pris.equals(""))){
            throw new AvvikKomponentPris("Pris må skrives inn som tall\n");
        }
        if(pris.isBlank() || pris.isEmpty()){
            throw new AvvikKomponentPris("Pris feltet kan ikke være tomt\n");
        }

        return true;
    }


    public boolean sjekkPris(String pris) {
        try {
            if(validerPris(pris)){
                return true;
            }
        } catch (AvvikKomponentPris avvikKomponentPris){
            ugyldigData.append(avvikKomponentPris.getMessage());
        }
        return false;
    }

}
