package presentation.controllers;

import business.UserManager;
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

    private UserManager userManager;

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

                    //mostrem el error de no fer ho be
                    System.out.println(("no has posgut fer login"));
                }

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
