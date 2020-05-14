package org.openjfx.Models.Validering;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//BLIR IKKE BRUKT
public class ValiderCSVFil {

    private final String TXT_STRENG_KOMPONENTER = "^([A-ZÆØÅa-zæøå0-9 ]{2,50};)([A-ZÆØÅa-zæøå ]{2,50};)((?:[1-9]\\d.{2,7}|1);)([a-z]{4,5};)$";
    private final String TXT_STRENG_ADMIN="^([A-ZÆØÅa-zæøå0-9 ]{2,50};)([A-ZÆØÅa-zæøå ]{2,50};)$";
    private final String TXT_STRENG_BRUKER="^([A-ZÆØÅa-zæøå0-9 ]{2,50};)([A-ZÆØÅa-zæøå ]{2,50};)$";

    public void testKomponenterFil(String filnavn){
        try{
            BufferedReader br= new BufferedReader(new FileReader(filnavn));
            String linje= br.readLine();
            int linjeTeller=0;
            while(linje!=null){

                if(linje.matches(TXT_STRENG_KOMPONENTER)){
                    System.out.println("Linje "+ linjeTeller + " er riktig formatert. "+ linje);
                }
                else{
                    System.out.println("Linje "+ linjeTeller+" er ikke riktig formatert."+ linje);
                    //Slette linje. evt alert boks med valg om sletting.
                }
                linje= br.readLine();
                linjeTeller++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void testAdminBrukerCSVFil(String filnavn){

        try{
            BufferedReader br= new BufferedReader(new FileReader(filnavn));
            String linje= br.readLine();
            int linjeTeller=0;
            while(linje!=null){

                if(linje.matches(TXT_STRENG_ADMIN)){
                    System.out.println("Linje "+ linjeTeller + " er riktig formatert. "+ linje);
                }
                else{
                    System.out.println("Linje "+ linjeTeller+" er ikke riktig formatert."+ linje);
                    //Slette linje. evt alert boks med valg om sletting.
                }
                linje= br.readLine();
                linjeTeller++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testBrukerCSVFil(String filnavn){

        try{
            BufferedReader br= new BufferedReader(new FileReader(filnavn));
            String linje= br.readLine();
            int linjeTeller=0;
            while(linje!=null){

                if(linje.matches(TXT_STRENG_BRUKER)){
                    System.out.println("Linje "+ linjeTeller + " er riktig formatert. "+ linje);

                }
                else{
                    System.out.println("Linje "+ linjeTeller+" er ikke riktig formatert."+ linje);
                    //Slette linje. evt alert boks med valg om sletting.
                }
                linje= br.readLine();
                linjeTeller++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

