/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Graphics;
import java.awt.geom.Line2D;

/**
 *
 * @author poker
 */
public class Line implements Shape{

    int x1,x2,y1,y2;
    
    public Line(int x1,int y1,int x2,int y2)
    {
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }
    
    @Override
    public void draw(Graphics g) {
        
        g.drawLine(x1, y1, x2, y2);
        
        
        
        
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
        x1=x1+x;
        y1=y1+y;
    }

    @Override
    public boolean position(int x, int y) {
        return Line2D.ptSegDist(x1,y1,x2,y2, x, y)<=1;
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
