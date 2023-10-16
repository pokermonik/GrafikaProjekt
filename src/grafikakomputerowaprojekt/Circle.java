/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Graphics;

/**
 *
 * @author poker
 */
public class Circle extends Shape {

    int x1,y1,x2,y2;
    public Circle(int x1,int y1,int x2,int y2)
    {
        this.x1=Math.min(x1, x2);
        this.y1=Math.min(y1,y2);
        this.x2=Math.max(x1,x2);
        this.y2=Math.max(y1, y2);
    }
    @Override
    public void draw(Graphics g) {
        g.drawOval(x1, y1, x2-x1, y2-y1);
    }

    @Override
    public void move(int x, int y) {
        x1=x1+x;
        x2=x2+x;
        y1=y1+y;
        y2=y2+y;
    }

    @Override
    public void resize(int x,int y) {
       // Calculate the new values of a and b
    int a = Math.abs(x2 - x1) / 2; // Current semi-major axis
    int b = Math.abs(y2 - y1) / 2; // Current semi-minor axis

    // Calculate the new width and height
    int newWidth = Math.abs(x2 - x1) + x;
    int newHeight = Math.abs(y2 - y1) + y;

    // Ensure that both the new width and height are greater than 0 to avoid issues
    if (newWidth > 0 && newHeight > 0) {
        // Calculate the new values of a and b based on the new width and height
        a = newWidth / 2;
        b = newHeight / 2;
        
        System.out.println(x1+"  "+x2+"  "+y1+"   "+ y2+"   "+ newWidth+"   "+ newHeight+"  ");
        if(x2>x1)
        {
               x2 = x1 + newWidth;
        y2 = y1 + newHeight;
        }
        else
        {
            x1=x2+newWidth;
            y1=y2+newHeight;
        }
     
    }
    
    }

    @Override
    public boolean position(int x, int y) {

int centerX = (x1 + x2) / 2;
    int centerY = (y1 + y2) / 2;
    int a = Math.abs(x2 - x1) / 2; // Semi-major axis for the ellipse
    int b = Math.abs(y2 - y1) / 2; // Semi-minor axis for the ellipse

    double dx = x - centerX;
    double dy = y - centerY;

    // Calculate the distance from the click point to the closest point on the border
    double distanceToBorder = (dx * dx) / (a * a) + (dy * dy) / (b * b);

    return distanceToBorder >= 0.9 && distanceToBorder <= 1.1; // Adjust the range as needed

    }
    
       @Override
    public int getX1() {
        return x1;
    }

    @Override
    public int getY1() {
        return y1;
    }
    

    @Override
    public int getX2() {
        return x2;
    }

    @Override
    public int getY2() {
        return y2;
    }
    
}
