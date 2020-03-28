package org.openjfx.Model.Interfaces;

import org.openjfx.Models.Produkt;

import java.io.IOException;
import java.util.List;

public interface FilHenting {
    public List<Produkt> read(String path) throws IOException;
}
