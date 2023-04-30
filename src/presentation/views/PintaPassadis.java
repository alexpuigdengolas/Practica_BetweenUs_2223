package presentation.views;


import business.entities.map.Mobility;
import presentation.controllers.NGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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


    public PintaPassadis(Mobility mov){
        this.mov = mov;

    }

    /**
     * Pintem les passadisos del mapa
     * @param g grafics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        //Pintamos los pasillos en funcion de la movilidad para ver hacia donde van
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

    }
}
