package presentation.controllers;

import business.UserManager;
import presentation.views.LoginView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase es con la que controlamos la vista del login
 */
public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final MainView mainView;


    private final UserManager userManager;


    /**
     * Constructor del controller
     * @param loginView La vista que controlamos
     * @param mainView La vista general
     * @param userManager el Manager el usuario
     */
    public LoginController(LoginView loginView, MainView mainView, UserManager userManager) {
        this.loginView = loginView;
        this.mainView = mainView;
        this.userManager = userManager;
    }

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case LoginView.BTN_LOG:
                if (userManager.loginUser(loginView.getInputUsernameEmail(),String.valueOf(loginView.getInputPassword()))){
                    //Guardem el nom del usuari
                    String userName = userManager.getUsername(loginView.getInputUsernameEmail());
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
