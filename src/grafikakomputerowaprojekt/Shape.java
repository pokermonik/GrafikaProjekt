/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Graphics;

/**
 *
 * @author poker
 */
public interface Shape {
    
    public abstract void draw(Graphics g);
    public abstract void move(int x, int y);
    public abstract void resize(int x,int y);
    public abstract boolean position(int x,int y);
    
}
