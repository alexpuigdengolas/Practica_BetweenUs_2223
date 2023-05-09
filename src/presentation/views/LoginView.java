package presentation.views;

import presentation.views.custom.BackGroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");
        fondo.setLayout(new BorderLayout());

        //this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());
        this.add(fondo);


        JPanel titlePanel = new JPanel();

        JLabel jlTitle = new JLabel("Login");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        titlePanel.setOpaque(false);
        fondo.add(titlePanel, BorderLayout.NORTH);

        JPanel voidPanel = new JPanel();
        voidPanel.setOpaque(false);
        voidPanel.setPreferredSize(new Dimension(50, 50));
        fondo.add(voidPanel, BorderLayout.EAST);
        fondo.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        //Label de Login
        JLabel jlLogin = new JLabel("Username or Email");
        jlLogin.setForeground(Color.WHITE);
        jlLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(jlLogin);
        infoPanel.add(Box.createVerticalGlue());

        //TextField de Login
        nameSpace.setMaximumSize(new Dimension(500, nameSpace.getPreferredSize().height));
        nameSpace.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(nameSpace);
        infoPanel.add(Box.createVerticalGlue());

        //Label de Contraseña
        JLabel jlPassword = new JLabel("Password");
        jlPassword.setForeground(Color.WHITE);
        jlPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(jlPassword);
        infoPanel.add(Box.createVerticalGlue());

        //TextField de Contraseña
        passwordField.setMaximumSize(new Dimension(500, passwordField.getPreferredSize().height));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(passwordField);
        infoPanel.add(Box.createVerticalGlue());

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        loginButton.setActionCommand(BTN_LOG);
        registerButton.setActionCommand(BTN_REG);
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(buttonPanel);
        infoPanel.add(Box.createVerticalGlue());

        fondo.add(infoPanel);
    }



    public void loginController(ActionListener listener) {
        loginButton.addActionListener(listener);
        registerButton.addActionListener(listener);
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

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

    public void printLoginErrors(String Error) {
        JOptionPane.showMessageDialog(null, Error, "Error Login", JOptionPane.ERROR_MESSAGE);

    }





}
