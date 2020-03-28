package org.openjfx.FilLagring;

import org.openjfx.Model.Interfaces.FilLagring;
import org.openjfx.Models.Produkt;
import org.openjfx.Models.ProduktListe;
//import org.openjfx.Formaterer.KonfigurasjonsFormaterer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FilLagringAdmin implements FilLagring {

    public void skrivString(String str, Path path) throws IOException{
        Files.write(path, str.getBytes());
    }

}
