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
public class Rectangle implements Shape{

    int x1,y1,x2,y2;
    public Rectangle(int x1,int y1,int x2,int y2)
    {
        this.x1=Math.min(x1, x2);
        this.y1=Math.min(y1,y2);
        this.x2=Math.max(x1,x2);
        this.y2=Math.max(y1, y2);
    }
    @Override
    public void draw(Graphics g) {
        g.drawRect(x1, y1, x2-x1, y2-y1);
    }

    @Override
    public void move(int x, int y) {
        x1=x1+x;
        x2=x2+x;
        y1=y1+y;
        y2=y2+y;
    }

    @Override
    public void resize(int x, int y) {
          // Adjust the width and height based on the direction of resizing
    
    
    int srednia =x+y/2;
    
    x1=x1-srednia;
    x2=x2+srednia;
    y1=y1-srednia;
    y2=y2+srednia;
 
    }

    @Override
    public boolean position(int x, int y) {
        return ((x==x1 || x==x2) && (y<=y2 && y>=y1)) || ((y==y1 || y==y2) && (x>=x1 && x<=x2));
           
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
