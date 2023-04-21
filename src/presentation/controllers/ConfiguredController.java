package presentation.controllers;

import presentation.views.ConfiguredView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguredController implements ActionListener {

    private ConfiguredView configuredView;
    private MainView mainView;
    private CardLayout cardLayout;

    public ConfiguredController(ConfiguredView configuredView, MainView mainView, CardLayout cardLayout) {
        this.configuredView = configuredView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ConfiguredView.BTN_BACK -> mainView.showStart();
            case ConfiguredView.BTN_STI -> mainView.showSettings();
            case ConfiguredView.BTN_CHA -> {
                System.out.println(configuredView.optionSelected());
            }
        }
    }
}
