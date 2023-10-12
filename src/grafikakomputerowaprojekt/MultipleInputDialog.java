/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

/**
 *
 * @author poker
 */
public class MultipleInputDialog extends JPanel{
    JTextField x1 = new JTextField();
    JTextField y1 = new JTextField();
    JTextField x2 = new JTextField();
    JTextField y2 = new JTextField();
    Object[] message = {
        "Input value 1:", x1,
        "Input value 2:", y1,
        "Input value 3:", x2,
        "Input value 4:", y2,

    };
    public MultipleInputDialog(int mode) 
    {
        if(mode==1)
        {
        setLayout(new GridLayout(5, 2));
        add(new JLabel("X1:"));
        add(x1);
        add(new JLabel("Y1:"));
        add(y1);
        add(new JLabel("X2:"));
        add(x2);
        add(new JLabel("Y2:"));
        add(y2);
        }
        else if(mode==2)
        {
                setLayout(new GridLayout(5, 2));
        add(new JLabel("X:"));
        add(x1);
        add(new JLabel("Y:"));
        add(y1);
        }

  
    }
       
    public int showInputDialog(Component parentComponent) 
    {   
        
        return JOptionPane.showConfirmDialog
        (
        parentComponent,
        this,
        "Podaj współrzędne pixeli",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
      );
    }
    
    public void setX1(int X1)
    {
        x1.setText(Integer.toString(X1));
    }
    public int getX1()
    {
        return(Integer.parseInt(x1.getText()));
    }
    public int getY1()
    {
        return(Integer.parseInt(y1.getText()));
    }
    public int getX2()
    {
        return(Integer.parseInt(x2.getText()));
    }
    public int getY2()
    {
        return(Integer.parseInt(y2.getText()));
    }
    
}
