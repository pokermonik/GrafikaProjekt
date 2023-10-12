/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author poker
 */
public class MyButton extends JButton{
    public MyButton(String text) {
        super(text);
        setForeground(Color.WHITE);  // Text color
        setBackground(Color.BLUE);   // Background color
        
    }
    
        public MyButton(String text, Color c) {
        super(text);
        setForeground(Color.WHITE);  // Text color
        setBackground(c);   // Background color
        
    }
}
