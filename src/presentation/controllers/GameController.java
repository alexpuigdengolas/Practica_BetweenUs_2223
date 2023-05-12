package presentation.controllers;

import business.GameManager;
import business.PlayerManager;
import business.entities.character.Player;
import presentation.views.GameView;
import presentation.views.MainView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {

    private GameView gameView;
    private MainView mainView;
    private CardLayout cardLayout;
    private GameManager gameManager;

    public GameController(GameView gameView, MainView mainView, CardLayout cardLayout, GameManager gameManager) {
        this.gameView = gameView;
        this.mainView = mainView;
        this.cardLayout = cardLayout;
        this.gameManager = gameManager;

    }

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
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer());

                }
            }
            case GameView.BTN_D -> {
                System.out.println("Clicko abajo");
                if (gameManager.getPlayerManager().checkDown()) {
                    System.out.println("pasa check down");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(3);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer());

                }
            }
            case GameView.BTN_R -> {
                System.out.println("Clicko right");
                if (gameManager.getPlayerManager().checkRight()) {
                    System.out.println("Pasa check right");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(2);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer());

                }
            }
            case GameView.BTN_L -> {
                System.out.println("Clicko left");
                //necesito traer aqui un player manager para controlar el movimiento del usuario
                if (gameManager.getPlayerManager().checkLeft()) {
                    System.out.println("pasa check left");
                    int[] nextCell = gameManager.getPlayerManager().nextCell(0);
                    gameManager.getPlayerManager().moveUserPlayer(gameManager.getmapManager().nextPlayerCell(nextCell));
                    gameView.updateMapView(gameManager.getmapManager().getMap(),gameManager.getPlayerManager().getPlayer());

                }
            }
        }
    }
}
