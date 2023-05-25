package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPanel extends JPanel {
    private final String colorName;
    private JPanel currentColumn;

    private final JPanel unknownColumn;
    private final JPanel susColumn;
    private final JPanel notSusColumn;

    public CardPanel(String colorName, JPanel unknownColumn, JPanel susColumn, JPanel notSusColumn) {
        this.unknownColumn = unknownColumn;
        this.susColumn = susColumn;
        this.notSusColumn = notSusColumn;

        this.colorName = colorName;
        this.currentColumn = this.unknownColumn;

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
        int[] components = new int[3];
        return switch (colorName) {
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            case "YELLOW" -> Color.YELLOW;
            case "MAGENTA" -> Color.MAGENTA;
            case "ORANGE" -> Color.ORANGE;
            case "PINK" -> Color.PINK;
            case "BLACK" -> Color.BLACK;
            case "PURPLE" -> {
                components[0] = 102;
                components[1] = 0;
                components[2] = 153;
                yield new Color(components[0], components[1], components[2]);
            }
            case "BROWN" -> {
                components[0] = 102;
                components[1] = 51;
                components[2] = 0;
                yield new Color(components[0], components[1], components[2]);
            }
            case "CYAN" -> Color.CYAN;
            case "LIME" -> {
                components[0] = 50;
                components[1] = 205;
                components[2] = 50;
                yield new Color(components[0], components[1], components[2]);
            }
            default -> Color.WHITE;
        };
    }

    public String getColorName() {
        return colorName;
    }
}
