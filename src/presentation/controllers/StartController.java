package presentation.controllers;

import business.GameManager;
import presentation.views.MainView;
import presentation.views.StartView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private StartView startView;

    private MainView mainView;

    private CardLayout cardLayout;

    private GameManager gameManager;

    /**
     * Constructor del controller
     * @param startView
     * @param mainView
     * @param cardLayout
     */
    public StartController(StartView startView, MainView mainView, CardLayout cardLayout, GameManager gameManager) {
        this.startView = startView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
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
                gameManager.updatecomboBox();
                mainView.showConfigured();
                break;

            case StartView.BTN_CHAR :
                gameManager.updatecomboBox();
                mainView.showCharge();
                break;

            case StartView.BTN_DEL :
                gameManager.updatecomboBox();
                mainView.showDelete();
                break;

        }



    }
}
