package presentation.views;


import business.entities.map.Mobility;
import presentation.controllers.NGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * La classe ens permet pintar els passadisos del mapa.
 *
 * interacciona directament amb la clase MapPaint.
 */

public class PintaPassadis extends JPanel{
    private final Mobility mov;
    private final Boolean userIsHere;
    private LinkedList<String> colors;
    private NGController ngController = new NGController();
    private boolean revealMap;
    private Color colori;
    private LinkedList<Boolean> corpses;
    private BufferedImage image;


    /**
     *
     * @param mov
     * @param colors
     * @param userIsHere
     * @param revealMap
     * @param corpses
     */
    public PintaPassadis(Mobility mov,LinkedList<String> colors, Boolean userIsHere, Boolean revealMap,LinkedList<Boolean> corpses){
        this.mov = mov;
        this.colors = colors;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;
        this.corpses = corpses;
        try{
            File imageFile = new File("files/Images/XDead.png");
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

        if (userIsHere || revealMap) {

        //hay que hacer un if para cuando el user no esta
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,400);

        int alturaX = (getHeight()/5)*3;
        int ampladaX = (getWidth()/5)*3;
        int alturaY = (getWidth()/5)*3;
        int ampladaY = (getHeight()/5)*3;

        // sabre posicio o tamant dels quadrats.
        if(mov.getUp() == 1){
            g.setColor(Color.WHITE);
            g.fillRect((getWidth()/2) - ampladaY/2,0, ampladaY, ampladaY);
        }
        if(mov.getDown() == 1){
            g.setColor(Color.WHITE);
            g.fillRect((getWidth()/2) - ampladaY/2,(getHeight()/5)*2, ampladaY,alturaY);
        }
        if(mov.getLeft() == 1){
            g.setColor(Color.WHITE);
            g.fillRect(0,getHeight()/5,(getWidth()/2) - ampladaY/2 + ampladaY, alturaX);
        }
        if(mov.getRight() == 1){
            g.setColor(Color.WHITE);
            g.fillRect((getWidth()/2) - ampladaY/2,getHeight()/5,ampladaX + alturaX/2, alturaX);
        }

         //miramos si esta el user en es cuarto y lo pintamos
            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

            for (int i = 0; i < colors.size(); i++) {
                if (colors.get(i).equals("PURPLE") || colors.get(i).equals("BROWN") || colors.get(i).equals("CYAN") || colors.get(i).equals("LIME")) {
                    int[] components = ngController.getColorComponents(colors.get(i));
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
