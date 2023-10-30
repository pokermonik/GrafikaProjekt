/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Color;

/**
 *
 * @author poker
 */
public class ColorConverter {
    
    
    private Color color;
    
    private float black;
    private float cyan;
    private float magenta;
    private float yellow;
    
    private int red;
    private int green;
    private int blue;
    
    private boolean RGB;
    
    public ColorConverter()
    {
        
    }
    
    
    public void convertRGBtoCMYK() {
    float redTemp = red / 255f;
    float greenTemp = green / 255f;
    float blueTemp = blue / 255f;

    black = 1 - Math.max(Math.max(redTemp, greenTemp), blueTemp);

    if (black == 1.0) {
        cyan = 0;
        magenta = 0;
        yellow = 0;
    } else {
        cyan = (1 - redTemp - black) / (1 - black);
        magenta = (1 - greenTemp - black) / (1 - black);
        yellow = (1 - blueTemp - black) / (1 - black);
    }

    black *= 100;
    cyan *= 100;
    magenta *= 100;
    yellow *= 100;
}


    


    public void convertCMYKtoRGB() {
        red = (int) (255 - ((cyan * (255 - black) / 100) + black));
        green = (int) (255 - ((magenta * (255 - black) / 100) + black));
        blue = (int) (255 - ((yellow * (255 - black) / 100) + black));
    }
    public Color getColor()
    {
        return color;
    }
    public void setColor(Color color)
    {
        this.color=color;
    }
    public void setGreen(int value)
    {
        this.green=value;
    }
    public void setBlue(int value)
    {
        this.blue=value;
    }
    public void setRed(int value)
    {
        this.red=value;
    }
        public void setMagenta(int value)
    {
        this.magenta=value;
    }
    public void setCyan(int value)
    {
        this.cyan=value;
    }
    public void setYellow(int value)
    {
        this.yellow=value;
    }
    public void setBlack(int value)
    {
        this.black=value;
    }
    public int getGreen()
    {
        return green;
    }
    public int getBlue()
    {
        return blue;
    }
    public int getRed()
    {
        return red;
    }
    public float getMagenta()
    {
        return magenta;
    }
    public float getCyan()
    {
        return cyan;
    }
    public float getYellow()
    {
        return yellow;
    }
    public float getBlack()
    {
        return black;
    }
    
}
