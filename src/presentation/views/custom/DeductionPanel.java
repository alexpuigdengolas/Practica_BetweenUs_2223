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
        unknownColumn = new JPanel(new GridLayout(colorNames.size(), 1));
        susColumn = new JPanel(new GridLayout(colorNames.size(), 1));
        notSusColumn = new JPanel(new GridLayout(colorNames.size(), 1));

        for (String colorName : colorNames) {
            CardPanel cardPanel = new CardPanel(colorName);
            unknownColumn.add(cardPanel);
        }

        setLayout(new GridLayout(1, 3));
        addColumn("Unknown", unknownColumn);
        addColumn("Sus", susColumn);
        addColumn("Not Sus", notSusColumn);
    }

    private void addColumn(String columnName, JPanel columnPanel) {
        JPanel columnWrapper = new JPanel(new BorderLayout());
        columnWrapper.setBorder(BorderFactory.createTitledBorder(columnName));

        columnWrapper.add(columnPanel, BorderLayout.CENTER);
        add(columnWrapper);
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
            //TODO: Añadir todos los colores
            return switch (colorName) {
                case "RED" -> Color.RED;
                case "GREEN" -> Color.GREEN;
                case "BLUE" -> Color.blue;
                case "YELLOW" -> Color.yellow;
                case "MAGENTA" -> Color.MAGENTA;
                case "ORANGE" -> Color.ORANGE;
                default -> Color.BLACK;
            };
        }
    }
}
