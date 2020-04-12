package org.openjfx.Models.Avvik;

import java.io.IOException;

public class InvalidPasswordException extends IOException {
    public InvalidPasswordException(String msg){
        super(msg);
    }
}
