package business.entities;

public class User {
    // Attributes
    private final String name;
    private final String mail;
    private final String password;
    private final String confirmedPassword;


    public User (String name, String mail, String password, String confirmedPassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = password;

    }

    public String getName() {
        return name;
    }
    public String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmedPassword() {
        return confirmedPassword;
    }
}
