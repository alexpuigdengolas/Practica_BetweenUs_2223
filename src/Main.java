import business.*;
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
        ChargeManager chargeManager = new ChargeManager(chargeView);
        DeleteManager deleteManager = new DeleteManager(deleteView);
        ConfigManager configManager = new ConfigManager(configuredView);
        MapView mapView  = new MapView();
        GameView gameView = new GameView();


        CardLayout cardLayout = new CardLayout();
        MainView mainView = new MainView(cardLayout, registerView, loginView, startView, nGView, statisticsView, deleteView, configuredView, chargeView, settingsView,mapView, gameView);

        RegisterController registerController = new RegisterController(registerView, mainView, cardLayout,userManager);
        LoginController loginController = new LoginController(loginView, mainView, cardLayout,userManager);
        StartController startController = new StartController(startView, mainView, cardLayout, chargeManager, configManager, deleteManager);
        SettingsController settingsController = new SettingsController(settingsView, mainView, cardLayout,userManager);
        ConfiguredController configuredController = new ConfiguredController(configuredView, mainView, cardLayout, configManager);
        ChargeController chargeController = new ChargeController(chargeView, mainView, cardLayout,  chargeManager, gameView);
        DeleteController deleteController = new DeleteController(deleteView, mainView, cardLayout, deleteManager);
        GameController gameController = new GameController(gameView, mainView, cardLayout);
        NGController NGcontroller = new NGController(nGView, mainView, cardLayout,userManager, ngManager, gameView);

        registerView.registerController(registerController);
        loginView.loginController(loginController);
        startView.startController(startController);
        nGView.NGController(NGcontroller);
        settingsView.settingsController(settingsController);
        configuredView.configuredController(configuredController);
        chargeView.chargeController(chargeController);
        deleteView.deleteController(deleteController);
        gameView.gameController(gameController);

        mainView.start();

        /* Test correcte de lectura
        ReaderMap readerMap = new ReaderMap("Station.json");
        Map map = readerMap.getMap();
        System.out.println(map.getMapName());
        */
    }

}
