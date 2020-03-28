package org.openjfx.FilHenting;

import org.openjfx.Model.Interfaces.FilHenting;
import org.openjfx.Models.Produkt;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilHentingBruker implements FilHenting {
    @Override
    public List<Produkt> read(String path) throws IOException {
        ArrayList<Produkt> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;

            //Lager en ny produktlinje.
            while((line=reader.readLine()) != null) {
                //list.add(line); må parses.
            }
        }
        return list;
    }
}
