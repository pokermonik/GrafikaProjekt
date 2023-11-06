 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author poker
 */
public class ImageDisplay {
    ImageIcon img;
    JOptionPane dialogCompression = new JOptionPane();
    JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
    JFileChooser chooseFile = new JFileChooser();
    ImageModifier imageModifier = new ImageModifier();
    ZoomImagePanel imagePanel;
    JDialog dialogAlert = new JDialog();
    JFrame frame = new JFrame();
    JSlider zoomSlider; 
    JLabel label = new JLabel("R:0 G:0 B:0");
    public ImageDisplay()
    {
       
    }
    public ImageDisplay(BufferedImage img) {
         jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        this.img = new ImageIcon(img);
        this.imagePanel = new ZoomImagePanel(img);
        imagePanel.addMouseMotionListener(new MouseMotionListener() {
            int xPoint=0;
            int yPoint=0;
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                double zoomLevel = imagePanel.getZoomLevel();
                int originalX=x;
                int originalY = y;
                if((int)zoomLevel!=0)
                {
                    originalX=originalX/(int)zoomLevel;
                    originalY=originalY/(int)zoomLevel;
                }
               
                
                if (originalX >= 0 && originalX < imagePanel.getImage().getWidth() &&
        originalY >= 0 && originalY < imagePanel.getImage().getHeight()) {
        Color pixelColor = new Color(imagePanel.getImage().getRGB(originalX, originalY));
        label.setText("R: " + pixelColor.getRed() + " G: " + pixelColor.getGreen() + " B: " + pixelColor.getBlue());
    }
            }

             @Override
             public void mouseDragged(MouseEvent e) {
                 
             }
            
        });
                
        // Create a JSlider for zooming
        zoomSlider = new JSlider(JSlider.HORIZONTAL, 1, 30000*(1/(img.getHeight()*img.getHeight())), 100); // Change the min, max, and initial values as needed
       

        // Add a change listener to the slider
        zoomSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int zoomLevel = source.getValue();
                    setZoomLevel(zoomLevel / 100.0); // Update the zoom level
                }
            }
        });
        
      
    }

  

      public void setZoomLevel(double zoomLevel) {
        imagePanel.setZoomLevel(zoomLevel);
    }
       public void setVisible(boolean b) {
           
    
           frame.setLayout(new BorderLayout());
            JLabel textDialogAlert = new JLabel("NIEOBSLUGIWANY TYP PLIKU");
             textDialogAlert.setHorizontalAlignment(SwingConstants.CENTER);
        dialogAlert.add(textDialogAlert);
        dialogAlert.setSize(200, 100); // Set the size of the dialog 
        dialogAlert.setTitle("BLAD WYBORU");
        dialogAlert.setLocationRelativeTo(frame);
        MyButton button = new MyButton("Wczytaj JPG/PPM");
        MyButton button2 = new MyButton("Zapisz jako JPG");
        MyButton button3 = new MyButton("Skala szarosci");
        MyButton button4 = new MyButton("Zmien jasnosc");
        MyButton button5 = new MyButton("Dodawanie");
        MyButton button6 = new MyButton("Odejmowanie");
        MyButton button7 = new MyButton("Mnozenie");
        MyButton button8 = new MyButton("Dzielenie");
        MyButton button9 = new MyButton("Medianowy");
        MyButton button10 = new MyButton("Usredniajacy");
        MyButton button11 = new MyButton("Detekcja krawedzi");
        MyButton button12 = new MyButton("Gorny");
        MyButton button13 = new MyButton("Gauss");
   
           button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                loadJPG(0);
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
           button2.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                saveToJPG();
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        button3.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            grayScale();
            
        }
        });
        button4.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            //your actions
            changeBrigtness();
           
            
        }
        });
        button5.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                loadJPG(1);
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        });
           button6.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                loadJPG(2);
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        });
        button7.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                loadJPG(3);
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        });
        button8.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //your actions
                loadJPG(4);
            } catch (IOException ex) {
                Logger.getLogger(ImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        }
        });
        button9.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                //your actions
                changeImage(imageModifier.medianFilter(imagePanel.getImage()));
           
           
            
        }
        });
        button10.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                //your actions
                changeImage(imageModifier.averageFilter(imagePanel.getImage()));
           
           
            
        }
        });
           button11.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                //your actions
                changeImage(imageModifier.sobelFilter(imagePanel.getImage()));
           
           
            
        }
        });
        button12.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            
                //your actions
                changeImage(imageModifier.highPassSharpening(imagePanel.getImage()));
           
           
            
        }
        });
        button13.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
                //your actions
                changeImage(imageModifier.gaussianBlur(imagePanel.getImage()));
           
           
            
        }
        });
        
        
        
        
        
        
        
           
           
        JPanel controlPanel = new JPanel();
        controlPanel.add(label);
        controlPanel.add(button);
        controlPanel.add(button2);
        controlPanel.add(button3);
        controlPanel.add(button4);
        controlPanel.add(button5);
        controlPanel.add(button6);
        controlPanel.add(button7);
        controlPanel.add(button8);
        controlPanel.add(button9);
        controlPanel.add(button10);
        controlPanel.add(button11);
        controlPanel.add(button12);
        controlPanel.add(button13);
        controlPanel.setBorder(new LineBorder(Color.BLACK, 1));
        controlPanel.setBackground(Color.white);
        // Add the image panel to the frame's center
        frame.add(imagePanel, BorderLayout.CENTER);

        // Add the zoom slider to the frame's south
        frame.add(zoomSlider, BorderLayout.SOUTH);
        
        frame.add(controlPanel,BorderLayout.NORTH);
        frame.setSize(800, 600);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
       
       public void loadJPG(int mode) throws IOException
        {       
            int returnValue = chooseFile.showOpenDialog(label);
            
            if (returnValue == JFileChooser.APPROVE_OPTION)
            {
               
                File selectedFile = chooseFile.getSelectedFile();
                if(selectedFile.getName().contains("jpg"))
                {
                    JPGreader jpg= new JPGreader(selectedFile);
                    switch (mode) {
                        case 1 -> changeImage(imageModifier.addition(imagePanel.getImage(), jpg.getImage() ));
                        case 2 -> changeImage(imageModifier.substraction(imagePanel.getImage(), jpg.getImage() ));
                        case 3 -> changeImage(imageModifier.multiplication(imagePanel.getImage(), jpg.getImage() ));
                        case 4 -> changeImage(imageModifier.division(imagePanel.getImage(), jpg.getImage() ));
                        default -> changeImage(jpg.getImage());
                    }
                    
                }
                else if(selectedFile.getName().contains("ppm"))
                {
                    PPMreader ppm= new PPMreader(selectedFile);
                    changeImage(ppm.readP3(selectedFile.getName()));
                }
                else
                {
                    dialogAlert.setVisible(true);
                }
                 // Remove the file extension filter
            
            }
            zoomSlider.setValue(1);
            setZoomLevel(1);
        }
  
           public void changeImage(BufferedImage image)
           {
               imagePanel.setImage(image);
           }
    
   public void changeBrigtness()
   {
       Float showInputDialogF;
       while(true)
       {

            String showInputDialog = JOptionPane.showInputDialog(frame,"Prosze podaj stopien zmiany jasnosci, 0.1 do 2, gdzie 2 oznacza najjasniej, a 0.1 najciemniej");
           
            try{showInputDialogF = Float.parseFloat(showInputDialog);
            if(0.1<=showInputDialogF && showInputDialogF<=2 )
            {
                changeImage(imageModifier.changeBrightness(imagePanel.getImage(),showInputDialogF));
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }

       }
        
   }
      public void grayScale()
   {
       int showInputDialogF;
       while(true)
       {

            String showInputDialog = JOptionPane.showInputDialog(frame,"Ktora metoda zmiany szarosci? 1-srednia 2-wzor");
            if(showInputDialog==null)
            {
                break;
            }
            try{showInputDialogF = Integer.parseInt(showInputDialog);
            
            if(showInputDialogF==2 || showInputDialogF==1 )
            {
                changeImage(imageModifier.convertToGrayScale(imagePanel.getImage(),showInputDialogF));
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }

       }
        
   }
   
   public void saveToJPG() throws IOException 
   {
    Float showInputDialogF;
    int returnValue = chooseFile.showSaveDialog(label);
    if (returnValue == JFileChooser.APPROVE_OPTION) 
    {
        while(true)
        {
           
            String showInputDialog = JOptionPane.showInputDialog(frame,"Prosze podaj stopien kompresji, 0.1 do 1, gdzie 1 oznacza brak kompresji");
            try{showInputDialogF = Float.parseFloat(showInputDialog);
            if(0.1<=showInputDialogF && showInputDialogF<=1 )
            {
                setCompressionQuality(showInputDialogF);
                break;
            }
            else
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(imagePanel, "Zly parametr!");
            }
            
        }
   
        File selectedFile = chooseFile.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".jpg")) {
            // Ensure the file has a .jpg extension
            selectedFile = new File(selectedFile.getAbsolutePath() + ".jpg");
        }

        // Create a new BufferedImage with a white background
        BufferedImage newImage = new BufferedImage(
            imagePanel.getImage().getWidth(),
            imagePanel.getImage().getHeight(),
            BufferedImage.TYPE_INT_RGB
        );
        Graphics2D graphics = newImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, newImage.getWidth(), newImage.getHeight());
        graphics.drawImage(imagePanel.getImage(), 0, 0, null);
        graphics.dispose(); 
       

        final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        writer.setOutput(new FileImageOutputStream(selectedFile));

        try {
            if(showInputDialogF==1)
            {
                ImageIO.write(newImage, "jpg", selectedFile);
            }
            else
            {
                 writer.write(null, new IIOImage(newImage, null, null), jpegParams);
            }
            System.out.println("Image saved to " + selectedFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
    public void setCompressionQuality(float value)
    {
        jpegParams.setCompressionQuality(value);
    }

   
    }
