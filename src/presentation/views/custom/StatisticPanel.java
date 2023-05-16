package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatisticPanel extends JPanel {

    private static final int CHART_WIDTH = 400;
    private static final int CHART_HEIGHT = 300;
    private static final int MARGIN = 40;
    private static final int TICK_LENGTH = 10;
    private static final int TICK_DISTANCE = 10;
    private float[] data;

    public StatisticPanel(float[] data) {
        this.data = data;
        setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int minValue = 0;
        int maxValue = 100;

        int xUnit = (CHART_WIDTH - 2 * MARGIN) / (data.length - 1);
        int yUnit = (CHART_HEIGHT - 2 * MARGIN) / (maxValue - minValue);

        g2d.setColor(Color.BLACK);

        // Draw x-axis
        g2d.drawLine(MARGIN, CHART_HEIGHT - MARGIN, CHART_WIDTH - MARGIN, CHART_HEIGHT - MARGIN);

        // Draw y-axis
        g2d.drawLine(MARGIN, CHART_HEIGHT - MARGIN, MARGIN, MARGIN);

        // Draw x-axis tick marks and labels
        int xTickSpacing = (CHART_WIDTH - 2 * MARGIN) / (data.length - 1);
        for (int i = 0; i < data.length; i++) {
            int x = MARGIN + i * xTickSpacing;
            int y = CHART_HEIGHT - MARGIN;
            g2d.drawLine(x, y, x, y + TICK_LENGTH);
            g2d.drawString(String.valueOf(i), x - 5, y + TICK_LENGTH + 15);
        }

        // Draw y-axis tick marks and labels
        int yTickSpacing = (CHART_HEIGHT - 2 * MARGIN) / (maxValue - minValue);
        for (int i = minValue; i <= maxValue; i += TICK_DISTANCE) {
            int x = MARGIN;
            int y = CHART_HEIGHT - MARGIN - (i - minValue) * yTickSpacing;
            g2d.drawLine(x, y, x - TICK_LENGTH, y);
            g2d.drawString(String.valueOf(i), x - TICK_LENGTH - 30, y + 5);
        }

        g2d.setColor(Color.RED);

        // Draw data points and lines
        for (int i = 0; i < data.length; i++) {
            int x = MARGIN + i * xUnit;
            float y = CHART_HEIGHT - MARGIN - (data[i] - minValue) * yUnit;

            g2d.fillOval(x - 2, (int) (y - 2), 4, 4);

            if (i < data.length - 1) {
                int x2 = MARGIN + (i + 1) * xUnit;
                float y2 = CHART_HEIGHT - MARGIN - (data[i + 1] - minValue) * yUnit;
                g2d.drawLine(x, (int)y, x2, (int)y2);
            }
        }
    }

}
