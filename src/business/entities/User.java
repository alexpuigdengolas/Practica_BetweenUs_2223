package business.entities;

/**
 * Esta clase representara la clase de usuario
 */
public class User {
    // Attributes
    private final String name;
    private final String mail;
    private final String password;
    private final String confirmedPassword;

    /**
     * Este es el constructor de la clase
     * @param name Nombre del usuario
     * @param mail correo del usuario
     * @param password constraseña del usuario
     * @param confirmedPassword contraseña de confirmacion
     */
    public User (String name, String mail, String password, String confirmedPassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    /**
     * Este es el constructor de la clase
     * @param name Nombre del usuario
     * @param mail correo del usuario
     * @param password constraseña del usuario
     */
    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = password;

    }

    /**
     * Getter del nombre
     * @return El nombre del usuario
     */
    public String getName() {
        return name;
    }

    /**
     * Getter del correo del usuario
     * @return El correo del usuario
     */
    public String getMail() {
        return mail;
    }

    /**
     * Getter de la contraseña asociada al usuario
     * @return la contrseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter de la contraseña de confirmacion del ususario
     * @return la contraseña de confirmacion
     */
    public String getConfirmedPassword() {
        return confirmedPassword;
    }
}
