package presentation.controllers;

import business.entities.Game;
import presentation.views.MainView;
import presentation.views.NewGameView;
import presentation.views.StartView;
import business.ErrorMessage;
import business.UserManager;
import business.NGManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class NGController  implements ActionListener {

    private NewGameView NGView;

    private MainView mainView;

    private CardLayout cardLayout;
    private UserManager userManager;
    private NGManager ngManager;


    /**
     * Constructor del controller
     * @param NGView
     * @param mainView
     * @param cardLayout
     */

    public NGController(NewGameView NGView, MainView mainView, CardLayout cardLayout,UserManager userManager,NGManager ngManager) {
        this.NGView = NGView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.userManager = userManager;
        this.ngManager = ngManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case NewGameView.BTN_CHA:
                Game game  = new Game(NGView.getNameMap(), NGView.getPlayers(), NGView.getImp(), NGView.getColor(), NGView.getMap(),userManager.getUser());
                try {
                    caseNGame(game);

                }catch (ErrorMessage ex){

                    //TODO:Aqui mostramos el mensaje de error que corresponda en un pop up
                    System.out.println(ex.getMessage());
                }

                //TODO:Aqui hacemos las cosas para mirar si los datos son correctos en caso contrario petaria.
                break;
            case NewGameView.BTN_BACK:
                mainView.showStart();
                break;

        }
    }

    private void caseNGame(Game game) throws ErrorMessage{
        try{
                ngManager.checkGame(game);
                ngManager.saveGame(game);
                mainView.showStart();
        }catch (ErrorMessage e){
            System.out.println(e.getMessage());
        }
    }

}
