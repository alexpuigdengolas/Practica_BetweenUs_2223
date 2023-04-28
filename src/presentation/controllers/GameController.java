package presentation.controllers;

import presentation.views.GameView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private GameView gameView;
    private MainView mainView;
    private CardLayout cardLayout;

    public GameController(GameView gameView, MainView mainView, CardLayout cardLayout) {
        this.gameView = gameView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GameView.BTN_BACK -> mainView.showStart();
            case GameView.BTN_STI -> mainView.showSettings();
            //TODO: Aciones de la botonera lateral
        }
    }
}
