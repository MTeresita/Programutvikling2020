package org.openjfx.Model.Interfaces;

import org.openjfx.Models.Produkt;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FilLagring {
    public void skrivString(String str, Path path) throws IOException;
}
