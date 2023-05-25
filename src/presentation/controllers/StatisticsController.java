package presentation.controllers;

import business.GameManager;
import business.StatisticsManager;
import presentation.views.MainView;
import presentation.views.StartView;
import presentation.views.StatisticsView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sera el controlador de nuestra vista de estadisticas
 */
public class StatisticsController implements ActionListener {
    private MainView mainView;


    /**
     * Este es el constructor de nuestra clase
     * @param mainView la vista principal del programa
     */
    public StatisticsController(MainView mainView) {
        this.mainView = mainView;

    }

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
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
