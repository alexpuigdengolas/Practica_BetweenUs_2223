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

        //aixo s ha de adaptar al codi on toqui. JA registra
        userDAO = new UserSQLDAO();

        //es crea un user a lo guarro y es registra
        User user = new User("mario","mario@mario.com","mario12345","mario12345");
        userDAO.registerUser(user);
        System.out.println("Es registra el usuari: "+ user.getName());

        //Comrpova login false no true si
        if(userDAO.checkLoginUser(user.getName(),user.getPassword())){
            System.out.println("el usuari: "+ user.getName()+ " ha fet log in correctament");
        }


        //Borra un usuari de la base de dades
        if (userDAO.userNameExists(user.getName())) {
            userDAO.deleteUser(user.getName());
            System.out.println("S ha borrat el usuari: "+ user.getName());

        }*/
    }

}
