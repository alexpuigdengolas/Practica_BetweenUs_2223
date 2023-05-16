package presentation.controllers;

import business.GameManager;
import business.StatisticsManager;
import business.UserManager;
import business.entities.User;
import presentation.views.MainView;
import presentation.views.StartView;
import presentation.views.StatisticsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private StartView startView;

    private MainView mainView;

    private CardLayout cardLayout;

    private GameManager gameManager;

    private UserManager userManager;
    private StatisticsView statisticsView;

    /**
     * Constructor del controller
     * @param startView
     * @param mainView
     * @param cardLayout
     */
    public StartController(StartView startView, MainView mainView, CardLayout cardLayout, GameManager gameManager, UserManager userManager, StatisticsView statisticsView) {
        this.startView = startView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
        this.userManager = userManager;
        this.statisticsView = statisticsView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case StartView.BTN_STI :

                mainView.showSettings();
                break;

            case StartView.BTN_CRE :

                mainView.showNG();
                break;

            case StartView.BTN_CON :
                gameManager.updatecomboBox();
                mainView.showConfigured();
                break;

            case StartView.BTN_CHAR :
                gameManager.updatecomboBox();
                mainView.showCharge();
                break;

            case StartView.BTN_DEL :
                gameManager.updatecomboBox();
                mainView.showDelete();
                break;

            case StartView.BTN_STA:
                //TODO: Mostrar los valores de las estadísticas
                statisticsView.updateData(userManager.getUser());
                mainView.showStatistics();
                break;
        }



    }
}
