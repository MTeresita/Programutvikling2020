package org.openjfx.FilLagring;

import org.openjfx.Model.Interfaces.FilLagring;
import org.openjfx.Models.Produkt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilLagringBruker implements FilLagring {
    public void skrivString(String str, Path path) throws IOException{
        Files.write(path, str.getBytes());
    }
}
