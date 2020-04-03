package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Models.Produkt;
import org.openjfx.Models.Parsing.KonfigurasjonsParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilHentingBruker implements org.openjfx.Model.Interfaces.FilHenting {
    @Override
    public List<Produkt> lesingFraFil(String path) throws IOException {
        ArrayList<Produkt> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String linje;

            //Lager en ny produktlinje.
            while ((linje = reader.readLine()) != null) {

                //bruker parseProdukt til å gjøre om til et objekt fra fil.
                list.add((KonfigurasjonsParser.parseProdukt(linje)));

            }
            return list;
        }
        catch(FileNotFoundException fe){
            fe.getMessage();
        }
        return list;
    }
}