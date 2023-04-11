package business;

import business.entities.User;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

public class UserManager {

    private final UserDAO userDAO;

    public UserManager(){
        userDAO = new UserSQLDAO();
    }

    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    public int checkRegister(User user){
        if (userDAO.userNameExists(user.getName())) {
            return 1;
        } else if (unequalPasswords(user)) {
            return 2;
        } else if(!checkPasswordFormat(user).equals("")) {
            return 3;
        } else if (!checkMailFormat(user.getMail())) {
            return 4;
        } else if (userDAO.userMailExists(user.getMail())){
            return 5;
        } else {
            return 0;
        }
    }

    public boolean checkMailFormat(String mail) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]{3,}+.+[a-zA-Z0-9]{2,}+$";
        return mail.matches(emailRegex);
    }


    public String checkPasswordFormat(User user) {
        char c;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        boolean error = false;

        String[] errorMsg = new String[4];
        int index = 0;

        for (int i = 0; i < user.getPassword().length(); i++){
            c = user.getPassword().charAt(i);
            if ((Character.isDigit(c))) {      //Comprovem si la contrasenya té algun número
                numberFlag = true;
            } else if (Character.isUpperCase(c)) {     //Comprovem si la contrasenya té alguna majúscula
                capitalFlag = true;
            } else if (Character.isLowerCase(c)) {     //Comprovem si la contrasenya té alguna minúscula

                lowerCaseFlag = true;
            }
        }

        if (user.getPassword().length() < 8){     //  Comprovem que la contrasenya contingui com a mínim 8 caràcters
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir 8 caràcters com a mínim\n";
            index++;
        }
        if (!numberFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir algún valor numèric\n";
            index++;
        }
        if (!capitalFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna majúscula\n";
            index++;
        }
        if (!lowerCaseFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna minúscula\n";
        }

        String finalError = "";
        if (error) {
            for (String s: errorMsg) {     //Printem tots els errors trobats
                if (s != null) {
                    finalError = finalError.concat(s);
                }
            }
        }
        return finalError;
    }

    public boolean unequalPasswords(User user){
        return !user.getConfirmedPassword().equals(user.getPassword());
    }

    public boolean loginUser(String userNameMail, String password) {
        return userDAO.checkLoginUser(userNameMail,password);
    }

    public String getUsername(String loginName) {
        return userDAO.getUsername(loginName);
    }

}
