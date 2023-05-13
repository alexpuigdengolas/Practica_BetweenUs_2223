package presentation.views;

import presentation.controllers.NGController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class PintaHab extends JPanel {
    private final Color color;
    private String roomName;
    private Color colori;
    private LinkedList<String> colors;
    private final NGController NgController = new NGController();
    private final Boolean userIsHere;
    private boolean revealMap;



    public PintaHab(Color color, String roomName, LinkedList<String> colors, Boolean userIsHere, Boolean revealMap){
        this.color = color;
        this.roomName = roomName;
        this.colors = colors;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());



            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

            for (int i = 0; i < colors.size(); i++) {
                if (colors.get(i).equals("PURPLE") || colors.get(i).equals("BROWN") || colors.get(i).equals("CYAN") || colors.get(i).equals("LIME")) {
                    int[] components = NgController.getColorComponents(colors.get(i));
                    colori = new Color(components[0], components[1], components[2]);
                } else {
                    try {
                        colori = (Color) Color.class.getField(colors.get(i)).get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                if (colors.get(i).equals("BLACK")) {
                    g.setColor(Color.WHITE);
                    g.fillOval(separadorX - 1, separadorY - 1, 17, 17);
                } else {
                    g.setColor(Color.BLACK);
                    g.fillOval(separadorX - 1, separadorY - 1, 17, 17);
                }

                g.setColor(colori);
                g.fillOval(separadorX, separadorY, 15, 15);


                separadorX += 20;

                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
            }

        }
    }
