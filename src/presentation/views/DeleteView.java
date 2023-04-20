package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteView extends JPanel {
    public static final String BTN_CHA = "BTN_DEL";
    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_STI = "BTN_STI";

    private JTextField nameField = new JTextField();

    private JButton deleteButton = new JButton("Delete Game");
    private JButton jbBack = new JButton();
    private JButton jbSettings = new JButton();

    private CardLayout components;
    private MainView mainView;

    public DeleteView() {
        configureDeleteView();
    }

    private void configureDeleteView() {
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        this.setLayout(new BorderLayout());

        JPanel jpActions = new JPanel();
        jbBack.setActionCommand(BTN_BACK);
        jbSettings.setActionCommand(BTN_STI);
        jpActions.add(jbBack);
        jpActions.add(jbSettings);
        this.add(jpActions, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel();
        JLabel jlTitle = new JLabel("Delete game");
        jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        titlePanel.add(jlTitle);
        //this.add(titlePanel, BorderLayout.NORTH);

        JPanel voidPanel = new JPanel();
        voidPanel.setPreferredSize(new Dimension(50, 50));
        this.add(voidPanel, BorderLayout.EAST);
        this.add(voidPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //Label de cargar partida
        JLabel jlCharge = new JLabel("Game name");
        jlCharge.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jlCharge.setFont(new Font("Serif", Font.PLAIN, 20));
        infoPanel.add(jlCharge);


        //TextField de cargar partida
        nameField.setMaximumSize(new Dimension(500, nameField.getPreferredSize().height));
        infoPanel.add(Box.createHorizontalGlue()); // Add horizontal glue to align the text field to the center
        infoPanel.add(nameField);
        infoPanel.add(Box.createVerticalStrut(10));
        this.add(infoPanel);

        //Panel de botones
        JPanel buttonPanel = new JPanel();
        deleteButton.setActionCommand(BTN_CHA);
        buttonPanel.add(deleteButton);

        infoPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void deleteController(ActionListener actionListener){
        jbBack.addActionListener(actionListener);
        jbSettings.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
    }
}
