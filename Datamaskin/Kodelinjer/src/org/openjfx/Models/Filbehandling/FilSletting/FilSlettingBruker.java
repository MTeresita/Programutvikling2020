package org.openjfx.Models.Filbehandling.FilSletting;

import org.openjfx.Models.Interfaces.FilSletting;
import org.openjfx.Models.Parsing.KonfigurasjonsParserBruker;

import java.io.*;

public class FilSlettingBruker implements FilSletting {
    @Override
    public boolean slettingFraFil(String path) throws IOException {
        try {
            File file = new File(path);
            file.delete();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
