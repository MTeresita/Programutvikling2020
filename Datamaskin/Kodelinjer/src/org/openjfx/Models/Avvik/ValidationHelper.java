package org.openjfx.Models.Avvik;

import java.util.regex.Pattern;

public class ValidationHelper {

    private StringBuilder invalidValues = new StringBuilder();

    public String getInvalidInput(String username, String password, String password1) throws InvalidPasswordException {

        if(!checkFields(username, password, password1)){
            checkName(username);
            checkPassword(password, password1);

        }
        return invalidValues.toString();
    }


    private boolean checkUserNameFormat(String username) throws InvalidUsernameFormatException {
        if((!Pattern.matches("^[aA-zZ]\\w{5,15}$",username)) &&
                (!username.equals("N/A") && !username.isEmpty())){
            throw new InvalidUsernameFormatException("Brukernavnet må ha minst 5 tegn, og maks 15\n");
        }
        return true;
    }

    //TODO: denne fungerer ikke riktig
    private boolean checkPasswordFormat(String password, String password1) throws InvalidPasswordException{
        if(!Pattern.matches("^(?=.*[0-9]).{8,20}$",password) && !password.equals("N/A") && !password.isEmpty() &&
                (!Pattern.matches("^(?=.*[0-9]).{8,20}$",password1))
                && (!password1.equals("N/A") && !password1.isEmpty())){
            throw new InvalidPasswordException("Minimum eight characters, at least one letter and one number\n");
        }
        return true;

    }

    private boolean checkPassword(String password, String password1){
        try{
            if(checkPasswordFormat(password,password1)){
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

    private boolean checkIfEmptyFields(String username, String password, String password1) throws EmptyFieldsException{

        StringBuilder sb = new StringBuilder();

        if(username.isEmpty()){
            sb.append("Brukernavnet kan ikke være tomt\n");
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

    public boolean checkFields(String username, String pass, String pass1){
        try {
            if (checkIfEmptyFields(username, pass, pass1)) {
                return true;
            }
        } catch (EmptyFieldsException ef) {
            invalidValues.append(ef.getMessage());
        }
        return false;
    }


}
