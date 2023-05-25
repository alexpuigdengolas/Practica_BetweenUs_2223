package persistance.Conn;

import business.entities.User;

/**
 * Esta interficie implementara todos los metodos que necesitamos para poder acceder a la base de datos del usuario
 */
public interface UserDAO {

    /**
     * Este metodo nos permite registrar nuevos usuarios en la base de datos
     * @param user el usuario a registrar
     */
    void registerUser(User user);

    /**
     * Este metodo nos permite comprobar que los datos de login de un usuario son correctos
     * @param userNameMail el nombre o correo de login
     * @param password la contraseña
     * @return si se ha podido hacer login o no
     */
    boolean checkLoginUser(String userNameMail, String password);

    /**
     * Metodo para poder eliminar un usuario
     * @param username el nombre del usuario que queremos eliminar
     */
    void deleteUser(String username);

    /**
     * Metodo para comprobar si un nombre de usuario existe
     * @param userName el nombre del usuario
     * @return si el nombre existe o no
     */
    boolean userNameExists(String userName);

    /**
     * Metodo para comprobar si el correo de usuario existe
     * @param userMail el correo
     * @return si el correo existe o no
     */
    boolean userMailExists(String userMail);

    /**
     * Metodo para conseguir el nombre de usuario
     * @param loginName nombre de usuario o correo del usuario
     * @return el nombre del usuario
     */
    String getUsername(String loginName);
    //#nuevo

    /**
     * Metodo para conseguir el numero de victorias de un usuario
     * @param user el nombre del usuario
     * @return el numero de victorias del usuario
     */
    int getNumVictories(String user);

    //#nuevo
    /**
     * Metodo para settear el numero de partidas
     * @param user nombre del usuario
     * @param num numero de victorias
     */
    void setNumGames(String user,int num);

    //#nuevo
    /**
     * Este metodo nos permitira conseguir el numero de partidas
     * @param user nombre del usuario
     * @return el numero de partidas
     */
    int getNumGames(String user);

    //#nuevo

    /**
     * Metodo para settear el numero de partidas ganadas de un usuario
     * @param user el nombre del usuario
     * @param num el numero de partidas ganadas
     */
    void setNumWins(String user, int num);



}
