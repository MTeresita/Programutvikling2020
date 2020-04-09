package org.openjfx.Models.Validering;

import org.junit.jupiter.api.Test;
import org.openjfx.Models.Avvik.AvvikLoggInn;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValiderLoggInnTestTest {
    @Test
    public void testLoggInnSann() throws AvvikLoggInn {
        assertTrue(ValiderLoggInn.valideringBrukernavn("camillaharic"));
        assertTrue(ValiderLoggInn.validerPassord("HalloHallo"));

    }
    @Test
    public void testLoggInnFeil() throws AvvikLoggInn{
        //tomme felt:
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn(""));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord(""));
        //for kort input:
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn("a"));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord("a"));
        //for lang input:
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.valideringBrukernavn("aaaaaaaaaaaaaaakkakflakflafkajfkalfjalfjlaffjlalfjlalfjlafjlajfjlalfjjlaljf"));
        assertThrows(AvvikLoggInn.class, () -> ValiderLoggInn.validerPassord("aklafkaøfkølaflkkfaølkaøklfkløkøakøfkøafkkjgkrjiegmkslngklsjgklsjglsjglksgjlsgjlsgjgs"));
    }
}