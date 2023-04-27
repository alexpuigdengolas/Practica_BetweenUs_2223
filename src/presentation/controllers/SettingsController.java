package presentation.controllers;

import business.UserManager;
import presentation.views.MainView;
import presentation.views.SettingsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController implements ActionListener {

    private SettingsView settingsView;
    private MainView mainView;
    private CardLayout cardLayout;
    private UserManager userManager;

    public SettingsController(SettingsView settingsView, MainView mainView, CardLayout cardLayout, UserManager userManager) {
        this.settingsView = settingsView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case SettingsView.BTN_BACK :
                mainView.showStart();
                break;

            case SettingsView.BTN_DEL:
                if(JOptionPane.OK_OPTION == settingsView.confirmPopUp("Estas segur que vols BORRAR el compte?")){
                    if (!userManager.deleteUser()) {
                        System.out.println("no se esta borrando");
                        //Se muestra que no hay usuario que borrar
                    }else{
                        userManager.resetName();
                        mainView.showLogin();
                        //TODO: Se tendra que borrar los games de esta persona
                    }
                }

                break;

            case SettingsView.BTN_LOG:
                if(JOptionPane.OK_OPTION == settingsView.confirmPopUp("Estas segur que vols fer LOG OUT del compte?")){
                    userManager.resetName();
                    mainView.showLogin();
                }

                break;
        }
    }
}
