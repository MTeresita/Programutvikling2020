package org.openjfx.Model.Interfaces;

import org.openjfx.Models.Produkt;

import java.io.IOException;
import java.util.List;

public interface FilHenting {

    public List<Produkt> lesingFraFil(String path) throws IOException;
}
