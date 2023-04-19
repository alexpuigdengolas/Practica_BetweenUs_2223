package presentation.controllers;

import presentation.views.MainView;
import presentation.views.SettingsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController implements ActionListener {

    private SettingsView settingsView;
    private MainView mainView;
    private CardLayout cardLayout;

    public SettingsController(SettingsView settingsView, MainView mainView, CardLayout cardLayout) {
        this.settingsView = settingsView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case SettingsView.BTN_BACK :
                mainView.showStart();
                break;

            case SettingsView.BTN_DEL:
                //TODO: Hacer DELETE USER Query accesible desde el controller
                System.out.println("El usuario sera eliminado");
                mainView.showLogin();
                break;

            case SettingsView.BTN_LOG:
                //TODO: Guardar información del usuario logeado al hacer login
                mainView.showLogin();
                break;
        }
    }
}
