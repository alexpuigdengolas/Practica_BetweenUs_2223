package presentation.views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel cards;


    public MainView() {
        //setTitle("Card Layout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the card layout panel
        cards = new JPanel(new CardLayout());

        // Add the card views to the card layout panel
        cards.add(new LoginView(), "LoginView");
        cards.add(new RegisterView(), "RegisterView");

        // Create a button panel to switch between cards
        JPanel buttonPanel = new JPanel();
        JButton button1 = new JButton("Show Card 1");
        JButton button2 = new JButton("Show Card 2");

        // Add action listeners to the buttons to switch between cards
        button1.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "LoginView");
        });

        button2.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, "RegisterView");
        });

        // Add the buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Add the card layout panel and the button panel to the frame
        getContentPane().add(cards, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

