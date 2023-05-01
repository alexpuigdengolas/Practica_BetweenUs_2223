package presentation.controllers;

import business.ChargeManager;
import business.ConfigManager;
import business.DeleteManager;
import presentation.views.DeleteView;
import presentation.views.MainView;
import presentation.views.StartView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private StartView startView;

    private MainView mainView;

    private CardLayout cardLayout;

    private ChargeManager chargeManager;

    private ConfigManager configManager;

    private DeleteManager deleteManager;

    /**
     * Constructor del controller
     * @param startView
     * @param mainView
     * @param cardLayout
     */
    public StartController(StartView startView, MainView mainView, CardLayout cardLayout, ChargeManager chargeManager, ConfigManager configManager, DeleteManager deleteManager) {
        this.startView = startView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.chargeManager = chargeManager;
        this.configManager = configManager;
        this.deleteManager = deleteManager;
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
                configManager.updateConfig();
                mainView.showConfigured();
                break;

            case StartView.BTN_CHAR :
                chargeManager.updateCharge();
                mainView.showCharge();
                break;

            case StartView.BTN_DEL :
                deleteManager.updateDelete();
                mainView.showDelete();
                break;

        }



    }
}
