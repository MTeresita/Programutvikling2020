package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInnBrukernavn;
import org.openjfx.Models.Avvik.AvvikLoggInnPassord;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInnBrukernavn, AvvikLoggInnPassord {
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
    public void testLoggInnFeil() throws AvvikLoggInnBrukernavn, AvvikLoggInnPassord {
        //tomme felt:

        assertThrows(AvvikLoggInnBrukernavn.class, () -> ValiderLoggInn.valideringBrukernavn(""));
        assertThrows(AvvikLoggInnPassord.class, () -> ValiderLoggInn.validerPassord(""));
        //for kort input:
        assertThrows(AvvikLoggInnBrukernavn.class, () -> ValiderLoggInn.valideringBrukernavn("a"));
        assertThrows(AvvikLoggInnPassord.class, () -> ValiderLoggInn.validerPassord("a"));
        //for lang input:
        assertThrows(AvvikLoggInnBrukernavn.class, () -> ValiderLoggInn.valideringBrukernavn("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        assertThrows(AvvikLoggInnPassord.class, () -> ValiderLoggInn.validerPassord("aklafkaøfkølaflkkfaølkaøklfkløkøakøfkøafkkjgkrjiegmkslngklsjgklsjglsjglksgjlsgjlsgjgs"));




      
    }
}