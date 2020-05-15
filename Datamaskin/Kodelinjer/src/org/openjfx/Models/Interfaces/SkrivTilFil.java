package org.openjfx.Models.Interfaces;

import java.io.IOException;

public interface SkrivTilFil {
    void writer(Object object, String path, boolean append) throws IOException;
}
