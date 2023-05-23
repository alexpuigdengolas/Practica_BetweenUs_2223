package presentation.views;

import presentation.views.custom.MapView;

import javax.swing.*;
import java.awt.*;

/**
 * sera la clase para construir la vista main de nuestro programa
 */
public class MainView extends JFrame {

    static final int width = 1000;

    static final int height = 700;

    private final JPanel jpView = new JPanel();

    private CardLayout viewComponents;

    private RegisterView registerView;
    private LoginView loginView;
    private StartView startView;
    private NewGameView nGView;
    private StatisticsView statisticsView;
    private DeleteView deleteView;
    private ConfiguredView configuredView;
    private ChargeView chargeView;
    private SettingsView settingsView;
    private MapView mapView;
    private GameView gameView;


    /**
     *
     * @param viewComponents
     * @param registerView
     * @param loginView
     * @param startView
     * @param nGView
     * @param statisticsView
     * @param deleteView
     * @param configuredView
     * @param chargeView
     * @param settingsView
     * @param mapView
     * @param gameView
     */
    public MainView(CardLayout viewComponents, RegisterView registerView, LoginView loginView, StartView startView, NewGameView nGView, StatisticsView statisticsView, DeleteView deleteView, ConfiguredView configuredView, ChargeView chargeView, SettingsView settingsView, MapView mapView, GameView gameView) {

        this.viewComponents = viewComponents;
        this.setLayout(viewComponents);
        this.configureWindow();
        this.configureMainView();

        this.startView = startView;
        this.loginView = loginView;
        this.registerView = registerView;
        this.nGView = nGView;
        this.statisticsView = statisticsView;
        this.deleteView = deleteView;
        this.configuredView = configuredView;
        this.chargeView = chargeView;
        this.settingsView = settingsView;
        this.mapView = mapView;
        this.gameView = gameView;

        this.add(loginView, "loginView");
        this.add(registerView, "registerView");
        this.add(startView, "startView");
        this.add(nGView, "nGView");
        this.add(statisticsView, "statisticsView");
        this.add(deleteView, "deleteView");
        this.add(configuredView, "configuredView");
        this.add(chargeView, "chargeView");
        this.add(settingsView, "settingsView");
        this.add(mapView,"mapView");
        this.add(gameView, "gameView");
    }

    /**
     * Este metodo generara la vista inicial con todos los parametros deseados
     */
    private void configureMainView() {

        jpView.setBackground(Color.BLACK);

        this.add(jpView, "main");
        this.getViewComponent().show(this.getContentPane(), "main");
    }

    /**
     * Getter para devolver los componentes del card layout
     *
     * @return card layout
     */
    private CardLayout getViewComponent() {
        return viewComponents;
    }

    /**
     * Este metodo generara el nombre de la ventana en si, con el nombre del programa y algun parametro mas
     */
    private void configureWindow() {
        setTitle("Between Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(width, height);
    }

    /**
     * Este metodo servira para iniciar la vista de nuestro codigo
     */
    public void start() {
        setVisible(true);
        this.getViewComponent().show(this.getContentPane(), "loginView");
    }

    /**
     * Este metodo nos mostrara la pantalla de registro
     */
    public void showRegister() {
        this.getViewComponent().show(this.getContentPane(), "registerView");
    }

    /**
     * Este metodo nos mostrara la pantalla de inicio de sesion
     */
    public void showLogin() {
        this.getViewComponent().show(this.getContentPane(), "loginView");
    }

    public void showStart() {
        this.getViewComponent().show(this.getContentPane(), "startView");
    }

    public void showNG() {this.getViewComponent().show(this.getContentPane(), "nGView");}

    public void showStatistics(){this.getViewComponent().show(this.getContentPane(), "statisticsView");}

    public void showDelete(){this.getViewComponent().show(this.getContentPane(), "deleteView");}

    public void showConfigured(){
        this.getViewComponent().show(this.getContentPane(), "configuredView");
    }

    public void showCharge(){
        this.getViewComponent().show(this.getContentPane(), "chargeView");}

    public void showSettings(){this.getViewComponent().show(this.getContentPane(), "settingsView");}
    public void showGame(){this.getViewComponent().show(this.getContentPane(), "gameView");}


}

