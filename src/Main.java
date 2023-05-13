import business.*;
import business.entities.character.Player;
import presentation.controllers.*;
import presentation.views.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {


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
            GameManager gameManager = new GameManager();



            MapView mapView  = new MapView();
            GameView gameView = new GameView();


            CardLayout cardLayout = new CardLayout();
            MainView mainView = new MainView(cardLayout, registerView, loginView, startView, nGView, statisticsView, deleteView, configuredView, chargeView, settingsView,mapView, gameView);

            RegisterController registerController = new RegisterController(registerView, mainView, cardLayout,userManager);
            LoginController loginController = new LoginController(loginView, mainView, cardLayout,userManager);
            GameController gameController = new GameController(gameView, mainView, cardLayout,gameManager);
            StartController startController = new StartController(startView, mainView, cardLayout, gameManager);
            SettingsController settingsController = new SettingsController(settingsView, mainView, cardLayout,userManager);
            ConfiguredController configuredController = new ConfiguredController(configuredView, mainView, cardLayout);
            ChargeController chargeController = new ChargeController(chargeView, mainView, cardLayout, gameManager,gameView);
            DeleteController deleteController = new DeleteController(deleteView, mainView, cardLayout,gameManager);
            NGController NGcontroller = new NGController(nGView, mainView, cardLayout,userManager, gameManager, gameView,gameController);

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

            }
        });

        }

}
