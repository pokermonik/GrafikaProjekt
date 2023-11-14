/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author poker
 */
public class ShapesList implements Serializable {
    
    LinkedList<Shape> shapes = new LinkedList<>();
    
    public void removeAll()
    {
 
             shapes.clear();
        
        
    }
    public int getLength()
    {
        return shapes.size();
    }
    public Shape get(int i)
    {
        return shapes.get(i);
    }
    public Shape getSelectedShape(int x, int y) {
    for (Shape s : shapes) {
        // Check if the shape contains the point (x, y)
        if (s.position(x, y)) {
            return s;
        }
    }
    return null; // No shape found at the given coordinates
}
    public void add(Shape s)
    {
        shapes.add(s);
    }
    public void replace(Shape s)
    {
        shapes.removeLast();
        shapes.add(s);
    }
    
}
