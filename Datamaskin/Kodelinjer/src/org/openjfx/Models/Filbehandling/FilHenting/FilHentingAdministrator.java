package org.openjfx.Models.Filbehandling.FilHenting;

import org.openjfx.Model.Interfaces.FilHenting;
import org.openjfx.Models.KomponenterListe;
import org.openjfx.Models.Parsing.KonfigurasjonsParseAdmin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class FilHentingAdministrator implements FilHenting {

    @Override
    public List<KomponenterListe> lesingFraFil(String path) throws IOException {
        ArrayList<KomponenterListe> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String linje;

            //Lager en ny produktlinje.
            while ((linje = reader.readLine()) != null) {

                //bruker parseProdukt til å gjøre om til et objekt fra fil.
                list.add((KonfigurasjonsParseAdmin.parseKomponenterListe(linje)));

                }
            return list;
        }

        catch (FileNotFoundException fe){
            fe.getMessage();
        }

        return list;

    }

}
