package presentation.controllers;

import business.UserManager;
import presentation.views.LoginView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class will be used as the controller to de login View
 */
public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final MainView mainView;
    private final CardLayout cardLayout;

    private final UserManager userManager;


    public LoginController(LoginView loginView, MainView mainView, CardLayout cardLayout, UserManager userManager) {
        this.loginView = loginView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case LoginView.BTN_LOG:
                if (userManager.loginUser(loginView.getInputUsernameEmail(),String.valueOf(loginView.getInputPassword()))){
                    //Guardem el nom del usuari
                    String userName = userManager.getUsername(loginView.getInputUsernameEmail());
                    System.out.println("l'usuari "+userName+ " ha fet log in correcre");
                    //Anem a la vista que tocaria
                    mainView.showStart();
                } else {

                    loginView.printLoginErrors("El mail o la contrasenya no estan be");
                }

                break;
            case LoginView.BTN_REG:
                loginView.resetInputInfo();
                mainView.showRegister();
                break;
        }
    }
}
