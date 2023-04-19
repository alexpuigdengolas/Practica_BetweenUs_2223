package presentation.controllers;

import presentation.views.MainView;
import presentation.views.NewGameView;
import presentation.views.StartView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NGController  implements ActionListener {

    private NewGameView NGView;

    private MainView mainView;

    private CardLayout cardLayout;


    /**
     * Constructor del controller
     * @param NGView
     * @param mainView
     * @param cardLayout
     */

    public NGController(NewGameView NGView, MainView mainView, CardLayout cardLayout) {
        this.NGView = NGView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case NewGameView.BTN_P -> System.out.print("EI");
            case NewGameView.BTN_BACK -> mainView.showStart();

        }
    }
}
