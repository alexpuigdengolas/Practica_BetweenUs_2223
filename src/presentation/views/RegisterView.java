package presentation.views;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JPanel {

    private JTextField userNameSpace = new JTextField();
    private JTextField emailSpace = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmationPasswordField = new JPasswordField();

    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    public RegisterView(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Username"));
        infoPanel.add(userNameSpace);
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(emailSpace);
        infoPanel.add(new JLabel("Password"));
        infoPanel.add(passwordField);
        infoPanel.add(new JLabel("Confirmation Password"));
        infoPanel.add(confirmationPasswordField);
        add(infoPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        infoPanel.add(buttonPanel);

    }
}
