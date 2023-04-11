package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {

    public static final String BTN_LOG = "BTN_LOG";
    public static final String BTN_REG = "BTN_REG";

    private JTextField nameSpace = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    private CardLayout components;
    private MainView mainView;

    public LoginView() {
        configureLoginView();
    }

    private void configureLoginView() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de Login
        JLabel jlLogin = new JLabel("Username or Email");
        jlLogin.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlLogin.setFont(new Font("Serif", Font.PLAIN, 40));
        //TODO: Cambiar el color de la vista
        infoPanel.add(jlLogin);

        //TextField de Login
        infoPanel.add(nameSpace);

        //Label de Contraseña
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlPassword.setFont(new Font("Serif", Font.PLAIN, 40));
        infoPanel.add(jlPassword);

        //TextField de Contraseña
        infoPanel.add(passwordField);
        add(infoPanel);

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        loginButton.setActionCommand(BTN_LOG);
        registerButton.setActionCommand(BTN_REG);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        infoPanel.add(buttonPanel);
    }

    public void loginController(ActionListener listener) {
        loginButton.addActionListener(listener);
        registerButton.addActionListener(listener);
    }

    /**
     * Este metodod nos devolvera el correo electronico / nombre de usuario introducido en nuestra vista.
     * @return un string que contiene lo que el usuario haya introducido en ese campo de texto
     */
    public String getInputUsernameEmail() {
        return nameSpace.getText();
    }

    /**
     * Este metodod nos devolvera la contraseña introducida en nuestra vista.
     * @return devolvera un array de caracteres que forman la contraseña de nuestro usuario
     */
    public char[] getInputPassword() {
        return passwordField.getPassword();
    }

    /**
     * Setter para asignar la main view
     *
     * @param mainView vista principal
     */
    public void setMainView(MainView mainView){
        this.mainView = mainView;
    }

    /**
     * Setter para asignar los componentes del card layout
     *
     * @param viewComponents card layout
     */
    public void setComponents(CardLayout viewComponents) {
        this.components = viewComponents;
    }

    /**
     * Metodo que resetea los campos de texto del login
     */
    public void resetInputInfo() {
        this.passwordField.setText("");
        this.nameSpace.setText("");
    }
}
