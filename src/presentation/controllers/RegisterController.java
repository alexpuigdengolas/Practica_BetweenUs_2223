package presentation.controllers;

import business.ErrorMessage;
import business.UserManager;
import business.entities.User;
import presentation.views.MainView;
import presentation.views.RegisterView;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class RegisterController implements ActionListener {

    private final RegisterView registerView;
    private final MainView mainView;
    private final CardLayout viewComponents;

    private  UserManager userManager;

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
            case RegisterView.BTN_REGISTER:
                User user = new User(registerView.getInputUsername(),registerView.getInputEmail(), String.valueOf(registerView.getInputPassword()), String.valueOf(registerView.getInputConfPassword()));
                try {
                    caseRegister(user);
                    mainView.showLogin();
                } catch (ErrorMessage ex) {
                    System.out.println(ex.getMessage());;
                }
                break;

            case RegisterView.BTN_BACK:
                registerView.resetInputInfo();
                registerView.setmainView(mainView);
                registerView.setComponents(viewComponents);
                mainView.showLogin();
                break;
        }
    }
    public void caseRegister(User user) throws ErrorMessage {
        userManager.checkRegister(user);

        try {
            userManager.checkRegister(user);
            userManager.registerUser(user);
        }catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }

/*
        if (checked == 0) {
            userManager.registerUser(user);
            mainView.showStart();
            //Ir al caso de que se ha registrado bien

        } else {
            //No se ha podido hacer cada uno marca el error
            switch (checked) {
                case 1 -> // nombre repetido
                        System.out.println("El nom del usuari ja existeix.");
                case 2 -> // Las contras no son iguales
                        System.out.println("Les contrasenyes no son iguals");
                case 3 -> { // error en el formato de la contra
                    String finalError = userManager.checkPasswordFormat(user);
                    System.out.println("El final error es:" + finalError);
                }
                case 4 -> // error mail format
                        System.out.println("El mail no te el format correcte");
                case 5 -> //Correo ya existe
                        System.out.println("El mail ya exiteix");
            }
        }*/
    }
}
