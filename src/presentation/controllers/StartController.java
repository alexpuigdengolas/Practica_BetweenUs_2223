package presentation.controllers;

import presentation.views.MainView;
import presentation.views.StartView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private StartView startView;

    private MainView mainView;

    private CardLayout cardLayout;


    /**
     * Constructor del controller
     * @param startView
     * @param mainView
     * @param cardLayout
     */
    public StartController(StartView startView, MainView mainView, CardLayout cardLayout) {
        this.startView = startView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){

            case StartView.BTN_STI : break;

            case StartView.BTN_CRE : break;

            case StartView.BTN_CON : break;

            case StartView.BTN_CHAR : break;

            case StartView.BTN_DEL : break;

        }



    }
}