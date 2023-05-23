package presentation.controllers;

import business.GameManager;
import business.MapManager;
import business.entities.Game;
import business.entities.map.Map;
import presentation.views.ChargeView;
import presentation.views.GameView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase nos se usara como controlador de la vista que nos permite cargar una partida
 */
public class ChargeController implements ActionListener{
    /**
     * La vista de carga de partidas
     */
    private ChargeView chargeView;
    /**
     * La main view del programa
     */
    private MainView mainView;

    /**
     * La clase que nos permite controlar el contenido de las partidas de la base de datos
     */
    private GameManager gameManager;

    /**
     * La vista del juego
     */
    private GameView gameView;

    /**
     * Este metodo nos es el constructor de nuestra clase
     * @param chargeView la vista de la pantalla de carga de partidas existentes
     * @param mainView la main view del programa
     * @param gameManager el manager de la información de las partidas
     * @param gameView al vista de la partida en si
     */
    public ChargeController(ChargeView chargeView, MainView mainView, GameManager gameManager, GameView  gameView) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.gameManager = gameManager;
        this.gameView = gameView;
    }

    /**
     * Este metodo nos permite asignar lo que queremos que ocurra al seleccionar cualquier boton de la vista de
     * carga
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ChargeView.BTN_BACK -> mainView.showStart();
            case ChargeView.BTN_STI -> mainView.showSettings();
            case ChargeView.BTN_CHA -> {
                String nom = chargeView.optionSelected();

                //gameView.setMap(map);
                //mainView.showGame();
            }
        }
    }
}
