/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.util.LinkedList;

/**
 *
 * @author poker
 */
public class ShapesList {
    
    LinkedList<Shape> shapes = new LinkedList<>();
    
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
    
}
