package presentation.controllers;

import business.GameManager;
import business.MapManager;
import business.NpcManager;
import business.PlayerManager;
import business.entities.Game;
import business.entities.character.Character;
import business.entities.character.Impostor;
import business.entities.character.Player;
import business.entities.map.Cell;
import business.entities.map.Map;
import presentation.views.ConfiguredView;
import presentation.views.GameView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase nos se usara como controlador de configuración
 */
public class ConfiguredController implements ActionListener {

    private ConfiguredView configuredView;
    private MainView mainView;
    private CardLayout cardLayout;
    private GameManager gameManager;

    private ArrayList<String> colors;
    private GameView gameView;
    private GameController gameController;


    public ConfiguredController(ConfiguredView configuredView, MainView mainView, CardLayout cardLayout, GameManager gameManager,GameView gameView,GameController gameController) {
        this.configuredView = configuredView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
        this.gameView = gameView;
        this.gameController = gameController;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ConfiguredView.BTN_BACK -> mainView.showStart();
            case ConfiguredView.BTN_STI -> mainView.showSettings();
            case ConfiguredView.BTN_CHA -> {

                String nom = configuredView.optionSelected();
                int firstColor = 0;
                Game game = gameManager.searchGame(nom);

                Map map = MapManager.llegeixMapa(game.getMap());
                MapManager mapManager = new MapManager(map);

                Player userPlayer = new Player(game.getColor());
                if (userPlayer.getColor().equals("RED")) {
                    firstColor++;
                }
                //Creem els npcs
                LinkedList<Character> npcs = gameManager.getNpcs(game.getPlayers() - game.getImpostors() - 1, game.getColor(), firstColor, colors, mapManager);
                firstColor = getImpostorsStarterColor(gameManager.getUserColorPosition(game.getColor(), colors), npcs.size(), firstColor);
                LinkedList<Character> impostors = gameManager.getImpostors(game.getImpostors(), game.getColor(), firstColor + npcs.size(), colors, mapManager);

                LinkedList<Character> players = new LinkedList<>();
                players.addAll(npcs);
                players.addAll(impostors);
                Collections.shuffle(players);

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

                    //Coloquem els jugadors a la cella de la cafeteria
            }
        }
    }
    public int getImpostorsStarterColor(int userPosition, int npcs, int starterColor) {
        if (userPosition <= npcs) {
            return starterColor+1;
        } else {
            return starterColor;
        }
    }
}
