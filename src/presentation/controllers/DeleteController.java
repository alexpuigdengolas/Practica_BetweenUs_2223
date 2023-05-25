package presentation.controllers;

import business.GameManager;
import presentation.views.DeleteView;
import presentation.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase servira para controlar la vista de eliminacion de partidas de la base de datos
 */
public class DeleteController implements ActionListener {

    private final DeleteView deleteView;
    private final MainView mainView;

    private final GameManager gameManager;

    /**
     * Este sera el constructor de esta clase
     * @param deleteView esta sera la vista que vamos a controllar desde esta clase
     * @param mainView la vista principal del codigo
     * @param gameManager el gestor de las partidas
     */
    public DeleteController(DeleteView deleteView, MainView mainView, GameManager gameManager) {
        this.deleteView = deleteView;
        this.mainView = mainView;
        this.gameManager = gameManager;
    }

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
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
