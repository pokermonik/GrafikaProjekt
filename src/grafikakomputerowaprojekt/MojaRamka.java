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
    int recznie=1;
    ShapesList shapes = new ShapesList();
    Line currentLine;
    Shape selectedShape;
    JButton btnLinia= new JButton("Linia");
    JButton btnProstokat= new JButton("Prostokat");
    JButton btnOkrag= new JButton("Okrag");
    JButton btnPrzesun= new JButton("Przesun");
    JButton btnRozmiar= new JButton("Zmien Rozmiar");
    JLabel coordinatesLabel= new JLabel("X: 0 Y: 0");
    
    JCheckBox checkBoxPixele = new JCheckBox("Wpisz recznie");
    DrawCanvas myCanvas;
    
    JPanel controlPanel = new JPanel();
    JPanel canvasPanel = new JPanel();
    MultipleInputDialog dialog4 =new MultipleInputDialog(1);
    MultipleInputDialog dialog2 =new MultipleInputDialog(2);
    
    public void showMyDialog()
    {
        
        int result = dialog4.showInputDialog(controlPanel);
        if(result==JOptionPane.OK_OPTION)
        {
            this.click=1;
            this.startX=dialog4.getX1();
            this.startY=dialog4.getY1();
            this.endX=dialog4.getX2();
            this.endY=dialog4.getY2();
            myCanvas.repaint();
        } 
    }
    public void showMyDialog2()
    {
        
        int result = dialog2.showInputDialog(controlPanel);
        if(result==JOptionPane.OK_OPTION)
        {
            if (wybor==3)
            {
                selectedShape.move(dialog2.getX1(), dialog2.getY1());
            }
            else
            {
                selectedShape.resize(dialog2.getX1(), dialog2.getY1());
            }
            
            myCanvas.repaint();
        } 
    }
    
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
            if(recznie==-1)
            {
                showMyDialog();
            }
        }
        });
        btnProstokat.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=1;
               if(recznie==-1)
            {
                showMyDialog();
            }
        }
        });
          btnOkrag.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=2;
               if(recznie==-1)
            {
                showMyDialog();
            }
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
                    
        checkBoxPixele.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                recznie=-recznie;
                System.out.println(recznie);
            }
            
        });
                
        controlPanel.add(checkBoxPixele);
        controlPanel.add(btnLinia);
        controlPanel.add(btnProstokat);
        controlPanel.add(btnOkrag);
        controlPanel.add(btnPrzesun);
        controlPanel.add(btnRozmiar);
        controlPanel.add(coordinatesLabel);
      
     

        // Add the canvas to the canvas panel
        canvasPanel.setLayout(new BorderLayout());
        canvasPanel.add(myCanvas, BorderLayout.CENTER);

        // Add the control panel and canvas panel to the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
        
        myCanvas.setPreferredSize(new Dimension(700, 700));
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
         Graphics2D g2 = (Graphics2D) g;
         
         setBackground(Color.WHITE);  // set background color for this JPanel
         
         
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g2.setStroke(new BasicStroke(7));
         g.setColor(Color.BLACK);    // set the drawing color
         
       
                for (int i=0;i<shapes.getLength();i++)
                   {
                       /*if(shapes.get(i).position(startX, startY))
                       {
                           g.setColor(Color.red);
                       }*/
                       shapes.get(i).draw(g);
                       //g.setColor(Color.BLACK);
                       
                       
                   }
         //g.setColor(Color.red);
         
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
                if(recznie==-1 && wybor<3)
                {
                    
                }
                else
                {
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
            System.out.println("Moved");
         
            
            
            
         }
  
         public void mousePressed(MouseEvent e)
         {
         
                if(recznie==-1 && wybor<3)
                {
                
                }
                else
                {
                       startX=e.getX();
                startY=e.getY();
                click=0;
                }
             
                if(wybor==3 || wybor==4)
                {
                  selectedShape=shapes.getSelectedShape(startX,startY);
                } 
                
            System.out.println("Pressed"+"X pocz: "+startX+"Y pocz: "+startY);
           
         }
         public void mouseMoved(MouseEvent e)
         {
                coordinatesLabel.setText("X: "+e.getX()+" Y: "+e.getY());
             
         }
         public void mouseReleased(MouseEvent e)
         {
             
            if(recznie==1)
            {
                endX=e.getX();
                endY=e.getY();
                System.out.println("Released"+"X koniec: "+endX+"Y koniec: "+endY);
                click=1;
                myCanvas.repaint();
            }
            else
            {
               if ((wybor == 3 || wybor==4) && selectedShape != null)
               {
                   showMyDialog2();
               }
            }
        
            
            
            
         }
     };
    
    

    
}
