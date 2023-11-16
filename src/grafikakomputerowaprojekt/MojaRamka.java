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
    int wybor=-3;
    int click =0;
    int recznie=1;
    int bezierCounter=0;
    int bezierSelectedLevel=0;
   
    ShapesList shapes = new ShapesList();
    
    int bezierRecznie=-3;
    int bezierWybor=1;
    Shape selectedShape;
    DrawCanvas myCanvas;
    BezierCurve bc = new BezierCurve(0,0,0,0);
    MyButton btnZapisz = new MyButton("Zapisz");
    MyButton btnWczytaj = new MyButton("Wczytaj");
    MyButton btnWyczysc = new MyButton("Wyczysc");
    MyButton btnLinia= new MyButton("Linia");
    MyButton btnProstokat= new MyButton("Prostokat");
    MyButton btnOkrag= new MyButton("Okrag");
    MyButton btnKrzywa= new MyButton("Krzywa");
    MyButton btnPrzesun= new MyButton("Przesun");
    MyButton btnRozmiar= new MyButton("Zmien Rozmiar");
    MyButton btnWybierzKolor= new MyButton("Wybierz Kolor");
    
    Color newColor = new Color(0);
    
    
    JLabel coordinatesLabel= new JLabel("X: 0 Y: 0");
    JLabel textDialogAlert = new JLabel("Udało się zapisać obraz");
    
    JCheckBox checkBoxPixele = new JCheckBox("Wpisz recznie");
    
    JPanel controlPanel = new JPanel();
    JPanel canvasPanel = new JPanel();
    
    MultipleInputDialog dialog4 =new MultipleInputDialog(1);
    MultipleInputDialog dialog2 =new MultipleInputDialog(2);
    JDialog dialogAlert = new JDialog(this,"AAA");
    
    JFileChooser chooseFile = new JFileChooser();
    
    public boolean checkBezier()
    {
        if(bezierCounter<bezierSelectedLevel)
        {
            JOptionPane.showMessageDialog(this, "Dokoncz rysowanie krzywej Beziera!");
            return false;
        }
        return true;
    }
   
    public void showMyDialog()
    {
        while(true)
        {
            int result = dialog4.showInputDialog(controlPanel);
            if(result==JOptionPane.OK_OPTION && dialog4.isValid1())
            {
                this.click=1;
                this.startX=dialog4.getX1();
                this.startY=dialog4.getY1();
                this.endX=dialog4.getX2();
                this.endY=dialog4.getY2();
                myCanvas.repaint();
                break;
 
            }
            else if(result ==-1 || result==JOptionPane.CANCEL_OPTION)
            {
                break;
            }
        }
     
    }
    
    public void showMyDialogBezier()
    {
        int ok=0;
        while(true)
        {
            int result = dialog4.showInputDialog(controlPanel);
            if(result==JOptionPane.OK_OPTION && dialog4.isValid1())
            {
                this.click=1;
                this.startX=dialog4.getX1();
                this.startY=dialog4.getY1();
                this.endX=dialog4.getX2();
                this.endY=dialog4.getY2();
                myCanvas.repaint();
                ok=1;
                break;
            }
            else if(result ==-1 || result==JOptionPane.CANCEL_OPTION)
               {
                   break;
               }
    
        }
        if(bezierSelectedLevel>2 && ok==1)
        {
             for(int i=2;i<bezierSelectedLevel;i++)
            {
                while(true)
                {
                    int result = dialog2.showInputDialog(controlPanel );
                    if(result==JOptionPane.OK_OPTION && dialog2.isValid1())
                    {
                        this.endX=dialog2.getX1();
                        this.endY=dialog2.getY1();
                        myCanvas.repaint();
                        break;
                    }
                }
         
            } 
            
        }
       
        
      
    }
    public void showMyDialog2()
    {
        while(true)
        {
        int result = dialog2.showInputDialog(controlPanel);
        if(result==JOptionPane.OK_OPTION && dialog2.isValid1())
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
            break;
        }
         else if(result ==-1 || result==JOptionPane.CANCEL_OPTION)
            {
                break;
            }
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
        
        
        btnWybierzKolor.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            newColor = JColorChooser.showDialog(null, "Wybierz kolor pędzla", Color.RED);
        }
        });

        btnKrzywa.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            wybor=-2;
            selectLevelBeizer();
            if(recznie==-1)
            {
                
                showMyDialogBezier();
            }
        }
        });
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
        btnWyczysc.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                shapes.removeAll();
                selectedShape=null;
                wybor=-1;
                bezierCounter=0;
                bezierSelectedLevel=0;
                myCanvas.repaint();

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
        controlPanel.add(btnKrzywa);
        controlPanel.add(btnPrzesun);
        controlPanel.add(btnRozmiar);
        controlPanel.add(btnWyczysc);
        controlPanel.add(btnWybierzKolor);
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
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        
             // set the drawing color
         
       
                for (int i=0;i<shapes.getLength();i++)
                   {
                       /*if(shapes.get(i).position(startX, startY))
                       {
                           g.setColor(Color.red);
                       }*/
                       g.setColor(shapes.get(i).getColor());
                       shapes.get(i).draw(g);
                       //g.setColor(Color.BLACK);
                       
                       
                   }    
                g.setColor(newColor);
         //g.setColor(Color.red);
         
         switch(wybor)
         {
              
             case 0:
                Line currentline = new Line(startX,startY,endX,endY);
                currentline.draw(g);
                if(click==1)
                {
                    currentline.setColor(newColor);
                    shapes.add(currentline);
                }
                
                break;
             case 1:
                 Rectangle r = new Rectangle(startX,startY,endX,endY);
                 r.draw(g);
                 if(click==1)
                 {
                     r.setColor(newColor);
                     shapes.add(r);
                 }
                 
                 break;
             case 2:
                 Circle c = new Circle(startX,startY,endX,endY);
                 c.draw(g);
                 if(click==1)
                 {
                     c.setColor(newColor);
                     shapes.add(c);
                 }
                 
                 break;
             case 3:
                 
                 break;
             case 4:
                 break;
             case -2:
                
                 if(bezierCounter==0)
                 {
                    bezierRecznie=recznie;
                    bezierWybor=wybor;
                    bc = new BezierCurve(startX,startY,endX,endY);
                    bc.setColor(newColor);
                    //bc.calculateDimensions();
                    bc.draw(g);
                    bezierCounter=1;
                    System.out.println("AAAA"+bc.controlPoints.size());
                    
                    if(click==1)
                    {
                        //shapes.add(bc);
                    }
                    
                    
                 }
                 else 
                 {
                    
                    System.out.println("STARE"+bc.controlPoints.size()+bc.getX1()+bc.getY1());
                    Point p = new Point(endX,endY);
                    
                    bc.addControlPoint(p);
                    System.out.println("NOWE"+bc.controlPoints.size()+bc.getX1()+bc.getY1());
                    
                    bc.draw(g);
                    
                    if(click!=1)
                    {
                        bc.removeControlPoint(p);
                    }
                    if(bezierSelectedLevel==bezierCounter )
                    {
                        
                        bezierCounter=0;
                        //bezierSelectedLevel=0;
                        shapes.add(bc);
                        
                        
                    }
                 
                    
                    
                    
                 }
                
               

             
                 
                 
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
                if(bezierSelectedLevel>bezierCounter && bezierSelectedLevel!=0 && bezierCounter!=0 && (bezierWybor!=wybor || bezierRecznie!=recznie))
                {
                   bc.setColor(newColor);
                   shapes.add(bc);
                   bezierCounter=0;
                   bezierSelectedLevel=0;
                }
                coordinatesLabel.setText("X: "+e.getX()+" Y: "+e.getY()); 
         }
         public void mouseReleased(MouseEvent e)
         {   
             if(wybor==-2)
                {
                    
                    bezierCounter++;
                }
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
    
    
 public void selectLevelBeizer()
   {
       int showInputDialogF;
       while(true)
       {

            String showInputDialog = JOptionPane.showInputDialog(this,"Podaj stopien krzywej Beziera \n od 2 do 10");
            if(showInputDialog==null)
            {
                break;
            }
            try{showInputDialogF = Integer.parseInt(showInputDialog);
            
            if(showInputDialogF>=2 && showInputDialogF<=10 )
            {
                bezierSelectedLevel =showInputDialogF;
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Zly parametr!");
            }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "Zly parametr!");
            }

       }
        
   }
    
}
