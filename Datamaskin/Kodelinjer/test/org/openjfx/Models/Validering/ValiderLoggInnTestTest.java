package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInn;

import static org.junit.jupiter.api.Assertions.*;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInn {
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
    public void testLoggInnFeil() throws AvvikLoggInn {
        //tomme felt:

        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn(""));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord(""));
        //for kort input:
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn("a"));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord("a"));
        //for lang input:
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord("aklafkaøfkølaflkkfaølkaøklfkløkøakøfkøafkkjgkrjiegmkslngklsjgklsjglsjglksgjlsgjlsgjgs"));


        //tomme felt:
        assertFalse(ValiderLoggInn.valideringBrukernavn(""));
        assertFalse(ValiderLoggInn.validerPassord(""));
        //For kort input:
        assertFalse(ValiderLoggInn.valideringBrukernavn("a"));
        assertFalse(ValiderLoggInn.validerPassord("a"));
        //for lang input:
        assertFalse(ValiderLoggInn.valideringBrukernavn("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        assertFalse(ValiderLoggInn.validerPassord("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        //med tall:
        assertFalse(ValiderLoggInn.valideringBrukernavn("12345"));
        assertFalse(ValiderLoggInn.validerPassord("12345"));
    }
}