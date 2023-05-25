package presentation.controllers;

import business.UserManager;
import presentation.views.MainView;
import presentation.views.SettingsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase se usara como controlador de la vista de ajustes
 */
public class SettingsController implements ActionListener {

    private final SettingsView settingsView;
    private final MainView mainView;
    private final UserManager userManager;

    /**
     * Este es el constructor de nuestra clase
     * @param settingsView la vista de ajustes
     * @param mainView la vista principal del programa
     * @param userManager el gestor de usuarios
     */
    public SettingsController(SettingsView settingsView, MainView mainView, UserManager userManager) {
        this.settingsView = settingsView;
        this.mainView = mainView;
        this.userManager = userManager;
    }

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case SettingsView.BTN_BACK :
                mainView.showStart();
                break;

            case SettingsView.BTN_DEL:
                if(JOptionPane.OK_OPTION == settingsView.confirmPopUp("Estas segur que vols BORRAR el compte?")){
                    userManager.deleteGames();
                    userManager.deleteUser();
                    userManager.resetName();
                    mainView.showLogin();

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
