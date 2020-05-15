package org.openjfx.Models.Interfaces;

import java.io.IOException;
import java.util.List;

public interface FilHenting {

    List<?> lesingFraFil(String path) throws IOException;
}
