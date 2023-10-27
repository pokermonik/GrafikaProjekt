/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author poker
 */
public class PPMreader {
    
    private File file;
    private BufferedImage image;
    public PPMreader()
    {
        
    }
    
    public PPMreader(File file)
    {
        this.file=file;
    }
    
    public BufferedImage readP3(String filename) throws IOException {
        try {
            if(file!=null)
            {
                filename=file.getName();
            }
            DataInputStream is = new DataInputStream(new FileInputStream(filename));
            byte[] header = new byte[2];
            is.read(header);

            String format = new String(header, "UTF-8");
            System.out.println(format);

            // Read comments and ignore them
            while (true) 
            {
                byte b = (byte) is.read();
                
    
                if (b == 32) 
                {
       
                    while (b ==10) 
                    {
                        b = (byte) is.read();                
                    }
                }
                else 
                {
            
                    break;
                }
               
            }
            
            // Read dimensions and max value
            
           
            int width = readNextInt(is);
            int height= readNextInt(is);
            int maxVal = readNextInt(is);
          
            System.out.println(width+" "+height+" "+maxVal);
            int changeVal = 255/maxVal;
            int r, g, b,r1,g1,b1,pixelValue;
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            if(maxVal>=256)
            {
                
                changeVal = 1;
            }
            if(format.equals("P6"))
            {
                //System.out.println(width+" "+height);
                for (int y = 0; y < height; y++) 
                {
                    for (int x = 0; x < width; x++) 
                        {
                            r = is.readUnsignedByte();
                            g = is.readUnsignedByte();
                            b = is.readUnsignedByte();
                            pixelValue = (255 << 24) | (r << 16) | (g << 8) | b;
                            image.setRGB(x, y, pixelValue);
                        }
                }
                //System.out.println("koniec");
            }
            else
            {
                for (int y = 0; y < height; y++)
                {
                    for (int x = 0; x < width; x++) 
                    {
                        r = readNextInt(is)/changeVal;
                        g = readNextInt(is)/changeVal;
                        b = readNextInt(is)/changeVal;
                        if(maxVal>=256)
                        {
                            r1 = readNextInt(is)/changeVal;
                            g1 = readNextInt(is)/changeVal;
                            b1 = readNextInt(is)/changeVal;

                            pixelValue = (255 << 24)  | ((r << 16) & (r1<<16)) | ((g << 8) & (g1<<8)) | (b & b1) ;
                        }
                        
                        else
                        {
                              pixelValue = (255 << 24) | (r << 16) | (g << 8) | b ;
                        }
                        image.setRGB(x, y, pixelValue);
                    }
                }
            }
            is.close();
            return image;
        } 
        catch (IOException e) 
        {
            System.err.println("Error loading PPM file: " + e.getMessage());
            return null;
        }
    }

    
    public void displayImage(BufferedImage image) {
        ImageDisplay display = new ImageDisplay(image);
        display.setVisible(true);
    }
    
    private int readNextInt(DataInputStream is) throws IOException {
        int value = 0;
        int ch;
        boolean digitFound = false;
        boolean comment = false;
        while ((ch = is.read()) != -1) 
        {
            
            
            char c = (char) ch;
           
            if(c=='#')
            {
                comment=true;
            }
            if(comment==true && c=='\n')
            {
                comment=false;
                
            }
             System.out.println(c+" "+ comment);
        
            if (Character.isDigit(c) &comment==false) 
            {
                value = value * 10 + Character.getNumericValue(c);
                
                digitFound = true;
            }
            else if (digitFound && (c == ' ' || c == '\n' || c == '\r'))
            {
                // Stop reading when a non-digit character is encountered
                break;
            }
        }
        return value;
    }
    
    public BufferedImage getImage()
    {
        return image;
    }

        
}
    

