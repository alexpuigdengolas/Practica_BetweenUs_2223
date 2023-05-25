package presentation.views;

import presentation.controllers.NGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * sera la clase para construir la vista de pintar habitaciones de nuestro programa
 */
public class PintaHab extends JPanel {
    private final Color color;
    private final String roomName;
    private Color colori;
    private final LinkedList<String> colors;
    private final NGController NgController = new NGController();
    private final Boolean userIsHere;
    private final boolean revealMap;
    private final LinkedList<Boolean> corpses;
    private BufferedImage image;


    /**
     *
     * @param color
     * @param roomName
     * @param colors
     * @param userIsHere
     * @param revealMap
     * @param corpses
     */
    public PintaHab(Color color, String roomName, LinkedList<String> colors, Boolean userIsHere, Boolean revealMap, LinkedList<Boolean> corpses){
        this.color = color;
        this.roomName = roomName;
        this.colors = colors;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;
        this.corpses = corpses;
        try{
            File imageFile = new File("files/Images/XDeadBlack.png");
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(revealMap || userIsHere){


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


                if(corpses.get(i)) {


                    Image imageScaled = image.getScaledInstance(21, 21, Image.SCALE_DEFAULT);
                    g.drawImage(imageScaled, separadorX - 3, separadorY - 3, this);

                }else{
                    if (colors.get(i).equals("BLACK")) {
                        g.setColor(Color.WHITE);
                        g.fillOval(separadorX - 1, separadorY - 1, 17, 17);
                    } else {
                        g.setColor(Color.BLACK);
                        g.fillOval(separadorX - 1, separadorY - 1, 17, 17);
                    }
                    g.setColor(colori);
                    g.fillOval(separadorX, separadorY, 15, 15);
                }

                separadorX += 20;

                if (separadorX + 30 > getWidth()) {
                    separadorY = separadorY + 20;
                    separadorX = getWidth() / 4;
                }
            }
        } else {
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        }
    }
