package presentation.views;


import business.entities.map.Mobility;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * La classe ens permet pintar els passadisos del mapa.
 *
 * interacciona directament amb la clase MapPaint.
 */

public class PintaPassadis extends JPanel{
    private final Mobility mov;
    private  String playerColor;
    private final Boolean userIsHere;
    private boolean revealMap;
    private Color colori;


    public PintaPassadis(Mobility mov,String playerColor, Boolean userIsHere, Boolean revealMap){
        this.mov = mov;
        this.playerColor = playerColor;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        if(userIsHere){ //miramos si esta el user en es cuarto y lo pintamos
            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

            try {
                colori = (Color) Color.class.getField(playerColor).get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }

            g.setColor(colori);
            g.fillOval(separadorX, separadorY, 15, 15);

        }





    }
}
