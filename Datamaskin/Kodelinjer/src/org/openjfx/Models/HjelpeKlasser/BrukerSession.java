package org.openjfx.Models.HjelpeKlasser;

public class BrukerSession {
    //lager en slags "session" for innlogget bruker
    private static String brukernavn;

    public static void setBrukerSession(String brukernavn){
        BrukerSession.brukernavn = brukernavn;
    }
    public static String getBrukerSession(){
        return brukernavn;
    }

}
