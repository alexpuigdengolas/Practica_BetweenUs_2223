package presentation.views;

import javax.swing.*;

public class LoginView extends JPanel {

    private JTextField nameSpace = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    public LoginView(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Username or Email"));
        infoPanel.add(nameSpace);
        infoPanel.add(new JLabel("Password"));
        infoPanel.add(passwordField);
        add(infoPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        infoPanel.add(buttonPanel);


    }
}
