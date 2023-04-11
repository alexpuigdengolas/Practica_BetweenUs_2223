import persistance.Conn.*;
import business.entities.User;
import presentation.controllers.LoginController;
import presentation.controllers.RegisterController;
import presentation.views.LoginView;
import presentation.views.MainView;
import presentation.views.RegisterView;

import java.awt.*;

public class Main {
    private static Data data;
    private static DBConnector conn;
    private static UserDAO userDAO;


    public static void main(String[] args) {

        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();

        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView);

        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout);

        registerView.registerController(registerController);
        loginView.loginController(loginController);


        mainView.start();
        /*


        //Borra un usuari de la base de dades (Cuando se implemente la vista de borrar lo quito asi tengo las lineas hechas ya)
        if (userDAO.userNameExists(user.getName())) {
            userDAO.deleteUser(user.getName());
            System.out.println("S ha borrat el usuari: "+ user.getName());

        }*/
    }

}
