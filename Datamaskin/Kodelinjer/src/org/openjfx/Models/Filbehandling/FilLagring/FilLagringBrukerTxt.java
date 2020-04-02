package org.openjfx.Models.Filbehandling.FilLagring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilLagringBrukerTxt {
    public void skrivString(String str, Path path) throws IOException {
        Files.write(path, str.getBytes());
    }
}
