package presentation.controllers;

import presentation.views.ChargeView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargeController implements ActionListener{
    private ChargeView chargeView;
    private MainView mainView;
    private CardLayout cardLayout;


    public ChargeController(ChargeView chargeView, MainView mainView, CardLayout cardLayout) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case ChargeView.BTN_BACK -> mainView.showStart();
            case ChargeView.BTN_STI -> mainView.showSettings();
            case ChargeView.BTN_CHA -> {
                System.out.println(chargeView.optionSelected());
            }
        }
    }
}
