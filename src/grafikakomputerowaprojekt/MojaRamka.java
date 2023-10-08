/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import static javax.swing.GroupLayout.Alignment.CENTER;

/**
 *
 * @author poker
 */
public class MojaRamka extends JFrame{
    int startX;
    int startY;
    int endX;
    int endY;
    int wybor=-1;
    int click =0;
    ShapesList shapes = new ShapesList();
    Line currentLine;
    Shape selectedShape;
    JButton btnLinia= new JButton("Linia");
    JButton btnProstokat= new JButton("Prostokat");
    JButton btnOkrag= new JButton("Okrag");
    JButton btnPrzesun= new JButton("Przesun");
    JButton btnRozmiar= new JButton("Zmien Rozmiar");
    DrawCanvas myCanvas;
    
    JPanel controlPanel = new JPanel();
    JPanel canvasPanel = new JPanel();
    
    
    
    public MojaRamka()
    {
        super("Nazwa");
        this.myCanvas = new DrawCanvas(mouseAdapter);

        controlPanel.setLayout(new FlowLayout());
        btnLinia.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=0;
            
        }
        });
        btnProstokat.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=1;
        }
        });
          btnOkrag.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=2;
        }
        });
               btnPrzesun.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=3;
        }
        });
               
                    btnRozmiar.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=4;
        }
        });
        controlPanel.add(btnLinia);
        controlPanel.add(btnProstokat);
        controlPanel.add(btnOkrag);
        controlPanel.add(btnPrzesun);
        controlPanel.add(btnRozmiar);

        // Add the canvas to the canvas panel
        canvasPanel.setLayout(new BorderLayout());
        canvasPanel.add(myCanvas, BorderLayout.CENTER);

        // Add the control panel and canvas panel to the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
        
        myCanvas.setPreferredSize(new Dimension(500, 500));
        setContentPane(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Pack the frame to fit components
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    
     private class DrawCanvas extends JPanel {
      // Override paintComponent to perform your own painting
         
        private BufferedImage buffer;
        public DrawCanvas(MouseAdapter MA) {
           addMouseListener(MA);
           addMouseMotionListener(MA);
       }
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         
         setBackground(Color.WHITE);  // set background color for this JPanel
         
         
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g.setColor(Color.BLACK);    // set the drawing color
       
                for (int i=0;i<shapes.getLength();i++)
                   {
                  
                       shapes.get(i).draw(g);
                       
                       
                   }
         switch(wybor)
         {
              
             case 0:
                Line currentline = new Line(startX,startY,endX,endY);
                currentline.draw(g);
                if(click==1)
                {
                    shapes.add(currentline);
                }
                
                break;
             case 1:
                 Rectangle r = new Rectangle(startX,startY,endX,endY);
                 r.draw(g);
                 if(click==1)
                 {
                     shapes.add(r);
                 }
                 
                 break;
             case 2:
                 Circle c = new Circle(startX,startY,endX,endY);
                 c.draw(g);
                 if(click==1)
                 {
                     shapes.add(c);
                 }
                 
                 break;
             case 3:
                 
                 break;
             case 4:
                 break;
                 
                 
         }
     
          
   
      }
   }
     
     MouseAdapter mouseAdapter = new MouseAdapter()
     {
      
         
         public void mouseDragged(MouseEvent e)
         {
            System.out.println("Moved");
            endX=e.getX();
            endY=e.getY();
            if (wybor == 3 && selectedShape != null)
            { // Check if the Przesun button is selected and a shape is selected
            int dx = e.getX() - startX;
            int dy = e.getY() - startY;
            selectedShape.move(dx, dy); // Update the position of the selected shape
            startX = e.getX();
            startY = e.getY();
           
            }
            if(wybor==4 && selectedShape !=null)
            {
                int dx = e.getX() - startX;
                int dy = e.getY() - startY;
                selectedShape.resize(dx, dy); // Update the position of the selected shape
                startX = e.getX();
                startY = e.getY();
            }
            myCanvas.repaint();
            
            
            
         }
         public void mousePressed(MouseEvent e)
         {
            
            startX=e.getX();
            startY=e.getY();
            click=0;
            if(wybor==3 || wybor==4)
            {
              selectedShape=shapes.getSelectedShape(startX,startY);
            }
            System.out.println("Pressed"+"X pocz: "+startX+"Y pocz: "+startY);
           
         }
         public void mouseReleased(MouseEvent e)
         {
            
            endX=e.getX();
            endY=e.getY();
            System.out.println("Released"+"X koniec: "+endX+"Y koniec: "+endY);
            click=1;
            myCanvas.repaint();
            
            
            
         }
     };
    
    

    
}
