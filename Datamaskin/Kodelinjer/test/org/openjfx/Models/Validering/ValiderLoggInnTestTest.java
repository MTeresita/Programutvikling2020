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

        assertTrue(ValideringLoggInn.valideringBrukernavn("camillacamilla"));
        assertTrue(ValideringLoggInn.valideringBrukernavn("camillacamilla"));
        //store og små bokstaver:
        assertTrue(ValideringLoggInn.valideringBrukernavn("HalloHallo"));
        assertTrue(ValideringLoggInn.validerPassord("HalloHallo"));
        //bare store bokstaver:
        assertTrue(ValideringLoggInn.valideringBrukernavn("HALLOEN"));
        assertTrue(ValideringLoggInn.validerPassord("HALLOEN"));



    }
    @Test
    public void testLoggInnFeil() throws AvvikLoggInnBrukernavn, AvvikLoggInnPassord {
        //tomme felt:

        assertThrows(AvvikLoggInnBrukernavn.class, () -> ValideringLoggInn.valideringBrukernavn(""));
        assertThrows(AvvikLoggInnPassord.class, () -> ValideringLoggInn.validerPassord(""));
        //for kort input:
        assertThrows(AvvikLoggInnBrukernavn.class, () -> ValideringLoggInn.valideringBrukernavn("a"));
        assertThrows(AvvikLoggInnPassord.class, () -> ValideringLoggInn.validerPassord("a"));
        //for lang input:
        assertThrows(AvvikLoggInnBrukernavn.class, () ->
                ValideringLoggInn.valideringBrukernavn(
                        "aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));

        assertThrows(AvvikLoggInnPassord.class, () ->
                ValideringLoggInn.validerPassord(
                        "aklafkaøfkølaflkkfaølkaøklfkløkøakøfkøafkkjgkrjiegmkslngklsjgklsjglsjglksgjlsgjlsgjgs"));




      
    }
}