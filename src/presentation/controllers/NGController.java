package presentation.controllers;

import business.*;
import business.entities.Game;
import business.entities.character.Impostor;
import business.entities.character.Npc;
import business.entities.character.Player;
import business.entities.map.Cell;
import business.entities.map.Map;
import presentation.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NGController  implements ActionListener {

    private NewGameView NGView;

    private MainView mainView;

    private CardLayout cardLayout;
    private UserManager userManager;
    private GameManager gameManager;
    private GameView gameView;

    private ArrayList<String> colors;

    private GameController gameController;

    /**
     * Constructor del controller
     * @param NGView
     * @param mainView
     * @param cardLayout
     */

    public NGController(NewGameView NGView, MainView mainView, CardLayout cardLayout, UserManager userManager, GameManager gameManager, GameView gameView,GameController gameController) {
        this.NGView = NGView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.gameView = gameView;
        this.gameController = gameController;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));

    }
    public NGController(){}

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
                //TODO: Mirar si esto lo tiene que hacer el manager de gamemanager
                Game game  = new Game(NGView.getNameMap(), NGView.getPlayers(), NGView.getImp(), NGView.getColor(), mapName,userManager.getUser());
                try{
                    //Comprueba que cumpla condiciones y lo guarda si asi lo hace
                    gameManager.checkGame(game);
                    gameManager.saveGame(game);
                    int firstColor = 0;

                    //Printar la vista del mapa
                    Map map = MapManager.llegeixMapa(mapName);
                    MapManager mapManager = new MapManager(map);
                    //Aqui creariem tots els crewmembers i impostors necesaris per jugar

                    //Creem el player
                    Player userPlayer = new Player(NGView.getColor());
                    if (userPlayer.getColor().equals("RED")) {
                        firstColor++;
                    }
                    //Creem els npcs
                    LinkedList<Npc> npcs = gameManager.getNpcs(NGView.getPlayers() - NGView.getImp() - 1, NGView.getColor(), firstColor, colors, mapManager);



                    //Coloquem els jugadors a la cella de la cafeteria
                    Cell initialCell = gameManager.getCafeCell(map.getCells());
                    userPlayer.setCell(initialCell);
                    gameManager.setInitialCell(userPlayer,npcs,map.getCells());

                    for (Npc npc: npcs) {
                        gameManager.startPlayers(npc);
                    }

                    //Creem els manegers que el controlen
                    PlayerManager playerManager = new PlayerManager(userPlayer);
                    NpcManager npcManager = new NpcManager(npcs);
                    for (Npc npc: npcs) {

                        npc.setNpcManager(npcManager);
                    }

                    gameManager.setPlayerManager(playerManager);
                    mapManager.setPlayerManager(playerManager);
                    gameManager.setMapManager(mapManager);

                    gameManager.setNpcManager(npcManager);
                    //Creidem la vista del mapa
                    gameView.setMap(map,userPlayer,npcs);
                    gameController.startMapThread();



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
    public int[] getColorComponents(String color) {
        int[] components = new int[3];
        switch (color) {
            case "PURPLE":
                components[0] = 102;
                components[2] = 153;
                return components;

            case "BROWN":
                components[0] = 102;
                components[1] = 51;
                return components;

            case "CYAN":
                components[1] = 255;
                components[2] = 255;
                return components;
            default:
                components[0] = 50;
                components[1] = 205;
                components[2] = 50;
                return components;
        }
    }

}
