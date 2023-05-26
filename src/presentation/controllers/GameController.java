package presentation.controllers;

import business.GameManager;
import business.entities.Time;
import business.entities.character.Character;
import business.entities.character.Impostor;
import business.entities.character.Npc;
import business.entities.character.Player;
import presentation.views.GameView;
import presentation.views.LogsView;
import presentation.views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase servira para poder controlar la vista de la partida
 */
public class GameController implements Runnable,ActionListener{

    private final Time totalTime =  new Time();
    private final Time checkTime =  new Time();
    private final GameView gameView;
    private final MainView mainView;
    private boolean isRunning;
    private final GameManager gameManager;

    private Boolean revealMap = false;
    private LogController logController;
    private LogsView logsView;
    private Thread T;

    //#nuevo
    private boolean stateCheck;

    /**
     * Este metodo sera el constructor de la clase
     * @param gameView la vita de la partida
     * @param mainView al vista principal de nuestra partida
     * @param gameManager el gestor de las partidas
     */
    public GameController(GameView gameView, MainView mainView, GameManager gameManager) {
        this.gameView = gameView;
        this.mainView = mainView;
        this.gameManager = gameManager;
    }


    /**
     * Este metodo sera util para programar el comportamiento del codigo cuando se interactua con los componentes de la vista [botones, paneles de texto, ...].
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GameView.BTN_BACK -> mainView.showStart();
            case GameView.BTN_STI -> mainView.showSettings();
            case GameView.BTN_STP -> {
                //TODO: Aqui se haria la parte de guardar la partida
                getCheckTime().stopTimer();
                gameView.stateCheck(true);
                gameManager.interruptThreads();
                this.stopMapThread();
                mainView.showStart();
            }
            case GameView.BTN_RVL -> {
                if(revealMap){

                    revealMap = false;
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                }else{
                    revealMap = true;
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                }
            }
            case GameView.BTN_U -> {

                if (gameManager.getPlayerManager().checkUp()) {

                    int[] nextCell = gameManager.getPlayerManager().nextCell(1);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
            }

            case GameView.BTN_D -> {

                if (gameManager.getPlayerManager().checkDown()) {

                    int[] nextCell = gameManager.getPlayerManager().nextCell(3);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());

                }

            }
            case GameView.BTN_R -> {

                if (gameManager.getPlayerManager().checkRight()) {

                    int[] nextCell = gameManager.getPlayerManager().nextCell(2);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }

            }
            case GameView.BTN_L -> {
                if (gameManager.getPlayerManager().checkLeft()) {

                    int[] nextCell = gameManager.getPlayerManager().nextCell(0);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
            }

            case GameView.CHECK -> {
                String roomName = gameManager.getPlayerManager().getPlayer().getCell().getRoomName();
                if(roomName.equals("admin")){
                    boolean rightAnswer = false;
                    boolean gotAnswer = false;

                    LinkedList<String> colors = gameView.getCardPosition();

                    LinkedList<String> susColors = new LinkedList<>();
                    LinkedList<String> notSusColors = new LinkedList<>();
                    LinkedList<String> unknownColors = new LinkedList<>();

                    separateColors(colors, susColors, notSusColors, unknownColors);
                    if(unknownColors.size() > 0){
                        gotAnswer = true;
                    }else {
                        LinkedList<Character> players = gameManager.getNpcManager().getPlayers();
                        LinkedList<Npc> npcs = new LinkedList<>();
                        LinkedList<Impostor> impostors = new LinkedList<>();

                        for (Character character : players) {
                            if (character.getClass().getSimpleName().equals("Npc")) {
                                npcs.add((Npc) character);
                            } else if (character.getClass().getSimpleName().equals("Impostor")) {
                                impostors.add((Impostor) character);
                            }
                        }


                        for (Impostor impostor : impostors) {
                            if (!susColors.contains(impostor.getColor())) {
                                gotAnswer = true;
                                break;
                            }
                        }


                        for (Npc npc : npcs) {
                            if (!notSusColors.contains(npc.getColor())) {
                                gotAnswer = true;
                                break;
                            }
                        }

                    }

                    if(!gotAnswer){
                        rightAnswer = true;
                    }

                    if(rightAnswer){
                        finishGame(true);

                        gameView.setDeductionShowing(false);
                    }else {
                        //Bloquear el boton
                        getCheckTime().initCounter();
                        stateCheck = false;
                        gameView.stateCheck(false);
                    }

                }else{
                    System.out.println("Not on the wright room!");
                }
            }
        }
    }

    /**
     * Este metodo recibe una lista de strings con este formato ("COLOR - COLUMN")
     * @param colorsList lita de strings a separar
     * @param susColors lista de personajes sus
     * @param notSusColors lista de personajes not sus
     * @param unknownColors lista de personajes unknown
     */
    public static void separateColors(LinkedList<String> colorsList, LinkedList<String> susColors, LinkedList<String> notSusColors, LinkedList<String> unknownColors) {
        for (String color : colorsList) {
            String[] parts = color.split(" - ");
            String colorName = parts[0].trim();
            String tag = parts[1].replaceAll(" -.*", "").trim();

            // Add the color to the corresponding LinkedList based on the tag
            if (tag.equals("Sus")) {
                susColors.add(colorName);
            } else if (tag.equals("Not Sus")) {
                notSusColors.add(colorName);
            } else {
                unknownColors.add(colorName);
            }
        }
    }

    /**
     * Este metodo comprobara la habitación en la que se encuentra nuestro usuario
     * @param player el personaje del usuario
     * @param players el resto de presonajes
     */
    private void checkRoom(Player player, LinkedList<Character> players) {
        ArrayList<String> colors = new ArrayList<>();
        for (Character character : players) {
            colors.add(character.getColor());
        }
        if(!gameView.getDeductionShowing()) {

            gameView.updateDeductionPanel(colors);

        }

        if ("security".equals(player.getCell().getRoomName())) {
            logsView = new LogsView(logController.getLogs());
        }
    }

    public void startMapThread() {
        isRunning = true;
        T = new Thread(this);
        T.start();
    }

    /**
     * Este metodo sirve para poder parar el thread del mapa
     */
    //#nuevo
    public void stopMapThread() {
        isRunning = false;
        T.interrupt();
        gameView.setDeductionShowing(false);

    }

    /**
     * Getter del tiempo desde el inicio de la partida
     * @return el tiempo total
     */
    public Time getTotalTime() {
        return totalTime;
    }


    //#nuevo
    public Time getCheckTime(){
        return checkTime;
    }

    /**
     * Funcion que termina el juego en victoria o derrota en función del parametro que le pasemos
     * @param win valor booleano que indica si ha ganado o perdido
     */
    //#nuevo
    public void finishGame(Boolean win){
        gameManager.interruptThreads();
        this.stopMapThread();
        gameView.stateCheck(true);
        gameManager.finishGame(win);
        gameManager.setStatistics();
        if(win){
            gameView.playerWinMsg();
        }else{
            gameView.impostorsWinMsg();
        }
        getTotalTime().stopTimer();
        mainView.showStart();
    }
    /**
     * Este metodo nos servira para poder lanzar el thread asociado con el game controller
     */
    public void run() {
        stateCheck = false;
        logController = new LogController(gameManager.getNpcManager());
        getTotalTime().initCounter();
        getCheckTime().setSeconds(0);
        while(isRunning) {
            System.out.println("el check time es: "+getCheckTime().getSeconds());
            if(getCheckTime().getSeconds() == 60 && !stateCheck){
                getCheckTime().stopTimer();
                stateCheck = true;
                gameView.stateCheck(true);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                if (gameManager.checkImpostorsWin()) {
                    finishGame(false);
                }

            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("El mapa peta pero no nos afecta");
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    gameView.updateMapView(gameManager.getmapManager().getMap(), gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers(), revealMap);
                    logController.updateLogs();
                }
            });

        }
    }

}
