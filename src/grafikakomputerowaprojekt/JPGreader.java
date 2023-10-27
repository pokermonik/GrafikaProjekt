/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author poker
 */
public class JPGreader {
    
private BufferedImage image;

    public JPGreader(String filePath) {
        try {
            // Load the image from the specified file path
            File file = new File(filePath);
            image = ImageIO.read(file);

            if (image == null) {
                System.err.println("Failed to load the image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public JPGreader(File file) throws IOException {
            image = ImageIO.read(file);

            if (image == null) {
                System.err.println("Failed to load the image.");
            }
       
        }
    

    public BufferedImage getImage() {
        return image;
    }
    
    public void displayImage(Image img)
    {
        ImageDisplay display = new ImageDisplay(image);
        
        display.setVisible(true);
    }

    public int getWidth() {
        return image != null ? image.getWidth() : 0;
    }

    public int getHeight() {
        return image != null ? image.getHeight() : 0;
    }
}
