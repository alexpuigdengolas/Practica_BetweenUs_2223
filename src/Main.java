import business.UserManager;
import persistance.Conn.*;
import presentation.controllers.*;
import presentation.views.*;

import java.awt.*;

public class Main {

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
        UserManager userManager = new UserManager();



        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView, startView, nGView, statisticsView, deleteView, configuredView, chargeView, settingsView);


        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout,userManager);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout,userManager);
        StartController startController = new StartController(startView, mainView, cardLayout);
        NGController NGcontroller = new NGController(nGView, mainView, cardLayout);
        SettingsController settingsController = new SettingsController(settingsView, mainView, cardLayout,userManager);
        ConfiguredController configuredController = new ConfiguredController(configuredView, mainView, cardLayout);

        registerView.registerController(registerController);
        loginView.loginController(loginController);
        startView.startController(startController);
        nGView.NGController(NGcontroller);
        settingsView.settingsController(settingsController);
        configuredView.configuredController(configuredController);

        mainView.start();

    }

}
