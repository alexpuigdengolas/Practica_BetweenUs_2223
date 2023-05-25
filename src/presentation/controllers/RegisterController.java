package presentation.controllers;

import business.ErrorMessage;
import business.UserManager;
import business.entities.User;
import presentation.views.MainView;
import presentation.views.RegisterView;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase nos permite controlar la vista de registro
 */
public class RegisterController implements ActionListener {

    private final RegisterView registerView;
    private final MainView mainView;
    private final CardLayout viewComponents;

    private final UserManager userManager;

    /**
     * Este metodo es el constructor de nuestra clase
     * @param registerView esta es la vista del registro
     * @param mainView esta es la vista principal de nuestro programa
     * @param viewComponents ese sera el Layout de nuestra vista principal
     * @param userManager este es el gestor de usuarios
     */
    public RegisterController(RegisterView registerView, MainView mainView, CardLayout viewComponents,UserManager userManager) {
        this.registerView = registerView;
        this.mainView = mainView;
        this.viewComponents = viewComponents;
        this.userManager = userManager;
    }

    /**
     * Este metodo recibe los strings de nuestro inicio de sesion y su contraseña y comprobamos que su estado sea le correcto
     * @param e es el objecto asignado al boton selecionado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RegisterView.BTN_REGISTER -> {
                User user = new User(registerView.getInputUsername(), registerView.getInputEmail(), String.valueOf(registerView.getInputPassword()), String.valueOf(registerView.getInputConfPassword()));
                try {
                    //Comprueba que el registro cumpla condiciones y registra si asi es
                    userManager.checkRegister(user);
                    userManager.registerUser(user);
                    mainView.showLogin();
                } catch (ErrorMessage ex) {
                    registerView.printRegisterErrors(ex.getMessage());
                    mainView.showRegister();

                }
            }
            case RegisterView.BTN_BACK -> {
                registerView.resetInputInfo();
                mainView.showLogin();
            }
        }
    }

    /**
     * Este metodo nos da la posibilidad de ver si un usuario ha podido ser registrado
     * @param user el usuario a registrar
     * @throws ErrorMessage el mensaje de error que nos da este registro
     */
    public void caseRegister(User user) throws ErrorMessage {
        userManager.checkRegister(user);

        try {
            userManager.checkRegister(user);
            userManager.registerUser(user);
        }catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }

    }
}
