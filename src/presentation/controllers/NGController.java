package presentation.controllers;

import business.*;
import business.entities.Game;
import business.entities.character.Character;
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

/**
 * Esta clase se usara para controlar la vista de creación de partidas
 */
public class NGController  implements ActionListener {

    private NewGameView NGView;

    private MainView mainView;

    private UserManager userManager;
    private GameManager gameManager;
    private GameView gameView;

    private ArrayList<String> colors;

    private GameController gameController;

    private Game game;


    /**
     * Constructor del controller
     * @param NGView la vista
     * @param mainView la vista principal del programa
     */
    public NGController(NewGameView NGView, MainView mainView, UserManager userManager, GameManager gameManager, GameView gameView,GameController gameController) {
        this.NGView = NGView;
        this.mainView = mainView;
        this.userManager = userManager;
        this.gameManager = gameManager;
        this.gameView = gameView;
        this.gameController = gameController;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));

    }

    /**
     * Este es un constructor vacio de nuestra clase
     */
    public NGController(){}

    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
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

                game  = gameManager.newGame(NGView.getNameMap(), NGView.getPlayers(), NGView.getImp(), NGView.getColor(), mapName,userManager.getUser());
                gameManager.setGameName(NGView.getNameMap());
                try{
                    //Comprueba que cumpla condiciones y lo guarda si asi lo hace
                    gameManager.checkGame(game);
                    gameManager.saveGame(game);

                    int firstColor = 0;

                    //Printar la vista del mapa
                    Map map = MapManager.llegeixMapa(mapName);
                    MapManager mapManager = new MapManager(map);

                    //Creem el player
                    Player userPlayer = new Player(NGView.getColor());
                    if (userPlayer.getColor().equals("RED")) {
                        firstColor++;
                    }
                    //Creem els npcs
                    LinkedList<Character> npcs = gameManager.getNpcs(NGView.getPlayers() - NGView.getImp() - 1, NGView.getColor(), firstColor, colors, mapManager);
                    firstColor = getImpostorsStarterColor(gameManager.getUserColorPosition(NGView.getColor(), colors), npcs.size(), firstColor);
                    LinkedList<Character> impostors = gameManager.getImpostors(NGView.getImp(), NGView.getColor(), firstColor + npcs.size(), colors, mapManager);

                    LinkedList<Character> players = new LinkedList<>();
                    players.addAll(npcs);
                    players.addAll(impostors);
                    Collections.shuffle(players);


                    //Coloquem els jugadors a la cella de la cafeteria
                    Cell initialCell = gameManager.getCafeCell(map.getCells());
                    userPlayer.setCell(initialCell);
                    gameManager.setInitialCell(userPlayer,players,map.getCells());

                    for (Character character: players) {
                        gameManager.startPlayers(character);
                    }

                    //Creem els manegers que el controlen
                    PlayerManager playerManager = new PlayerManager(userPlayer);
                    NpcManager npcManager = new NpcManager(players);
                    for (Character character: players) {

                        character.setNpcManager(npcManager);
                    }

                    gameManager.setPlayerManager(playerManager);
                    mapManager.setPlayerManager(playerManager);
                    gameManager.setMapManager(mapManager);

                    gameManager.setNpcManager(npcManager);
                    //Creidem la vista del mapa
                    gameView.setMap(map,userPlayer,players);
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

    /**
     * Getter del path de nuestros mapas
     * @return el path de la localización de estos mapas
     */
    public String getMPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "/src/mapFiles";
    }

    /**
     * Este metodo nos permite conseguir algunos colores dependiendo del nombre de su color
     * @param color el nombre del color que buscamos
     * @return los componentes RGB que componen ese color
     */
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

    /**
     *
     * @param userPosition
     * @param npcs
     * @param starterColor
     * @return
     */
    public int getImpostorsStarterColor(int userPosition, int npcs, int starterColor) {
        if (userPosition <= npcs) {
            return starterColor+1;
        } else {
            return starterColor;
        }
    }

}
