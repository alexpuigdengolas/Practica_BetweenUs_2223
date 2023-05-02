package presentation.controllers;

import business.ChargeManager;
import business.DeleteManager;
import business.MapManager;
import business.NGManager;
import business.entities.Game;
import business.entities.map.Map;
import presentation.views.DeleteView;
import presentation.views.MainView;

import javax.swing.*;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case DeleteView.BTN_BACK -> mainView.showStart();
            case DeleteView.BTN_STI -> mainView.showSettings();
            case DeleteView.BTN_CHA -> {
                String nom = deleteView.optionSelected();
                if(JOptionPane.OK_OPTION == deleteView.confirmPopUp("Estas segur que vols BORRAR el game "+nom+"?")){
                    deleteManager.deleteGame(nom);
                    games = deleteManager.getGames();
                    deleteView.updateComboBoxList(games);
                }

            }
        }
    }
}
