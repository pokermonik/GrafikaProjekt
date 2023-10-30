/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author poker
 */
public class MenuJFrame{
    
    JFrame frame = new JFrame();
    MyButton butt1 = new MyButton("Zad1");
    MyButton butt2 = new MyButton("Zad2");
    MyButton butt3 = new MyButton("Zad3");
    JPanel panel = new JPanel();
    MojaRamka zad1 = new MojaRamka();
    ImageDisplay zad2 = new ImageDisplay(new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB));
    ColorConverterFrame zad3 = new ColorConverterFrame();
    public void setVisible(boolean mode)
    {
        frame.setLayout(new GridLayout(1,1));
        panel.setLayout(new GridLayout(3,1));
        panel.add(butt1);
        panel.add(butt2);
        panel.add(butt3);
        butt1.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            zad1.setVisible(true);
        }
        });
        butt2.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            zad2.setVisible(true);
        }
        });
        butt3.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            zad3.setVisible(true);
        }
        });
        
        frame.add(panel);
        frame.setSize(100, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    
}
