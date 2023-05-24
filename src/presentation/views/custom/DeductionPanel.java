package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class DeductionPanel extends JPanel {
    private JPanel unknownColumn;
    private JPanel susColumn;
    private JPanel notSusColumn;



    public DeductionPanel(ArrayList<String> colorNames) {
        // Initialize the columns
        unknownColumn = new JPanel(new GridLayout(3, 3));
        susColumn = new JPanel(new GridLayout(3, 3));
        notSusColumn = new JPanel(new GridLayout(3, 3));

        // Set the colors for the cards
        setCardColors(colorNames);

        // Create the columns panel
        JPanel columnsPanel = new JPanel(new GridLayout(1, 3));
        columnsPanel.add(addColumn("Unknown", unknownColumn));
        columnsPanel.add(addColumn("Sus", susColumn));
        columnsPanel.add(addColumn("Not Sus", notSusColumn));

        // Create the main panel with columns and button
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(columnsPanel, BorderLayout.CENTER);

        // Add the main panel to the DeductionPanel
        setLayout(new BorderLayout());
        add(mainPanel);

        // Set the maximum size of the DeductionPanel
        setMaximumSize(getPreferredSize());
    }

    private JPanel addColumn(String columnName, JPanel columnPanel) {
        JPanel columnWrapper = new JPanel(new BorderLayout());
        columnWrapper.setBorder(BorderFactory.createTitledBorder(columnName));

        columnWrapper.add(columnPanel, BorderLayout.CENTER);
        add(columnWrapper);
        return columnWrapper;
    }

    public void setCardColors(ArrayList<String> colorNames) {
        int size = colorNames.size();

        // Clear existing cards from the columns
        unknownColumn.removeAll();
        susColumn.removeAll();
        notSusColumn.removeAll();

        // Calculate the number of cards to add to each column
        int maxCardsPerColumn = 9;
        int cardsPerColumn = Math.min(size, maxCardsPerColumn);
        int remainingCards = size - cardsPerColumn;

        // Add cards with updated colors to the unknown column
        for (int i = 0; i < cardsPerColumn; i++) {
            String colorName = colorNames.get(i);
            CardPanel cardPanel = new CardPanel(colorName, unknownColumn, susColumn, notSusColumn);
            unknownColumn.add(cardPanel);
        }

        // Add remaining cards to the sus column
        for (int i = cardsPerColumn; i < cardsPerColumn + remainingCards; i++) {
            String colorName = colorNames.get(i);
            CardPanel cardPanel = new CardPanel(colorName, unknownColumn, susColumn, notSusColumn);
            susColumn.add(cardPanel);
        }

        // Repaint the columns
        unknownColumn.revalidate();
        unknownColumn.repaint();
        susColumn.revalidate();
        susColumn.repaint();
        notSusColumn.revalidate();
        notSusColumn.repaint();
    }

    public LinkedList<String> getCardPositions() {
        LinkedList<String> positions = new LinkedList<>();

        // Iterate over cards in the unknown column
        for (Component component : unknownColumn.getComponents()) {
            if (component instanceof CardPanel) {
                CardPanel card = (CardPanel) component;
                String position = getCardPosition(card);
                positions.add(position);
            }
        }

        // Iterate over cards in the sus column
        for (Component component : susColumn.getComponents()) {
            if (component instanceof CardPanel) {
                CardPanel card = (CardPanel) component;
                String position = getCardPosition(card);
                positions.add(position);
            }
        }

        // Iterate over cards in the notSus column
        for (Component component : notSusColumn.getComponents()) {
            if (component instanceof CardPanel) {
                CardPanel card = (CardPanel) component;
                String position = getCardPosition(card);
                positions.add(position);
            }
        }

        return positions;
    }

    private String getCardPosition(CardPanel card) {
        String column = getColumnFromParent(card.getParent());
        String position = card.getColorName() + " - " + column;
        return position;
    }

    private String getColumnFromParent(Component parent) {
        if (parent == unknownColumn) {
            return "Unknown";
        } else if (parent == susColumn) {
            return "Sus";
        } else if (parent == notSusColumn) {
            return "Not Sus";
        } else {
            return "";
        }
    }


}
