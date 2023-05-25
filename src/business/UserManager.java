package business;

import business.entities.User;
import persistance.Conn.GameDAO;
import persistance.Conn.GameSQLDAO;
import persistance.Conn.UserDAO;
import persistance.Conn.UserSQLDAO;

/**
 * Clase que ejerce como gestor de usuarios
 */
public class UserManager {

    private final UserDAO userDAO;
    private final GameDAO gameDAO;

    private String user;

    /**
     * El constructor de la clase
     */
    public UserManager(){
        userDAO = new UserSQLDAO();
        this.gameDAO = new GameSQLDAO();
    }

    /**
     * Metodo para registrar al usuario
     * @param user el usuario a registrar
     */
    //Registra el usuario en la BBDD
    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    /**
     * Metodo para comprobar si se puede registrar
     * @param user el usuario
     * @throws ErrorMessage el menaje de error
     */
    //Comprueba las diferentes condiciones de registro y envia una excepcion en caso de no complir las que toquen
    public void checkRegister(User user) throws ErrorMessage{
        if (userDAO.userNameExists(user.getName())) {
             throw new ErrorMessage("El nom del usuari ja existeix.");
        } else if (unequalPasswords(user)) {
            throw new ErrorMessage("Les contrasenyes no son iguals");
        } else if(!checkPasswordFormat(user).equals("")) {
            String finalError = checkPasswordFormat(user);
            throw new ErrorMessage(finalError);
        } else if (!checkMailFormat(user.getMail())) {
            throw new ErrorMessage("El mail no te el format correcte");
        } else if (userDAO.userMailExists(user.getMail())){
            throw new ErrorMessage("El mail ya existeix");
        }
    }

    /**
     * Metodo para comprobar el mail
     * @param mail el mail
     * @return un booleano que indica si es correcto
     */
    //Comprueba el formato del mail
    public boolean checkMailFormat(String mail) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]{3,}+.+[a-zA-Z0-9]{2,}+$";
        return mail.matches(emailRegex);
    }

    /**
     * Metodo para comprobar el formato de la contraseña
     * @param user el usuario
     * @return El error que debe mostrarse
     */
    //Comprueba el formato de la contrasenya
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

    /**
     * Metodo para comprobar que las contraseñas sean identicas
     * @param user usuario
     * @return booleano para indicar si esto es asi
     */
    public boolean unequalPasswords(User user){
        return !user.getConfirmedPassword().equals(user.getPassword());
    }

    /**
     * Metodo para logear al usuario
     * @param userNameMail el nombre o correo del usuario
     * @param password la contraseña del usuario
     * @return boolean que indica si la informacion es correcta
     */
    public boolean loginUser(String userNameMail, String password) {

        return userDAO.checkLoginUser(userNameMail,password);
    }

    /**
     * Getter del nombre de usuario logeado
     * @param loginName el nombre o correo
     * @return el nombre del usuario
     */
    public String getUsername(String loginName) {
        user = userDAO.getUsername(loginName);
        return userDAO.getUsername(loginName);
    }

    /**
     * Getter del usuario logeado
     * @return nombre del usuario
     */
    public String getUser(){
        return user;
    }

    /**
     * Metodo para resetear el nombre
     */
    public void resetName(){
        user = null;

    }

    /**
     * Metodo para eliminar las partidas del usuario
     */
    public void deleteGames(){
     gameDAO.deleteGames(user);

    }

    /**
     * metodo para eliminar el usuario
     */
    public void deleteUser() {
        if (userDAO.userNameExists(user)) {
            userDAO.deleteUser(user);
        }
    }

    /**
     * Getter el numero de partidas de un usuario especifico
     * @param user
     * @return
     */
    public int getNumGames(String user){
        return  userDAO.getNumGames(user);

    }

    /**
     * Getter de el numero de partidas ganadas del usuario
     * @param user el usuario
     * @return el numero de partidas ganadas
     */
    public int getnumWins(String user){
        return userDAO.getNumVictories(user);
    }

    //#nuevo
    /**
     * Metodo para añadir una partida finalizada a la base de datos
     * @param win boolean que indica si se vencio o no
     */
    public void gameFinish(Boolean win){
        if(userDAO.userNameExists(user)){
            int games = getNumGames(user);
            userDAO.setNumGames(user,games+1);
            if(win){
               int wins = getnumWins(user);
               userDAO.setNumWins(user,wins);
            }
        }
    }
}
