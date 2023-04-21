package presentation.controllers;

import presentation.views.DeleteView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteController implements ActionListener {

    private DeleteView deleteView;
    private MainView mainView;
    private CardLayout cardLayout;

    public DeleteController(DeleteView deleteView, MainView mainView, CardLayout cardLayout) {
        this.deleteView = deleteView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case DeleteView.BTN_BACK -> mainView.showStart();
            case DeleteView.BTN_STI -> mainView.showSettings();
            case DeleteView.BTN_CHA -> {
                System.out.println(deleteView.optionSelected());
            }
        }
    }
}
