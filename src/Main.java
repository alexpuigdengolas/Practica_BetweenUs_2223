import business.NGManager;
import business.UserManager;
import business.entities.map.Map;
import persistance.Conn.*;
import business.entities.User;
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
        NGManager ngManager = new NGManager();

        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView, startView, nGView, statisticsView, deleteView, configuredView, chargeView, settingsView);

        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout,userManager);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout,userManager);
        StartController startController = new StartController(startView, mainView, cardLayout);
        NGController NGcontroller = new NGController(nGView, mainView, cardLayout,userManager, ngManager);
        SettingsController settingsController = new SettingsController(settingsView, mainView, cardLayout,userManager);
        ConfiguredController configuredController = new ConfiguredController(configuredView, mainView, cardLayout);
        ChargeController chargeController = new ChargeController(chargeView, mainView, cardLayout);
        DeleteController deleteController = new DeleteController(deleteView, mainView, cardLayout);

        registerView.registerController(registerController);
        loginView.loginController(loginController);
        startView.startController(startController);
        nGView.NGController(NGcontroller);
        settingsView.settingsController(settingsController);
        configuredView.configuredController(configuredController);
        chargeView.chargeController(chargeController);
        deleteView.deleteController(deleteController);

        mainView.start();

        /* Test correcte de lectura
        ReaderMap readerMap = new ReaderMap("Station.json");
        Map map = readerMap.getMap();
        System.out.println(map.getMapName());
        */
    }

}
