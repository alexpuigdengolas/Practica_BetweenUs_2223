package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NewGameView extends JPanel {


    public static final String BTN_P = "BTN_P";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private JButton jbProva = new JButton("Prova");
    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();

    private JTextField nameSpace = new JTextField();


    private MainView mainView;

    private CardLayout components;




    public NewGameView(){
        configureNGView();
    }

    private void configureNGView(){

        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        JPanel jpActions = new JPanel();
        jbBack.setActionCommand(BTN_BACK);
        jbSettings.setActionCommand(BTN_STI);
        jpActions.add(jbBack);
        jpActions.add(jbSettings);
        this.add(jpActions, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("NEW GAME");
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        //this.add(titlePanel, BorderLayout.NORTH);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        this.add(voidPanel, BorderLayout.EAST);
        this.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de Login
        JLabel jlGame = new JLabel("GAME NAME");
        jlGame.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlGame.setFont(new Font("Arial", Font.PLAIN, 20));
        //TODO: Cambiar el color de la vista
        infoPanel.add(jlGame);

        //TextField de New Game
        nameSpace.setMaximumSize(new Dimension(500, nameSpace.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(nameSpace);
        //infoPanel.add(Box.createVerticalStrut(10)); // Add vertical space


        JPanel actionPanel= new JPanel();

        jbProva.setActionCommand(BTN_P);
        actionPanel.add(jbProva);

        this.add(actionPanel, BorderLayout.SOUTH);
    }

    public void NGController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbProva.addActionListener(actionListener);
    }


}
