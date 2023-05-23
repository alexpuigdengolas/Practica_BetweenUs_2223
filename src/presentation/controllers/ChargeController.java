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

public class ChargeController implements ActionListener{
    private ChargeView chargeView;
    private MainView mainView;
    private CardLayout cardLayout;

    private GameManager gameManager;
    private GameView gameView;


    public ChargeController(ChargeView chargeView, MainView mainView, CardLayout cardLayout, GameManager gameManager, GameView  gameView) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
        this.gameView = gameView;
    }

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
