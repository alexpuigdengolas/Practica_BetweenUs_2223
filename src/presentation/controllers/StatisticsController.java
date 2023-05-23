package presentation.controllers;

import business.GameManager;
import business.StatisticsManager;
import presentation.views.MainView;
import presentation.views.StartView;
import presentation.views.StatisticsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsController implements ActionListener {
    private StatisticsView statisticsView;
    private MainView mainView;
    private CardLayout cardLayout;

    private StatisticsManager statisticsManager;

    public StatisticsController(StatisticsView statisticsView, MainView mainView, CardLayout cardLayout,StatisticsManager statisticsManager) {
        this.statisticsView = statisticsView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.statisticsManager = statisticsManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case StatisticsView.BTN_STI:
                mainView.showSettings();
                break;
            case StatisticsView.BTN_BACK:
                mainView.showStart();
                break;
        }
    }
}
