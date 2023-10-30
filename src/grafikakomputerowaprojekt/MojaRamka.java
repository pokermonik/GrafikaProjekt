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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    DrawCanvas myCanvas;
    
    MyButton btnZapisz = new MyButton("Zapisz");
    MyButton btnWczytaj = new MyButton("Wczytaj");
    MyButton btnLinia= new MyButton("Linia");
    MyButton btnProstokat= new MyButton("Prostokat");
    MyButton btnOkrag= new MyButton("Okrag");
    MyButton btnPrzesun= new MyButton("Przesun");
    MyButton btnRozmiar= new MyButton("Zmien Rozmiar");
    
  
    
    JLabel coordinatesLabel= new JLabel("X: 0 Y: 0");
    JLabel textDialogAlert = new JLabel("Udało się zapisać obraz");
    
    JCheckBox checkBoxPixele = new JCheckBox("Wpisz recznie");
    
    JPanel controlPanel = new JPanel();
    JPanel canvasPanel = new JPanel();
    
    MultipleInputDialog dialog4 =new MultipleInputDialog(1);
    MultipleInputDialog dialog2 =new MultipleInputDialog(2);
    JDialog dialogAlert = new JDialog(this,"AAA");
    
    JFileChooser chooseFile = new JFileChooser();
    
    
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
        super("Paint Beta");
        this.myCanvas = new DrawCanvas(mouseAdapter);

        
          checkBoxPixele.setForeground(Color.white); // Change the text color
        checkBoxPixele.setBackground(Color.blue); // Change the background color
       
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBorder(new LineBorder(Color.BLACK, 1));
        controlPanel.setBackground(Color.white);
        btnLinia.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=0;
            if(recznie==-1)
            {
                
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
        
        btnZapisz.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {       
                    myCanvas.saveCanvas();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MojaRamka.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
            
        }
        );
        
        btnWczytaj.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCanvas.loadCanvas();

            }
            
        });
        
        textDialogAlert.setHorizontalAlignment(SwingConstants.CENTER);
        dialogAlert.add(textDialogAlert);
        dialogAlert.setSize(200, 100); // Set the size of the dialog
        dialogAlert.setLocationRelativeTo(MojaRamka.this); // 
        dialogAlert.setTitle("Udalo sie!");
        
        chooseFile.setFileFilter(new FileNameExtensionFilter(".png", "png"));

        
        
        controlPanel.add(checkBoxPixele);
        controlPanel.add(btnLinia);
        controlPanel.add(btnProstokat);
        controlPanel.add(btnOkrag);
        controlPanel.add(btnPrzesun);
        controlPanel.add(btnRozmiar);
        controlPanel.add(btnZapisz);
        controlPanel.add(btnWczytaj); 
        controlPanel.add(coordinatesLabel);
        
      
     

        // Add the canvas to the canvas panel
        canvasPanel.setLayout(new BorderLayout());
        canvasPanel.add(myCanvas, BorderLayout.CENTER);

        // Add the control panel and canvas panel to the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
        
        myCanvas.setPreferredSize(new Dimension(750, 750));
        setContentPane(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Pack the frame to fit components
        setLocationRelativeTo(null); // Center the frame
        
    }
 
    
     private class DrawCanvas extends JPanel {
      // Override paintComponent to perform your own painting
         
        private BufferedImage buffer;
        private Image loadedImage;
        public DrawCanvas(MouseAdapter MA) {
           addMouseListener(MA);
           addMouseMotionListener(MA);
       }
       
        public void setImage(Image img)
        {
            this.loadedImage=img;
        }
        public void clearCanvas(Graphics g)
        {
            super.paintComponent(g); 
        }
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         
         
         setBackground(Color.WHITE);  // set background color for this JPanel
         
          if (loadedImage != null) {
        g.drawImage(loadedImage, 0, 0, null);
    }
         // Your custom painting codes. For example,
         // Drawing primitive shapes
        
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
      
      public void saveCanvas() throws FileNotFoundException
      {
        int returnValue = chooseFile.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooseFile.getSelectedFile();
               if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
            selectedFile = new File(selectedFile.getAbsolutePath()+".png");
        }

        try (FileOutputStream fileOut = new FileOutputStream(selectedFile);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(shapes); // Serialize and write the shapes
            dialogAlert.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
      }
      public void loadCanvas()
      {       
          
   int returnValue = chooseFile.showOpenDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        
        File selectedFile = chooseFile.getSelectedFile();
        
         // Remove the file extension filter
        shapes.removeAll();
        try (FileInputStream fileIn = new FileInputStream(selectedFile);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            shapes.removeAll();
            wybor=-1;
            selectedShape=null;
            shapes = (ShapesList) objectIn.readObject(); // Deserialize and read the shapes

            
            myCanvas.repaint();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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