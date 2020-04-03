package org.openjfx.Models.Filbehandling.FilLagring;

import org.openjfx.Model.Interfaces.FilLagring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//import org.openjfx.Models.Formaterer.KonfigurasjonsFormaterer;

//MÅ FINNE UT AV DET MED JOBJ.


public class FilLagringAdminJobj implements FilLagring {

    public void skrivString(String str, Path path) throws IOException{
        Files.write(path, str.getBytes());
    }

}
