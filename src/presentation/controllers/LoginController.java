package presentation.controllers;

import business.entities.User;
import presentation.views.LoginView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    private LoginView loginView;
    private MainView mainView;
    private CardLayout cardLayout;

    //TODO: Añadir user manager para gestión con la Base de datos
    public LoginController(LoginView loginView, MainView mainView, CardLayout cardLayout) {
        this.loginView = loginView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LoginView.BTN_LOG:
                String userNameEmail;
                String password;
                userNameEmail = loginView.getInputUsernameEmail();
                password = String.valueOf(loginView.getInputPassword());
                User user = new User(userNameEmail, userNameEmail, password);

                //TODO: Acciones con la base de datos

                break;
            case LoginView.BTN_REG:
                loginView.resetInputInfo();
                loginView.setMainView(mainView);
                loginView.setComponents(cardLayout);
                mainView.showRegister();
                break;
        }
    }
}
