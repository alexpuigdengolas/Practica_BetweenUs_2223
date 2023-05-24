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
import presentation.views.custom.DeductionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GameController implements Runnable,ActionListener, KeyListener {

    private Time totalTime =  new Time();
    private GameView gameView;
    private MainView mainView;
    private boolean isRunning;
    private CardLayout cardLayout;
    private GameManager gameManager;

    private Boolean revealMap = false;
    private LogController logController;
    private LogsView logsView;
    private Thread T;

    public GameController(GameView gameView, MainView mainView, CardLayout cardLayout, GameManager gameManager) {
        this.gameView = gameView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP: // Flecha hacia arriba
                System.out.println("click arriba");
                if (gameManager.getPlayerManager().checkUp()) {
                    System.out.println("pasa check arriba");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(1);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
                break;
            case KeyEvent.VK_DOWN: // Flecha hacia abajo
                System.out.println("Clicko abajo");
                if (gameManager.getPlayerManager().checkDown()) {
                    System.out.println("pasa check down");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(3);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());

                }
                break;
            case KeyEvent.VK_RIGHT: // Flecha hacia la derecha
                System.out.println("Clicko right");
                if (gameManager.getPlayerManager().checkRight()) {
                    System.out.println("Pasa check right");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(2);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
                break;
            case KeyEvent.VK_LEFT: // Flecha hacia la izquierda
                System.out.println("Clicko left");
                //necesito traer aqui un player manager para controlar el movimiento del usuario
                if (gameManager.getPlayerManager().checkLeft()) {
                    System.out.println("pasa check left");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(0);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers(),revealMap);

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GameView.BTN_BACK -> mainView.showStart();
            case GameView.BTN_STI -> mainView.showSettings();
            case GameView.BTN_STP -> {
                //TODO:Aqui se haria la parte de guardar la partida
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


                        for(int j = 0; j < impostors.size(); j++) {
                            if (!susColors.contains(impostors.get(j).getColor())){
                                gotAnswer = true;
                                break;
                            }
                        }


                        for(int j = 0; j < npcs.size(); j++) {
                            if (!notSusColors.contains(npcs.get(j).getColor())){
                                gotAnswer = true;
                                break;
                            }
                        }

                    }

                    if(!gotAnswer){
                        rightAnswer = true;
                    }

                    if(rightAnswer){
                        System.out.println("Ganas la partida");
                    }else{
                        System.out.println("Cagaste");
                    }


                }else{
                    System.out.println("Not on the wright room!");
                }
            }
        }
    }

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


    private void checkRoom(Player player, LinkedList<Character> players) {
        ArrayList<String> colors = new ArrayList<>();
        for (Character character : players) {
            colors.add(character.getColor());
        }
        if(!gameView.getDeductionShowing()) {
            //gameView.showDeductions(colors);
            gameView.updateDeductionPanel(colors);

        }

        switch (player.getCell().getRoomName()){
            case "security":
                logsView = new LogsView(logController.getLogs());
                break;
        }
    }

    public void startMapThread() {
        isRunning = true;
        T = new Thread(this);
        T.start();
    }

    //#nuevo
    public void stopMapThread() {
        isRunning = false;
        T.interrupt();

    }
    public Time getTotalTime() {
        return totalTime;
    }


    public void run() {
        logController = new LogController(gameManager.getNpcManager(),logsView);
        getTotalTime().initCounter();
        while(isRunning) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                if (gameManager.checkImpostorsWin()) {
                    gameManager.interruptThreads();
                    this.stopMapThread();
                    gameManager.finishGame(false);
                    gameManager.setStatistics();
                    gameView.impostorsWinMsg();
                    //Mensaje de impostores ganan
                    mainView.showStart();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
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
