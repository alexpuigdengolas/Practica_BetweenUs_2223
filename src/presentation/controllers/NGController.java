package presentation.controllers;

import business.MapManager;
import business.entities.Game;
import business.PlayerManager;
import business.entities.character.Player;
import business.entities.map.Cell;
import business.entities.map.Map;
import presentation.views.*;
import business.ErrorMessage;
import business.UserManager;
import business.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NGController  implements ActionListener {

    private NewGameView NGView;

    private MainView mainView;

    private CardLayout cardLayout;
    private UserManager userManager;
    private GameManager gameManager;
    private GameView gameView;

    private ArrayList<String> colors;

    /**
     * Constructor del controller
     * @param NGView
     * @param mainView
     * @param cardLayout
     */

    public NGController(NewGameView NGView, MainView mainView, CardLayout cardLayout, UserManager userManager, GameManager gameManager, GameView gameView) {
        this.NGView = NGView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.gameView = gameView;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

            case NewGameView.BTN_MAP:
                //El cargador de archivos, para seleccionar el mapa
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
                //Si no ha cogido mapa elije el de station
                if(NGView.getMap().equals("Select File")){
                    mapName = "Station.json";
                }else{
                    mapName = NGView.getMap();
                }
                Game game  = new Game(NGView.getNameMap(), NGView.getPlayers(), NGView.getImp(), NGView.getColor(), mapName,userManager.getUser());
                try{
                    //Comprueba que cumpla condiciones y lo guarda si asi lo hace
                    gameManager.checkGame(game);
                    gameManager.saveGame(game);

                    //Printar la vista del mapa
                    Map map = MapManager.llegeixMapa(mapName);
                    //MapManager mapManager = new MapManager(map);
                    //Aqui creariem tots els crewmembers i impostors necesaris per jugar

                    //Creem el player
                    Player userPlayer = new Player(NGView.getColor());
                    //Coloquem els jugadors a la cella de la cafeteria
                    //Cell initialCell = gameManager.getCafeCell(map.getCells());
                    //userPlayer.setCell(initialCell);


                    //PlayerManager playerManager = new PlayerManager(userPlayer);
                    //mapManager.setPlayerManager(playerManager);


                    gameView.setMap(map,userPlayer);

                    mainView.showGame();
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
