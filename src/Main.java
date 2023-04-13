import persistance.Conn.*;
import business.entities.User;
import presentation.controllers.LoginController;
import presentation.controllers.RegisterController;
import presentation.controllers.StartController;
import presentation.views.LoginView;
import presentation.views.MainView;
import presentation.views.RegisterView;
import presentation.views.StartView;

import java.awt.*;

public class Main {
    private static Data data;
    private static DBConnector conn;
    private static UserDAO userDAO;


    public static void main(String[] args) {

        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        StartView startView = new StartView();

        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView, startView);


        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout);
        StartController startController = new StartController(startView, mainView, cardLayout);

        registerView.registerController(registerController);
        loginView.loginController(loginController);
        startView.startController(startController);

        mainView.start();
        /*


        //Borra un usuari de la base de dades (Cuando se implemente la vista de borrar lo quito asi tengo las lineas hechas ya)
        if (userDAO.userNameExists(user.getName())) {
            userDAO.deleteUser(user.getName());
            System.out.println("S ha borrat el usuari: "+ user.getName());

        }*/
    }

}
