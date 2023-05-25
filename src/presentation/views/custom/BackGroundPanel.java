package presentation.views.custom;

import javax.swing.*;
import java.awt.*;

public class BackGroundPanel extends JPanel {

    private final Image image;

    public BackGroundPanel(String path) {
        image = new ImageIcon(path).getImage();
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0 , getWidth(), getHeight(), this);

        super.paintComponent(g);

    }
}

