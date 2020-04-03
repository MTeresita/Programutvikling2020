package org.openjfx.Models.Filbehandling.FilLagring;

//import org.openjfx.Model.Interfaces.FilLagring;

import org.openjfx.Models.Interfaces.FilLagring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import org.openjfx.Models.Formaterer.KonfigurasjonsFormaterer;




public class FilLagringAdminJobj implements FilLagring {

    public void skrivString(String str, Path path) throws IOException{
        Files.write(path, str.getBytes());
    }

}
