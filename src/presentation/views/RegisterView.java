package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * sera la clase para construir la vista del registro de usuarios de nuestro programa
 */
public class RegisterView extends JPanel {

    public static final String BTN_REGISTER = "BTN_REGISTER";
    public static final String BTN_BACK = "BTN_BACK";

    private JTextField userNameSpace = new JTextField();
    private JTextField emailSpace = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmationPasswordField = new JPasswordField();

    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    private CardLayout components;
    private MainView mainView;

    /**
     * es el constructor de nuestro registro de usuarios del programa
     */
    public RegisterView(){
        configureView();
    }

    /**
     * en este metodo creamos la vista de registro de usuarios
     */
    private void configureView(){

        //TODO:Hablar con el profe para ver porque no pinta bien y como arreglarlo.
        /*
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.jpg")));
            Image scaled = image.getScaledInstance(1080, 800, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0,1080,800);
            this.add(background);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());


        this.setLayout(new BorderLayout());
        this.add(fondo);


        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("Register");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        titlePanel.setOpaque(false);
        fondo.add(titlePanel, BorderLayout.NORTH);

        Box infoBox = Box.createVerticalBox();

        //JLabel para el nombre de usuario
        JLabel jlLogin = new JLabel("Username");
        jlLogin.setForeground(Color.WHITE);
        jlLogin.setAlignmentX(CENTER_ALIGNMENT);
        jlLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoBox.add(jlLogin);



        //JTextField para el nombre de usuario
        userNameSpace.setMaximumSize(new Dimension(500, userNameSpace.getPreferredSize().height));
        userNameSpace.setAlignmentX(CENTER_ALIGNMENT);
        infoBox.add(userNameSpace);

        //JLabel para el mail
        JLabel jlMail = new JLabel("Mali");
        jlMail.setForeground(Color.WHITE);
        jlMail.setAlignmentX(CENTER_ALIGNMENT);
        jlMail.setFont(new Font("Arial", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoBox.add(jlMail);

        //JTextField para el mail
        emailSpace.setMaximumSize(new Dimension(500, emailSpace.getPreferredSize().height));
        emailSpace.setAlignmentX(CENTER_ALIGNMENT);
        infoBox.add(emailSpace);

        //JLabel para el mail
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setForeground(Color.WHITE);
        jlPassword.setAlignmentX(CENTER_ALIGNMENT);
        jlPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoBox.add(jlPassword);

        //JTextField para el mail
        passwordField.setMaximumSize(new Dimension(500, passwordField.getPreferredSize().height));
        passwordField.setAlignmentX(CENTER_ALIGNMENT);
        infoBox.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoBox.add(passwordField);
        infoBox.add(Box.createVerticalStrut(10));

        //JLabel para el mail
        JLabel jlConPassword = new JLabel("Confirmation Password");
        jlConPassword.setForeground(Color.WHITE);
        jlConPassword.setAlignmentX(CENTER_ALIGNMENT);
        jlConPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoBox.add(jlConPassword);

        //JTextField para el mail
        confirmationPasswordField.setMaximumSize(new Dimension(500, confirmationPasswordField.getPreferredSize().height));
        confirmationPasswordField.setAlignmentX(CENTER_ALIGNMENT);
        infoBox.add(confirmationPasswordField);

        fondo.add(infoBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        loginButton.setActionCommand(BTN_BACK);
        buttonPanel.add(loginButton);
        registerButton.setActionCommand(BTN_REGISTER);
        buttonPanel.add(registerButton);
        buttonPanel.setOpaque(false);
        infoBox.add(buttonPanel, BorderLayout.SOUTH);




    }



    /**
     * Este metodo asignara el controlador de los botones de nuestra vista
     * @param listener es la clase que asigna los controladores a nuestros botones
     */
    public void registerController(ActionListener listener) {
        registerButton.addActionListener(listener);
        loginButton.addActionListener(listener);
    }

    /**
     * Este metodo muestra un popUp si la contraseña es muy corta
     */
    public void errorPasswordLength() {
        JOptionPane.showMessageDialog(this, "The minimum password lenght is 8 characters.");
    }

    /**
     * Este metodo muestra un popUp si la segunda contraseña no coincide con la primera
     */
    public void errorConfirmPassword() {
        JOptionPane.showMessageDialog(this, "Both passwords are not equal.");
    }

    /**
     * Este metodo muestra un popUp si la contraseña no tiene un formato correcto
     */
    public void errorUpperLowerNumber() {
        JOptionPane.showMessageDialog(this, "Password must have at least 1 upper case, 1 lower case and 1 number.");
    }

    /**
     * Este metodo muestra un popUp si la base de datos falla
     */
    public void errorConnection() {
        JOptionPane.showMessageDialog(this, "Something went wrong with the database connection");
    }

    /**
     * Este metodo muestra un popUp si el correo introducido ya esta asignado a algun usuario
     */
    public void errorEmailExist() {
        JOptionPane.showMessageDialog(this, "The email already exist.");
    }

    /**
     * Este metodo muestra un popUp si el nombre de usuario introducido ya esta asignado a algun usuario
     */
    public void errorUsernameExist() {
        JOptionPane.showMessageDialog(this, "The username already exist.");
    }

    /**
     * Este metodo muestra un popUp si el correo introducido no tiene un formato valido
     */
    public void errorFormat() {
        JOptionPane.showMessageDialog(this, "The mail has an invalid format.");
    }

    /**
     * Getter para recibir el texto del campo de texto del nombre del usuario
     *
     * @return nombre del usuario
     */
    public String getInputUsername() {
        return userNameSpace.getText();
    }

    /**
     * Getter para recibir el texto del campo de texto del email del usuario
     *
     * @return email del usuario
     */
    public String getInputEmail() {
        return emailSpace.getText();
    }

    /**
     * Getter para recibir el texto del campo de texto del la password del usuario
     *
     * @return password del usuario
     */
    public char[] getInputPassword() {
        return passwordField.getPassword();
    }

    /**
     * Getter para recibir el texto del campo de texto del confirm password del usuario
     *
     * @return password del usuario
     */
    public char[] getInputConfPassword() {
        return confirmationPasswordField.getPassword();
    }

    /**
     * Setter para asignar la main view
     *
     * @param mainView vista principal
     */
    public void setmainView(MainView mainView){
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
     * Metodo que resetea los campos de texto del register
     */
    public void resetInputInfo() {
        this.emailSpace.setText("");
        this.userNameSpace.setText("");
        this.confirmationPasswordField.setText("");
        this.passwordField.setText("");
    }

    /**
     *
     * @param Error
     */
    public void printRegisterErrors(String Error) {
        JOptionPane.showMessageDialog(null, Error, "Error Registre", JOptionPane.ERROR_MESSAGE);

    }
}

