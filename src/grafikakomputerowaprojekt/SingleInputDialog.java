/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Component;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author poker
 */
public class SingleInputDialog {
    
  public float singleInputDialogFloat(String message, float minRange, float maxRange,JFrame frame,Component parent) {
    float userInput;

    while (true) {
        String input = JOptionPane.showInputDialog(frame, message);

        if (input == null) {
            return -1;
        }

        try {
            userInput = Float.parseFloat(input);

            if (userInput >= minRange && userInput <= maxRange) {
                return userInput;
            } else {
                JOptionPane.showMessageDialog(frame, "Zla wartosc! Podaj miedzy " + minRange + " i " + maxRange + ".");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Zla wartosc! podaj poprawny rodzaj wartosci.");
        }
    }
}
}
