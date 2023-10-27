/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author poker
 */
public class ZoomImagePanel extends JPanel {
    private BufferedImage image;
    private double zoomLevel = 1.0;
    private Point imagePosition = new Point(0, 0);
    private Point dragStart;

    public ZoomImagePanel(BufferedImage image) {
        this.image = image;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragStart = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                dragStart = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragStart != null) {
                    int dx = e.getPoint().x - dragStart.x;
                    int dy = e.getPoint().y - dragStart.y;
                    imagePosition.translate(dx, dy);
                    repaint();
                    dragStart = e.getPoint();
                }
            }
        });
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = zoomLevel;
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        imagePosition.setLocation(0, 0); // Reset the position when changing the image.
        repaint();
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;

            double imageWidth = image.getWidth() * zoomLevel;
            double imageHeight = image.getHeight() * zoomLevel;

            // Calculate the position to center the image
            double x = (getWidth() - imageWidth) / 2 + imagePosition.getX();
            double y = (getHeight() - imageHeight) / 2 + imagePosition.getY();

            // Apply zoom transformation
            g2d.drawImage(image, (int) x, (int) y, (int) imageWidth, (int) imageHeight, null);
        }
    }
}
