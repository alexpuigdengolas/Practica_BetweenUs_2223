package presentation.controllers;

import business.ChargeManager;
import business.MapManager;
import business.NGManager;
import business.entities.Game;
import business.entities.map.Map;
import presentation.views.ChargeView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChargeController implements ActionListener{
    private ChargeView chargeView;
    private MainView mainView;
    private CardLayout cardLayout;

    private ChargeManager chargeManager;

    ArrayList<String> games = new ArrayList<>();

    public ChargeController(ChargeView chargeView, MainView mainView, CardLayout cardLayout, ChargeManager chargeManager) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.chargeManager = chargeManager;
        games = chargeManager.getGames();
        chargeView.updateComboBoxList(games);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case ChargeView.BTN_BACK -> mainView.showStart();
            case ChargeView.BTN_STI -> mainView.showSettings();
            case ChargeView.BTN_CHA -> {
                String nom = chargeView.optionSelected();
                Game Seleccionat = chargeManager.searchGame(nom);
                Map map = MapManager.llegeixMapa(Seleccionat.getMap());
                mainView.showMap(map);
            }
        }
    }
}
