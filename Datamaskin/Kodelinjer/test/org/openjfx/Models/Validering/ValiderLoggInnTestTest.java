package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.ExceptionLogInUsername;
import org.openjfx.Models.Avvik.ExceptionLogInPassword;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws ExceptionLogInUsername, ExceptionLogInPassword {
        //Bare små bokstaver:

        assertTrue(ValiderLoggInn.valideringBrukernavn("camillacamilla"));
        assertTrue(ValiderLoggInn.valideringBrukernavn("camillacamilla"));
        //store og små bokstaver:
        assertTrue(ValiderLoggInn.valideringBrukernavn("HalloHallo"));
        assertTrue(ValiderLoggInn.validerPassord("HalloHallo"));
        //bare store bokstaver:
        assertTrue(ValiderLoggInn.valideringBrukernavn("HALLOEN"));
        assertTrue(ValiderLoggInn.validerPassord("HALLOEN"));



    }
    @Test
    public void testLoggInnFeil() throws ExceptionLogInUsername, ExceptionLogInPassword {
        //tomme felt:

        assertThrows(ExceptionLogInUsername.class, () -> ValiderLoggInn.valideringBrukernavn(""));
        assertThrows(ExceptionLogInPassword.class, () -> ValiderLoggInn.validerPassord(""));
        //for kort input:
        assertThrows(ExceptionLogInUsername.class, () -> ValiderLoggInn.valideringBrukernavn("a"));
        assertThrows(ExceptionLogInPassword.class, () -> ValiderLoggInn.validerPassord("a"));
        //for lang input:
        assertThrows(ExceptionLogInUsername.class, () -> ValiderLoggInn.valideringBrukernavn("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        assertThrows(ExceptionLogInPassword.class, () -> ValiderLoggInn.validerPassord("aklafkaøfkølaflkkfaølkaøklfkløkøakøfkøafkkjgkrjiegmkslngklsjgklsjglsjglksgjlsgjlsgjgs"));




      
    }
}