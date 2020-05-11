package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Models.Interfaces.FilHenting;
import org.openjfx.Models.Komponent;
import org.openjfx.Models.Parsing.KonfigurasjonsParserBruker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FilHentingBruker implements FilHenting {
    @Override
    public ArrayList<Komponent> lesingFraFil(String path) throws IOException {

        ArrayList<Komponent> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String linje;

            //Lager en ny produktlinje.
            while ((linje = reader.readLine()) != null) {

                //bruker parseProdukt til å gjøre om til et objekt fra fil.
                //System.out.println("linje: "+linje);
                if(KonfigurasjonsParserBruker.parseKonfigurasjonBruker(linje) != null){
                    list.add((KonfigurasjonsParserBruker.parseKonfigurasjonBruker(linje)));
                }
            }
            return list;
        }
        catch(FileNotFoundException fe){
            fe.getMessage();
        }
        return list;
    }


}

