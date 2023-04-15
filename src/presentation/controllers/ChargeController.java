package presentation.controllers;

import business.UserManager;
import business.entities.User;
import presentation.views.ChargeView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargeController {
    private ChargeView chargeView;
    private MainView mainView;
    private CardLayout cardLayout;


    public ChargeController(ChargeView chargeView, MainView mainView, CardLayout cardLayout) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
    }


}
