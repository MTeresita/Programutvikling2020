package org.openjfx.Models.Avvik;

import java.util.regex.Pattern;

public class ValidationHelper {

    private StringBuilder invalidValues = new StringBuilder();

    public String getInvalidInput(String username, String password)  {

        if(!checkIfEmptyFields(username, password)){
            checkName(username);
            try {
                checkPassword(password);
            } catch (InvalidPasswordException e) {
                e.printStackTrace();
            }
        }
        return invalidValues.toString();
    }


    private boolean checkUserNameFormat(String username) throws InvalidUsernameFormatException {
        if((!Pattern.matches("[^[a-z0-9_-]{4,15}$]",username)) &&
                (!username.equals("N/A") && !username.isEmpty())){
            throw new InvalidUsernameFormatException("Brukernavnet må ha minst 3 tegn, og maks 15\n");
        }
        return true;
    }

    private boolean checkPasswordFormat(String password) throws InvalidPasswordException{
        if((!Pattern.matches("[^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$]",password)) &&
                (!password.equals("N/A") && !password.isEmpty())){
            throw new InvalidPasswordException("Minimum eight characters, at least one letter and one number");
        }
        return true;

    }

    private boolean checkPassword(String password) throws InvalidPasswordException{
        try{
            if(checkPasswordFormat(password)){
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

    private boolean checkIfEmptyFields(String username, String password) throws EmptyFieldsException{

        StringBuilder sb = new StringBuilder();

        if(username.isEmpty()){
            sb.append("Brukernavnet kan ikke være tomt");
        }

        if(password.isEmpty()){
            sb.append("Passordet kan ikke være tomt");
        }

        if(!sb.toString().isBlank()){
            throw new EmptyFieldsException(sb.toString());
        }

        return true;
    }


}
