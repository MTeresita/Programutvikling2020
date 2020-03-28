package org.openjfx.Models;

public class BrukerRegister {

    public String brukernavn, passord;

    public BrukerRegister(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public String toString(){
        return getBrukernavn()+";"+ getPassord()+";";
    }
}
