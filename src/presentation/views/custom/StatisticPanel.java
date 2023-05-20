package presentation.views.custom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatisticPanel extends JPanel {
    private static final int CHART_WIDTH = 400;
    private static final int CHART_HEIGHT = 300;
    private static final int MARGIN = 40;
    private static final int TICK_LENGTH = 15;
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

        float minValue = Float.MAX_VALUE;
        float maxValue = Float.MIN_VALUE;
        for (float value : data) {
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // Ajustar los valores mínimo y máximo al rango deseado
        minValue = Math.min(minValue, 0);
        maxValue = Math.max(maxValue, 100);

        int xUnit = (CHART_WIDTH - 2 * MARGIN) / (data.length - 1);
        int yUnit = (CHART_HEIGHT - 2 * MARGIN) / (int) (maxValue - minValue);

        g2d.setColor(Color.BLACK);

        int chartCenterX = getWidth() / 2;
        int chartCenterY = getHeight() / 2;

        int chartStartX = chartCenterX - CHART_WIDTH / 2;
        int chartStartY = chartCenterY - CHART_HEIGHT / 2;

        String title = "Stadistics";
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int titleWidth = fontMetrics.stringWidth(title);
        int titleX = chartCenterX - titleWidth / 2;
        int titleY = chartStartY - MARGIN-100;
        g2d.drawString(title, titleX, titleY);



        g2d.drawLine(chartStartX, chartCenterY, chartStartX + CHART_WIDTH, chartCenterY);

        g2d.drawLine(chartStartX, chartCenterY, chartStartX, chartStartY);

        int xTickSpacing = (CHART_WIDTH - 2 * MARGIN) / (data.length - 1);
        for (int i = 0; i < data.length; i++) {
            int x = chartStartX + i * xTickSpacing;
            int y = chartCenterY;
            g2d.drawLine(x, y, x, y + TICK_LENGTH);
            g2d.drawString(String.valueOf(i), x - 5, y + TICK_LENGTH +15);
        }

        int yTickSpacing = (CHART_HEIGHT - 2 * MARGIN) / (int) (maxValue - minValue);
        for (int i = (int) minValue; i <= maxValue; i += TICK_DISTANCE) {
            int x = chartStartX;
            int y = chartCenterY - (i - (int) minValue) * yTickSpacing;
            g2d.drawLine(x, y, x - (TICK_LENGTH), y);
            g2d.drawString(String.valueOf(i), x - TICK_LENGTH - 30, y + 5);
        }

        g2d.setColor(Color.RED);

        for (int i = 0; i < data.length; i++) {
            int x = chartStartX + i * xUnit;
            float y = chartCenterY - (data[i] - minValue) * yUnit;

            g2d.fillOval(x - 2, (int) y - 2, 4, 4);

            if (i > 0) {
                int xPrev = chartStartX + (i - 1) * xUnit;
                float yPrev = chartCenterY - (data[i - 1] - minValue) * yUnit;
                g2d.drawLine(xPrev, (int) yPrev, x, (int) y);
            }
        }
    }


    public void updateData(float[] newData) {
        this.data = newData;
    }
}
