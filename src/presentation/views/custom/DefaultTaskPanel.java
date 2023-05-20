package presentation.views.custom;

import javax.swing.*;
import java.awt.*;

public class DefaultTaskPanel extends JPanel {

    public DefaultTaskPanel(){
        configureView();
    }

    private void configureView(){
        this.setLayout(new BorderLayout());
        this.add(new JLabel("No task in this room"), BorderLayout.CENTER);
    }
}
