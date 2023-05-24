package presentation.controllers;

import business.GameManager;
import presentation.views.DeleteView;
import presentation.views.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteController implements ActionListener {

    private DeleteView deleteView;
    private MainView mainView;
    private CardLayout cardLayout;

    private GameManager gameManager;

    public DeleteController(DeleteView deleteView, MainView mainView, CardLayout cardLayout, GameManager gameManager) {
        this.deleteView = deleteView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case DeleteView.BTN_BACK -> mainView.showStart();
            case DeleteView.BTN_STI -> mainView.showSettings();
            case DeleteView.BTN_CHA -> {
                String nom = deleteView.optionSelected();
                if(JOptionPane.OK_OPTION == deleteView.confirmPopUp("Estas segur que vols BORRAR el game "+nom+"?")){
                    gameManager.deleteGame(nom);
                    deleteView.updateComboBoxList(gameManager.getGames(gameManager.getUserManager().getUser()));
                }

            }
        }
    }
}
