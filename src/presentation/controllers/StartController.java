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

/**
 * Esta clase servira para representar el controlador del menu principal de nuestra clase
 */
public class StartController implements ActionListener {

    private MainView mainView;

    private GameManager gameManager;

    private UserManager userManager;
    private StatisticsView statisticsView;

    private StatisticsManager statisticsManager;
    private ChargeView chargeView;
    private ConfiguredView configuredView;
    private DeleteView deleteView;

    /**
     * Este es el constructor de nuestra vista
     * @param mainView esta es la vista principal de nuestro programa
     * @param gameManager el gestor de partidas de nuestro programa
     * @param userManager el gestor de usuarios de nuestro programa
     * @param statisticsView la vista de estadisticas
     * @param statisticsManager el gestor de estadisticas
     * @param chargeView la vista de carga de partidas
     * @param configuredView la vista de carga de partida configurada
     * @param deleteView al vista de eliminación de una vista
     */
    public StartController(MainView mainView, GameManager gameManager, UserManager userManager, StatisticsView statisticsView,StatisticsManager statisticsManager,ChargeView chargeView, ConfiguredView configuredView, DeleteView deleteView) {
        this.mainView = mainView;
        this.gameManager = gameManager;
        this.userManager = userManager;
        this.statisticsManager = statisticsManager;
        this.statisticsView = statisticsView;
        this.chargeView = chargeView;
        this.configuredView = configuredView;
        this.deleteView = deleteView;
    }

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
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
                statisticsView.updateData(statisticsManager.searchGameStatistics(userManager.getUser()));
                mainView.showStatistics();
                break;
        }

    }

    /**
     * Metodo para actualizar el combo box de nuestra vista
     */
    public void updatecomboBox(){
        ArrayList<String> games = gameManager.getGames(userManager.getUser());
        //Aqui hay que actualizar ambas combo box
        chargeView.updateComboBoxList(games);
        configuredView.updateComboBoxList(games);
        deleteView.updateComboBoxList(games);


        //Aqui pintamos todas las combobox
    }
}
