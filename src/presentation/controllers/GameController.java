package presentation.controllers;

import business.GameManager;
import business.entities.character.Character;
import business.entities.character.Player;
import presentation.views.GameView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class GameController extends Thread implements ActionListener {

    private GameView gameView;
    private MainView mainView;
    private boolean isRunning;
    private CardLayout cardLayout;
    private GameManager gameManager;

    public GameController(GameView gameView, MainView mainView, CardLayout cardLayout, GameManager gameManager) {
        this.gameView = gameView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;

    }
    public GameController(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case GameView.BTN_BACK -> mainView.showStart();
            case GameView.BTN_STI -> mainView.showSettings();
            case GameView.BTN_U -> {
                System.out.println("click arriba");
                if (gameManager.getPlayerManager().checkUp()) {
                    System.out.println("pasa check arriba");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(1);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers());

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
            }

            case GameView.BTN_D -> {
                System.out.println("Clicko abajo");
                if (gameManager.getPlayerManager().checkDown()) {
                    System.out.println("pasa check down");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(3);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers());

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());

                }

            }
            case GameView.BTN_R -> {
                System.out.println("Clicko right");
                if (gameManager.getPlayerManager().checkRight()) {
                    System.out.println("Pasa check right");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(2);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers());

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }

            }
            case GameView.BTN_L -> {
                System.out.println("Clicko left");
                //necesito traer aqui un player manager para controlar el movimiento del usuario
                if (gameManager.getPlayerManager().checkLeft()) {
                    System.out.println("pasa check left");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(0);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers());

                    checkRoom(gameManager.getPlayerManager().getPlayer(), gameManager.getNpcManager().getPlayers());
                }
            }
        }
    }

    private void checkRoom(Player player, LinkedList<Character> players) {
        switch (player.getCell().getRoomName()){
            case "admin":
                ArrayList<String> colors = new ArrayList<>();
                for (Character character : players) {
                    colors.add(character.getColor());
                }
                gameView.showDeductions(colors);
                break;
            case "cafeteria":
                gameView.showDefaultTask();
                //TODO: Check DEDUCTIONS
                break;
            case "security":
                gameView.showDefaultTask();
                //TODO: Show LOG
                break;
            case "corridor":
                gameView.showDefaultTask();
                break;
            default:
                gameView.showDefaultTask();
                break;
        }
    }

    public void startMapThread() {
        isRunning = true;
        this.start();
    }

    public void run() {
        while(isRunning) {
            try {

                TimeUnit.MILLISECONDS.sleep(500);

                gameView.updateMapView(gameManager.getmapManager().getMap(), gameManager.getPlayerManager().getPlayer(),gameManager.getNpcManager().getPlayers());



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
