package grafikakomputerowaprojekt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JPanel;



public class HistogramChart extends JPanel {
    private int[] data;  // Array to store histogram data

    public HistogramChart(int[] data) {
        this.data = data;
    }

    public HistogramChart() {
    }

    public void setData(int[] data) {
        this.data = data;
        repaint();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g); 
    if (data != null && data.length > 0) {

        Graphics2D g2 = (Graphics2D) g;
        int padding = 60; 
        int labelPadding = padding + 40; 

        int width = getWidth() - (2 * padding);
        int height = getHeight() - (2 * labelPadding);

        int barWidth = Math.max(width / data.length, 1);
        int maxCount = Arrays.stream(data).max().orElse(0);

   
        for (int i = 0; i < data.length; i++) {
            int barHeight = (int) ((double) data[i] / maxCount * height);
            int x = i * barWidth + padding;
            int y = height + labelPadding - barHeight;

       
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, barWidth, barHeight);
        }

    
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        int axisY = height + labelPadding;
        g2.drawLine(padding, axisY, padding, padding);
        g2.drawLine(padding, axisY, width + padding, axisY);

        g2.drawString("Count", padding / 2, padding / 2);
        g2.drawString("Pixel value", 52+width + padding - g2.getFontMetrics().stringWidth("Pixel value"), axisY + g2.getFontMetrics().getHeight());
        
    
        int numXTicks = 10; 
        for (int i = 0; i <= numXTicks; i++) {
            int x0 = padding + (i * (width / numXTicks));
            int y0 = axisY;
            int y1 = axisY + 10;
            g2.drawLine(x0, y0, x0, y1);
            String tickLabel = String.valueOf((int) (i * (255 / numXTicks)));
            int labelWidth = g2.getFontMetrics().stringWidth(tickLabel);
            g2.drawString(tickLabel, x0 - labelWidth / 2, y1 + 20); 
        }


        int numYTicks = 10; 
        for (int i = 0; i <= numYTicks; i++) {
            int x0 = padding;
            int y0 = height - (i * height / numYTicks) + labelPadding;
            g2.drawLine(x0 - 10, y0, x0, y0);
            String tickLabel = String.valueOf((int) ((maxCount * (i / (double) numYTicks))));
            g2.drawString(tickLabel, x0 - g2.getFontMetrics().stringWidth(tickLabel) - 15, y0 + (g2.getFontMetrics().getHeight() / 2) - 3);
        }
    }
}


}


