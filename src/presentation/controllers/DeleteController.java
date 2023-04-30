package presentation.controllers;

import business.ChargeManager;
import business.DeleteManager;
import business.MapManager;
import business.NGManager;
import business.entities.Game;
import business.entities.map.Map;
import presentation.views.DeleteView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteController implements ActionListener {

    private DeleteView deleteView;
    private MainView mainView;
    private CardLayout cardLayout;

    private DeleteManager deleteManager;
    ArrayList<String> games = new ArrayList<>();

    public DeleteController(DeleteView deleteView, MainView mainView, CardLayout cardLayout, DeleteManager deleteManager) {
        this.deleteView = deleteView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.deleteManager = deleteManager;
        games = deleteManager.getGames();
        deleteView.updateComboBoxList(games);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case DeleteView.BTN_BACK -> mainView.showStart();
            case DeleteView.BTN_STI -> mainView.showSettings();
            case DeleteView.BTN_CHA -> {
                String nom = deleteView.optionSelected();
                deleteManager.deleteGame(nom);
                games = deleteManager.getGames();
                deleteView.updateComboBoxList(games);
            }
        }
    }
}
