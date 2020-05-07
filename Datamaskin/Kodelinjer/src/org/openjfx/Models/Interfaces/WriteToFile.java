package org.openjfx.Models.Interfaces;

import java.io.IOException;

public interface WriteToFile {
    void writer(Object object, String path, boolean append) throws IOException;
}
