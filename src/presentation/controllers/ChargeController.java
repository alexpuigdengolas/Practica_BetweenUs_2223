package presentation.controllers;

import business.*;
import business.entities.Game;
import business.entities.character.Character;
import business.entities.character.Player;
import business.entities.map.Cell;
import business.entities.map.Map;
import presentation.views.ChargeView;
import presentation.views.GameView;
import presentation.views.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase nos se usara como controlador de la vista que nos permite cargar una partida
 */
public class ChargeController implements ActionListener{
    /**
     * La vista de carga de partidas
     */
    private final ChargeView chargeView;
    /**
     * La main view del programa
     */
    private final MainView mainView;

    /**
     * La clase que nos permite controlar el contenido de las partidas de la base de datos
     */
    private final GameManager gameManager;

    /**
     * La vista del juego
     */
    private final GameView gameView;

    //#nuevo
    private final GameController gameController;

    private final ArrayList<String> colors;

    /**
     * Este metodo nos es el constructor de nuestra clase
     * @param chargeView la vista de la pantalla de carga de partidas existentes
     * @param mainView la main view del programa
     * @param gameManager el manager de la información de las partidas
     * @param gameView al vista de la partida en si
     */
    public ChargeController(ChargeView chargeView, MainView mainView, GameManager gameManager, GameView  gameView,GameController gameController) {
        this.chargeView = chargeView;
        this.mainView = mainView;
        this.gameManager = gameManager;
        this.gameView = gameView;
        this.gameController = gameController;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
    }

    /**
     * Este metodo nos permite asignar lo que queremos que ocurra al seleccionar cualquier boton de la vista de
     * carga
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case ChargeView.BTN_BACK -> mainView.showStart();
            case ChargeView.BTN_STI -> mainView.showSettings();
            case ChargeView.BTN_CHA -> {
               //Esto en vez de empezar una partida desde donde se dejo empieza una partida desde 0 que ya estaba creada
                int firstColor = 0;

                Game game = gameManager.searchGame(chargeView.optionSelected());

                //Esto es codigo repetido con el charge game
                Map map = MapManager.llegeixMapa(game.getMap());
                MapManager mapManager = new MapManager(map);

                Player userPlayer = new Player(game.getColor());
                if (userPlayer.getColor().equals("RED")) {
                    firstColor++;
                }
                //Creem els npcs
                LinkedList<Character> npcs = gameManager.getNpcs(game.getPlayers() - game.getImpostors() - 1, game.getColor(), firstColor, colors, mapManager);
                firstColor = gameManager.getImpostorsStarterColor(gameManager.getUserColorPosition(game.getColor(), colors), npcs.size(), firstColor);
                LinkedList<Character> impostors = gameManager.getImpostors(game.getImpostors(), game.getColor(), firstColor + npcs.size(), colors, mapManager);

                LinkedList<Character> players = new LinkedList<>();
                players.addAll(npcs);
                players.addAll(impostors);
                Collections.shuffle(players);

                Cell initialCell = gameManager.getCafeCell(map.getCells());
                userPlayer.setCell(initialCell);
                gameManager.setInitialCell(userPlayer, players, map.getCells());

                for (Character character : players) {
                    gameManager.startPlayers(character);
                }

                //Creem els manegers que el controlen
                PlayerManager playerManager = new PlayerManager(userPlayer);
                NpcManager npcManager = new NpcManager(players);
                for (Character character : players) {

                    character.setNpcManager(npcManager);
                }

                gameManager.setPlayerManager(playerManager);
                mapManager.setPlayerManager(playerManager);
                gameManager.setMapManager(mapManager);

                gameManager.setNpcManager(npcManager);
                //Creidem la vista del mapa

                gameView.setMap(map, userPlayer, players);
                gameController.startMapThread();

                mainView.showGame();
            }
        }
    }
}
