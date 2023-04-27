package presentation.views;


import presentation.controllers.NGController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
/**
 * La classe ens permet pintar les habitacións del mapa al mapPaint.
 *
 * Envia la informació de les rooms al MapPaint per poder pintarles.
 */


public class PintaHab extends JPanel {
    private final Color color;

    public PintaHab(Color color){
        this.color = color;

    }

    /**
     * pinta les habitacions del mapa
     * @param g grafics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Pintamos el cuarto (Aqui se tendra que hacer mucha mas cosa para pintar los peresonajes que esten en este cuarto etc
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());


    }
}
