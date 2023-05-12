package presentation.views;

import javax.swing.*;
import java.awt.*;




public class PintaHab extends JPanel {
    private final Color color;
    private String roomName;
    private Color colori;
    private String playerColor;
    private final Boolean userIsHere;
    private boolean revealMap;



    public PintaHab(Color color, String roomName, String playerColor, Boolean userIsHere, Boolean revealMap){
        this.color = color;
        this.roomName = roomName;
        this.playerColor = playerColor;
        this.userIsHere = userIsHere;
        this.revealMap = revealMap;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (userIsHere) {


            int separadorX = getWidth() / 4;
            int separadorY = getHeight() / 3;

            try {
                colori = (Color) Color.class.getField(playerColor).get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
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
