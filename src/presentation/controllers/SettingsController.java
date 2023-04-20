package presentation.controllers;

import business.UserManager;
import presentation.views.MainView;
import presentation.views.SettingsView;

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
                //TODO: Se tendra que borrar los games de esta persona
                //TODO:YO haria un pop up de estos que hhas de poner la palabra BORRAR para confiramr

                if (!userManager.deleteUser()) {
                    System.out.println("no se esta borrando");
                    //Se muestra que no hay usuario que borrar
                }else{
                    userManager.resetName();
                    mainView.showLogin();
                }

                break;

            case SettingsView.BTN_LOG:
                //TODO: Guardar información del usuario logeado al hacer login
                //Borrem el nom de la ram
                userManager.resetName();
                mainView.showLogin();
                break;
        }
    }
}
