import persistance.Conn.*;
import presentation.controllers.*;
import presentation.views.*;

import java.awt.*;

public class Main {
    private static Data data;
    private static DBConnector conn;
    private static UserDAO userDAO;


    public static void main(String[] args) {

        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        StartView startView = new StartView();
        NewGameView nGView = new NewGameView();
        StatisticsView statisticsView = new StatisticsView();
        DeleteView deleteView = new DeleteView();
        ConfiguredView configuredView = new ConfiguredView();
        ChargeView chargeView = new ChargeView();
        SettingsView settingsView = new SettingsView();



        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView, startView, nGView, statisticsView, deleteView, configuredView, chargeView, settingsView);


        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout);
        StartController startController = new StartController(startView, mainView, cardLayout);
        NGController NGcontroller = new NGController(nGView, mainView, cardLayout);
        SettingsController settingsController = new SettingsController(settingsView, mainView, cardLayout);

        registerView.registerController(registerController);
        loginView.loginController(loginController);
        startView.startController(startController);
        nGView.NGController(NGcontroller);
        settingsView.settingsController(settingsController);

        mainView.start();
        /*


        //Borra un usuari de la base de dades (Cuando se implemente la vista de borrar lo quito asi tengo las lineas hechas ya)
        if (userDAO.userNameExists(user.getName())) {
            userDAO.deleteUser(user.getName());
            System.out.println("S ha borrat el usuari: "+ user.getName());

        }*/
    }

}
