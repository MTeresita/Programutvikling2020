package org.openjfx.Models.Avvik;

import java.util.regex.Pattern;

public class ValideringBruker {

    private StringBuilder invalidValues = new StringBuilder();

    public String getRegistrationInvalidInput(String username, String password, String password1) {

        if(!checkRegistrationFields(username, password, password1)){
            checkName(username);
            checkDoublePassword(password, password1);
        }
        return invalidValues.toString();
    }

    public String getLogInInvalidInputs(String brukernavn, String passord){

        if(!checkLogInFields(brukernavn, passord)){
            checkName(brukernavn);
            checkSinglePassword(passord);
        }
        return invalidValues.toString();

    }


    private boolean checkUserNameFormat(String username) throws InvalidUsernameFormatException {
        if((!Pattern.matches("^[aA-zZ]\\w{5,15}$",username)) && !username.isEmpty()){
            throw new InvalidUsernameFormatException("Brukernavnet må ha minst 5 tegn, og maks 15\n");
        }
        return true;
    }

    private boolean checkSinglePasswordFormat(String passord) throws InvalidPasswordException{
        if((!Pattern.matches("^[A-ZÆØÅa-zæøå]{5,50}$", passord)) && !passord.isEmpty()){
            throw new InvalidPasswordException("Passord må være minst 5 bokstaver langt\n");
        }
        return true;
    }

    private boolean checkSinglePassword(String passord){
        try {
            if(checkSinglePasswordFormat(passord)){
                return true;
            }
        } catch (InvalidPasswordException e) {
            invalidValues.append(e.getMessage());
        }
        return false;
    }

    private boolean checkDoublePasswordFormat(String passord, String passord1) throws InvalidPasswordException{
        if((!Pattern.matches("^[A-ZÆØÅa-zæøå]{5,50}$", passord)  && !passord.isEmpty()) ||
                (!Pattern.matches("^[A-ZÆØÅa-zæøå]{5,50}$", passord1) && !passord1.isEmpty())){
            throw new InvalidPasswordException("Passordene må være minst 5 bokstaver langt.\n");
        }
            return true;
    }

    private boolean checkDoublePassword(String password, String password1){
        try{
            if(checkDoublePasswordFormat(password,password1)){
                return true;
            }
        }
        catch(InvalidPasswordException ine){
            invalidValues.append(ine.getMessage());
        }
        return false;
    }

    private boolean checkName(String username){
        try{
            if(checkUserNameFormat(username)){
                return true;
            }
        }
        catch(InvalidUsernameFormatException ine){
            invalidValues.append(ine.getMessage());
        }
        return false;
    }

    private boolean checkIfRegistrationEmptyFields(String username, String password, String password1) throws EmptyFieldsException{

        StringBuilder sb = new StringBuilder();

        if(username.isEmpty()){
            sb.append("Brukernavn feltet kan ikke være tomt\n");
        }

        if(password.isEmpty()){
            sb.append("Passord feltet kan ikke være tomt\n");
        }
        if(password1.isEmpty()){
            sb.append("Passord feltet kan ikke være tomt\n");
        }
        if(!password.equals(password1)){
            sb.append("Passordene er ikke like\n");
        }


        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }

        return true;
    }

    public boolean checkRegistrationFields(String username, String pass, String pass1){
        try {
            if (checkIfRegistrationEmptyFields(username, pass, pass1)) {
                return true;
            }
        } catch (EmptyFieldsException ef) {
            invalidValues.append(ef.getMessage());
        }
        return false;
    }

    private boolean checkifLogInFieldsEmpty(String brukernavn, String passord){
        StringBuilder sb = new StringBuilder();

        if(brukernavn.isEmpty()){
            sb.append("Brukernavn feltet kan ikke være tomt\n");
        }

        if(passord.isEmpty()){
            sb.append("Passord feltet kan ikke være tomt\n");
        }

        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }

        return true;
    }

    public boolean checkLogInFields(String brukernavn, String passord){
        try {
            if (checkifLogInFieldsEmpty(brukernavn, passord)) {
                return true;
            }
        } catch (EmptyFieldsException ef) {
            invalidValues.append(ef.getMessage());
        }
        return false;
    }


}
