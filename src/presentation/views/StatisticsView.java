package presentation.views;

import presentation.views.custom.BackGroundPanel;
import presentation.views.custom.StatisticPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * sera la clase para construir la vista de las estadisticas de nuestro programa
 */
public class StatisticsView extends JPanel {

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";


    private final JButton jbBack = new JButton("Back");
    private final JButton jbSettings = new JButton("Settings");


    private final float[] data = {};
    private StatisticPanel chartPanel = new StatisticPanel(new float[]{});
    BackGroundPanel fondo = new BackGroundPanel("files/images/background.jpg");

    /**
     * Este es el constructor de StatisticsManager
     */
    //La vista no puede hablar con el manager nunca
    public StatisticsView() {

        configureChargeView();
        //chargeExistingGames(new String[]{"A", "B", "C"});
    }

    /**
     * Este metodo sirve para generar la vista de StatisticsView
     */
    private void configureChargeView() {


        fondo.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());
        this.add(fondo);

        JPanel auxPanel = new JPanel();
        auxPanel.setLayout(new BorderLayout());
        auxPanel.setOpaque(false);

        JPanel jpActions = new JPanel();
        jpActions.setLayout(new BoxLayout(jpActions, BoxLayout.X_AXIS));

        jbBack.setActionCommand(BTN_BACK);
        jpActions.add(jbBack);

        // Espai buit per emputjar el segon botó a la dreta
        jpActions.add(Box.createHorizontalGlue());

        jbSettings.setActionCommand(BTN_STI);
        jpActions.add(jbSettings);
        jpActions.setOpaque(false);
        fondo.add(jpActions, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("Charge game");
        jlTitle.setForeground(Color.WHITE);
        jlTitle.setHorizontalAlignment(0);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        titlePanel.setOpaque(false);
        auxPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        voidPanel.setOpaque(false);
        fondo.add(voidPanel, BorderLayout.EAST);
        fondo.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de cargar partida
        JLabel jlCharge = new JLabel("Game name");
        jlCharge.setForeground(Color.WHITE);
        jlCharge.setHorizontalAlignment(0);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));
        infoPanel.setOpaque(false);
        infoPanel.add(jlCharge);

        chartPanel = new StatisticPanel(data);
        chartPanel.setPreferredSize(new Dimension(220, 120));
        fondo.add(chartPanel, BorderLayout.CENTER);

    }

    /**
     * sirve para asociar los botones con sus action listeners
     * @param listener accion listener para asociar los botones
     */
    public void statisticsController(ActionListener listener) {
        jbBack.addActionListener(listener);
        jbSettings.addActionListener(listener);
    }

    /**
     * Sirve para actualizar los datos de las estadisticas
     * @param dirtyData
     */
    public void updateData(ArrayList<Float> dirtyData){
        float[] data = new float[dirtyData.size()];
        for (int i = 0; i < dirtyData.size(); i++) {
            data[i] = dirtyData.get(i);
        }

        float[] dataCopy = Arrays.copyOf(data, data.length);

        chartPanel.updateData(dataCopy);
        chartPanel.revalidate();
        chartPanel.repaint();
        chartPanel.setPreferredSize(new Dimension(220, 120));
    }


}
