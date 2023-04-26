package presentation.controllers;

import business.entities.Game;
import presentation.views.MainView;
import presentation.views.NewGameView;
import presentation.views.StartView;
import business.ErrorMessage;
import business.UserManager;
import business.NGManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

            case NewGameView.BTN_MAP:
                String path = getMPath();
                JFileChooser jfc = new JFileChooser(path);
                System.out.println(NGView.getMap());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    String name = selectedFile.getName();
                    NGView.setMapName(name);
                }
                break;
            case NewGameView.BTN_CHA:
                String mapName;
                if(NGView.getMap().equals("Select File")){
                    mapName = "Station.json";
                }else{
                    mapName = NGView.getMap();
                }
                Game game  = new Game(NGView.getNameMap(), NGView.getPlayers(), NGView.getImp(), NGView.getColor(), mapName,userManager.getUser());
                try{
                    ngManager.checkGame(game);
                    ngManager.saveGame(game);
                    mainView.showStart();
                }catch (ErrorMessage ex){
                    NGView.printNewGameErrors(ex.getMessage());
                }

                break;
            case NewGameView.BTN_BACK:
                mainView.showStart();
                break;



        }
    }


    public String getMPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "/src/mapFiles";
    }

}
