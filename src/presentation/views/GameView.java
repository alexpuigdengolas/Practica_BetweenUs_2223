package presentation.views;

import business.entities.character.Character;
import business.entities.map.Map;
import presentation.views.custom.DeductionPanel;
import presentation.views.custom.DefaultTaskPanel;
import presentation.views.custom.MapView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * sera la clase para construir la vista de game de nuestro programa
 */
public class GameView extends JPanel {

    public static final String BTN_L = "BTN_L";
    public static final String BTN_R = "BTN_R";
    public static final String BTN_U = "BTN_U";
    public static final String BTN_D = "BTN_D";
    public static final String BTN_BACK = "BTN_BACK";

    public static final String BTN_RVL = "BTN_RVL";

    public static final String BTN_STP = "BTN_STP";

    public static final String CHECK = "CHECK";

    private final MapView mapView;
    private JPanel jpTask = new JPanel();
    private final DeductionPanel deductionPanel = new DeductionPanel(new ArrayList<String>());

    private JButton showDeductionsButton;

    private final JButton jbU = new BasicArrowButton(BasicArrowButton.NORTH);
    private final JButton jbL = new BasicArrowButton(BasicArrowButton.WEST);
    private final JButton jbR = new BasicArrowButton(BasicArrowButton.EAST);
    private final JButton jbD = new BasicArrowButton(BasicArrowButton.SOUTH);

    private final JButton jbReveal = new JButton("Reveal Map");

    private final JButton jbStop = new JButton("Stop Game");

    private final JButton jbCheck = new JButton("Check");

    private Boolean deductionShowing = false;


    /**
     * constructor de la clase GameView
     */
    public GameView() {

        // Add panels to jpTask
        jpTask.setLayout(new BorderLayout());

        // Add cardPanel to the GameView
        this.setLayout(new BorderLayout());

        configureView();

        // Create and add the MapView
        mapView = new MapView();
        this.add(mapView, BorderLayout.CENTER);
    }


    /**
     * este es el metodo que genera la vista entera de gameView
     */
    private void configureView(){
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());


        JPanel jpActions = new JPanel();
        jpActions.setLayout(new BoxLayout(jpActions, BoxLayout.X_AXIS));



        // Espai buit per emputjar el segon botó a la dreta
        jpActions.add(Box.createHorizontalGlue());


        jbReveal.setActionCommand(BTN_RVL);
        jpActions.add(jbReveal);

        jbStop.setActionCommand(BTN_STP);
        jpActions.add(jbStop);

        this.add(jpActions, BorderLayout.NORTH);

        JPanel jpAux = new JPanel();
        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BorderLayout());
        jbD.setActionCommand(BTN_D);
        //jbD.setPreferredSize(new Dimension(50, 75 ));
        jbL.setActionCommand(BTN_L);
        //jbL.setPreferredSize(new Dimension(50, 75));
        jbR.setActionCommand(BTN_R);
        //jbR.setPreferredSize(new Dimension(50, 75));
        jbU.setActionCommand(BTN_U);
        jbU.setPreferredSize(new Dimension(150, 75));
        jpButtons.add(jbU, BorderLayout.NORTH);
        jpButtons.add(jbL, BorderLayout.WEST);
        jpButtons.add(jbR, BorderLayout.EAST);
        jpButtons.add(jbD, BorderLayout.CENTER);
        jpButtons.setSize(new Dimension(420, 320));

        jpAux.add(jpButtons);
        this.add(jpAux, BorderLayout.WEST);

        jpTask = new DefaultTaskPanel();
        jpTask.setPreferredSize(new Dimension(420, 100));
        jpTask.add(deductionPanel, BorderLayout.CENTER);
        jpTask.add(jbCheck, BorderLayout.EAST);
        jbCheck.setActionCommand(CHECK);
        this.add(jpTask, BorderLayout.SOUTH);
    }

    /**
     * funcion que sirve para asociar los botones con sus action listeners
     * @param actionListener actionListener para asociar el resultado de los botones, para controlar si se pulsan
     */
    public void gameController(ActionListener actionListener){
        jbU.addActionListener(actionListener);
        jbL.addActionListener(actionListener);
        jbD.addActionListener(actionListener);
        jbR.addActionListener(actionListener);
        jbReveal.addActionListener(actionListener);
        jbCheck.addActionListener(actionListener);
        jbStop.addActionListener(actionListener);
        this.setFocusable(true);
        //this.addKeyListener((KeyListener) actionListener);
    }

    /**
     * esta funcion configura el mapa y los jugadores y los npcs correspondientes
     * @param map la funcion recibe el mapa
     * @param userPlayer la funcion recibe los jugadores
     * @param npcs recibe una lista de npcs
     */
    public void setMap(Map map, Character userPlayer, LinkedList<Character> npcs) {
        mapView.configureMapView(map, userPlayer, npcs);
        mapView.setSize(new Dimension(1500, 1500));
    }

    /**
     * esta funcion actualiza la vista del mapa y los personajes del juego con los nuevos datos proporcionados
     * @param map la funcion recibe el mapa
     * @param userPlayer la funcion recibe los jugadores
     * @param npcs recibe una lista de los npcs
     * @param revealMap es un booleano para revelar el mapa o no
     */
    public void updateMapView(Map map, Character userPlayer, LinkedList<Character> npcs, Boolean revealMap) {
        mapView.updateMapView(map, userPlayer, npcs, revealMap);
        mapView.setSize(new Dimension(1500, 1500));
    }

    /**
     * esta funcion se utiliza para mostrar la tarea predeterminada en la vista del juego
     */
    public void showDefaultTask(){
        CardLayout cardLayout = (CardLayout) jpTask.getLayout();
        cardLayout.show(jpTask, "defaultTask");
    }

    /**
     * es una funcion para crear y configurar un boton
     * @param colors esta funcion recibe una lista con los colores
     */
    public void comprovaBoto(ArrayList<String> colors){
        showDeductionsButton = new JButton("Show Deductions");
        showDeductionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeductions(colors);
            }
        });
        jpTask.add(showDeductionsButton, BorderLayout.CENTER);

    }

    /**
     * la funcion muestra las deducciones relacionadas con los colores en la vista del juego.
     * @param colors la funcion recibe una lista de colores
     */
    public void showDeductions(ArrayList<String> colors){
        //showDeductionsButton.setVisible(false);
        deductionPanel.setCardColors(colors);

        // Set CardLayout as the layout manager for jpTask
        jpTask.setLayout(new CardLayout());

        // Add deductionPanel to jpTask
        jpTask.add(deductionPanel, "deduction");

        // Show the deductionPanel using CardLayout
        CardLayout cardLayout = (CardLayout) jpTask.getLayout();
        cardLayout.show(jpTask, "deduction");

        jpTask.setPreferredSize(new Dimension(420, 100));
        this.add(jpTask, BorderLayout.SOUTH);
        this.repaint();

        deductionShowing = true;
    }

    /**
     * funcion para saber si deductionShowing vale true or false.
     * @return variable booleana para controlar si las deducciones se muestran por pantalla
     */
    public Boolean getDeductionShowing() {
        return deductionShowing;
    }

    /**
     * la funcion actualiza el panel de deducciones en la vista del juego con nuevos colores.
     * @param colors la funcion recibe una lista de colores
     */
    public void updateDeductionPanel(ArrayList<String> colors){
        deductionPanel.setCardColors(colors);
        deductionShowing = true;
    }

    /**
     * esta funcion sirve para establecer el estado de visualización de las deducciones en la interfaz gráfica del juego.
     * @param deductionShowing valdra true or false depeniendo de si las deducciones se muestran por pantalla
     */
    public void setDeductionShowing(boolean deductionShowing) {
        this.deductionShowing = deductionShowing;
    }

    /**
     *Mensaje pop up de que el impostor a ganado la partida
     */
    public void impostorsWinMsg() {
        JOptionPane.showMessageDialog(null, "Los impostores han ganado(hay el mismo numero de impostores que jugadores).\nHas de seguir practicando!", "Game end", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Pop up de que el jugador ha ganado la partida
     */
    //#nuevo
    public void playerWinMsg() {
        JOptionPane.showMessageDialog(null, "Felicidades has adivinado los roles de todos.\nHas Ganado la partida!", "Game end", JOptionPane.INFORMATION_MESSAGE);
    }

    //#nuevo
    /**
     * Este metodo mostrara por pantalla un pop up con un mensaje de error cuando el usuario no haya hecho una deducción correcta
     */
    public void playerNotGoodCheck(){
        JOptionPane.showMessageDialog(null, "La deducción no es correcta!\nComprueba que la información que has insertado sea correcta\nNo podrás hacer otra deducción hasta dentro de un minuto", "You made a mistake", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Funcion que activa o desactiva el boton en función de lo que le pasemos
     * @param state parametro que indica si activar o desactivar el botón
     */
    //#nuevo
    public void stateCheck(boolean state){
        jbCheck.setEnabled(state);
    }

    /**
     * funcion que devuelve la posicion de la tarjeta
     * @return las posiciones de la tarjeta
     */
    public LinkedList<String> getCardPosition() {
        return deductionPanel.getCardPositions();
    }

    //#nuevo
    /**
     * Este metodo mostrara un pop up que indicara que el usuario no esta haciendo el check donde se debe hacer y que esta fuera de la sala de admin
     */
    public void notRightRoom() {
        JOptionPane.showMessageDialog(null, "No puedes comprobar tu deducción en esta sala\nPara hacerlo debes estar en la sala de admin\n(La sala de color naranja)", "Not on the right room", JOptionPane.INFORMATION_MESSAGE);
    }
}
