package org.openjfx.Models.Interfaces;

import java.io.IOException;
import java.nio.file.Path;

//TODO denne blir ikke brukt, slette?
public interface FilLagring {
    public void skrivString(String str, Path path) throws IOException;
}
