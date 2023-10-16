/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author poker
 */
public abstract class Shape implements Serializable {
    
    public abstract void draw(Graphics g);
    public abstract void move(int x, int y);
    public abstract void resize(int x,int y);
    public abstract boolean position(int x,int y);
    public abstract int getX1();
    public abstract int getY1();
    public abstract int getX2();
    public abstract int getY2();
    
}
