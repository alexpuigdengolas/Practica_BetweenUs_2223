package presentation.controllers;

import business.GameManager;
import business.StatisticsManager;
import business.UserManager;
import business.entities.User;
import presentation.views.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartController implements ActionListener {

    private StartView startView;

    private MainView mainView;

    private CardLayout cardLayout;

    private GameManager gameManager;

    private UserManager userManager;
    private StatisticsView statisticsView;

    private StatisticsManager statisticsManager;
    private ChargeView chargeView;
    private ConfiguredView configuredView;
    private DeleteView deleteView;

    /**
     * Constructor del controller
     * @param startView
     * @param mainView
     * @param cardLayout
     */
    public StartController(StartView startView, MainView mainView, CardLayout cardLayout, GameManager gameManager, UserManager userManager, StatisticsView statisticsView,StatisticsManager statisticsManager,ChargeView chargeView, ConfiguredView configuredView, DeleteView deleteView) {
        this.startView = startView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
        this.userManager = userManager;
        this.statisticsManager = statisticsManager;
        this.statisticsView = statisticsView;
        this.chargeView = chargeView;
        this.configuredView = configuredView;
        this.deleteView = deleteView;
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
                updatecomboBox();

                mainView.showConfigured();
                break;

            case StartView.BTN_CHAR :
                updatecomboBox();
                mainView.showCharge();
                break;

            case StartView.BTN_DEL :
                updatecomboBox();
                mainView.showDelete();
                break;

            case StartView.BTN_STA:
                //TODO: Mostrar los valores de las estadísticas
                statisticsView.updateData(statisticsManager.searchGameStatistics(userManager.getUser()));
                mainView.showStatistics();
                break;
        }

    }
    public void updatecomboBox(){
        ArrayList<String> games = gameManager.getGames();
        //Aqui hay que actualizar ambas combo box
        chargeView.updateComboBoxList(games);
        configuredView.updateComboBoxList(games);
        deleteView.updateComboBoxList(games);


        //Aqui pintamos todas las combobox
    }
}
