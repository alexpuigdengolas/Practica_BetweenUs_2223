package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeductionPanel extends JPanel {
    private JPanel unknownColumn;
    private JPanel susColumn;
    private JPanel notSusColumn;

    public DeductionPanel(ArrayList<String> colorNames) {
        // Initialize the columns
        unknownColumn = new JPanel(new GridLayout(colorNames.size(), 1));
        susColumn = new JPanel(new GridLayout(colorNames.size(), 1));
        notSusColumn = new JPanel(new GridLayout(colorNames.size(), 1));

        // Set the colors for the cards
        setCardColors(colorNames);

        // Create the columns panel
        JPanel columnsPanel = new JPanel(new GridLayout(1, 3));
        columnsPanel.add(addColumn("Unknown", unknownColumn));
        columnsPanel.add(addColumn("Sus", susColumn));
        columnsPanel.add(addColumn("Not Sus", notSusColumn));

        // Create the button
        JButton button = new JButton("Check");

        // Create the main panel with columns and button
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(columnsPanel, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.EAST);

        // Add the main panel to the DeductionPanel
        add(mainPanel);
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

        // Add cards with updated colors to the unknown column
        for (String colorName : colorNames) {
            CardPanel cardPanel = new CardPanel(colorName);
            unknownColumn.add(cardPanel);
        }

        // Repaint the columns
        unknownColumn.revalidate();
        unknownColumn.repaint();
        susColumn.revalidate();
        susColumn.repaint();
        notSusColumn.revalidate();
        notSusColumn.repaint();
    }

    public ArrayList<String> getCardPositions() {
        ArrayList<String> positions = new ArrayList<>();

        // Iterate over cards in the unknown column
        int unknownColumnCount = unknownColumn.getComponentCount();
        for (int i = 0; i < unknownColumnCount; i++) {
            CardPanel card = (CardPanel) unknownColumn.getComponent(i);
            positions.add(card.getColorName() + " - Unknown");
        }

        // Iterate over cards in the sus column
        int susColumnCount = susColumn.getComponentCount();
        for (int i = 0; i < susColumnCount; i++) {
            CardPanel card = (CardPanel) susColumn.getComponent(i);
            positions.add(card.getColorName() + " - Sus");
        }

        // Iterate over cards in the notSus column
        int notSusColumnCount = notSusColumn.getComponentCount();
        for (int i = 0; i < notSusColumnCount; i++) {
            CardPanel card = (CardPanel) notSusColumn.getComponent(i);
            positions.add(card.getColorName() + " - Not Sus");
        }

        return positions;
    }

    private class CardPanel extends JPanel {
        private String colorName;
        private JPanel currentColumn;

        public CardPanel(String colorName) {
            this.colorName = colorName;
            this.currentColumn = unknownColumn;

            setBackground(getColor(colorName));

            JButton moveLeftButton = new JButton("<");
            moveLeftButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moveCardToLeft();
                }
            });

            JButton moveRightButton = new JButton(">");
            moveRightButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    moveCardToRight();
                }
            });

            setLayout(new BorderLayout());
            add(moveLeftButton, BorderLayout.WEST);
            add(new JLabel(colorName, SwingConstants.CENTER), BorderLayout.CENTER);
            add(moveRightButton, BorderLayout.EAST);
        }

        private void moveCardToLeft() {
            if (currentColumn == susColumn) {
                susColumn.remove(this);
                unknownColumn.add(this);
                currentColumn = unknownColumn;
            } else if (currentColumn == notSusColumn) {
                notSusColumn.remove(this);
                susColumn.add(this);
                currentColumn = susColumn;
            }

            revalidate();
            repaint();
        }

        private void moveCardToRight() {
            if (currentColumn == unknownColumn) {
                unknownColumn.remove(this);
                susColumn.add(this);
                currentColumn = susColumn;
            } else if (currentColumn == susColumn) {
                susColumn.remove(this);
                notSusColumn.add(this);
                currentColumn = notSusColumn;
            }

            revalidate();
            repaint();
        }

        private Color getColor(String colorName) {
            // TODO: Add all the colors
            return switch (colorName) {
                case "RED" -> Color.RED;
                case "GREEN" -> Color.GREEN;
                case "BLUE" -> Color.BLUE;
                case "YELLOW" -> Color.YELLOW;
                case "MAGENTA" -> Color.MAGENTA;
                case "ORANGE" -> Color.ORANGE;
                default -> Color.BLACK;
            };
        }

        public String getColorName() {
            return colorName;
        }
    }
}
