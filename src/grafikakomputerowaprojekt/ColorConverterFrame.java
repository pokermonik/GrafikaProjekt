/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.color.ColorSpace;
import static java.awt.color.ColorSpace.TYPE_CMYK;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author poker
 */
public class ColorConverterFrame {
    JFrame frame = new JFrame();
    private ColorConverter colorConverter;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JSlider cyanSlider;
    private JSlider magentaSlider;
    private JSlider yellowSlider;
    private JSlider keySlider;
    
    
    private Canvas canvasCMYK;
    private Canvas canvasRGB;
    
    private Color rgbColor;
    private Color cmykColor;
    
    
    private JTextField redTextField;
    private JTextField greenTextField;
    private JTextField blueTextField;
    
    private JTextField magentaTextField;
    private JTextField cyanTextField;
    private JTextField yellowTextField;
    private JTextField blackTextField;

    int block=1;
    
    JLabel titleLabelRGB = new JLabel("RGB");
    JLabel titleLabelCMYK  = new JLabel("CMYK");
    
    private JCheckBox cmykButton;
    public ColorConverterFrame()
    {
        colorConverter=new ColorConverter();
        
        JPanel controlPanel = new JPanel(new GridLayout(2,1));
        JPanel textPanel = new JPanel(new GridLayout(4,2));
        JPanel colorPanel = new JPanel(new GridLayout(1,2));
        
        JPanel RGBPanel = new JPanel(new GridLayout(3,1));
        RGBPanel.setBorder(new LineBorder(Color.BLACK, 1));
        RGBPanel.setBackground(Color.white);
        JPanel CMYKPanel = new JPanel(new GridLayout(4,1));
        CMYKPanel.setBorder(new LineBorder(Color.BLACK, 1));
        CMYKPanel.setBackground(Color.white);
        // Add the image panel to the frame's center
 
        redSlider = createSlider("Red", 0, 255);
        greenSlider = createSlider("Green", 0, 255);
        blueSlider = createSlider("Blue", 0, 255);
        cyanSlider = createSlider("Cyan", 0, 100);
        magentaSlider = createSlider("Magenta", 0, 100);
        yellowSlider = createSlider("Yellow", 0, 100);
        keySlider = createSlider("Key (Black)", 0, 100);

      
        
       
        
        
   

        cmykButton =  new JCheckBox("CMYK = ON, RGB = OFF");
        redTextField = createTextField("Red");
        greenTextField = createTextField("Green");
        blueTextField = createTextField("Blue");
        cyanTextField = createTextField("Cyan");
        magentaTextField = createTextField("Magenta");
        yellowTextField = createTextField("Yellow");
        blackTextField = createTextField("Key");

        textPanel.add(redTextField);
        textPanel.add(greenTextField);
        textPanel.add(blueTextField);
        textPanel.add(cyanTextField);
        textPanel.add(magentaTextField);
        textPanel.add(yellowTextField);
        textPanel.add(blackTextField);
        //textPanel.add(cmykButton);
        colorPanel.add(RGBPanel);
        colorPanel.add(CMYKPanel);
        controlPanel.add(colorPanel);
        controlPanel.add(textPanel);
        frame.add(controlPanel);
        
        
        JPanel colorPanelRGB = new JPanel(new BorderLayout());
        canvasRGB = new Canvas();
        
       

        JPanel titlePanelRGB = new JPanel();
       
       
        titlePanelRGB.add(titleLabelRGB);

        colorPanelRGB.add(titlePanelRGB, BorderLayout.NORTH);
        
        colorPanelRGB.add(canvasRGB, BorderLayout.CENTER);
        
     
        JPanel colorPanelCMYK= new JPanel(new BorderLayout());
        canvasCMYK = new Canvas();
        

        JPanel titlePanelCMYK  = new JPanel();

        titlePanelCMYK.add(titleLabelCMYK );

       
        colorPanelCMYK .add(titlePanelCMYK , BorderLayout.NORTH);
        //colorPanelCMYK .add(canvasCMYK , BorderLayout.CENTER);
        colorPanelRGB.add(titlePanelCMYK,BorderLayout.SOUTH);
        
        
        
        
        
        
        controlPanel.add(colorPanelRGB);
        
        //controlPanel.add(colorPanelCMYK);
     
        

        
        
        RGBPanel.add(redSlider);
        RGBPanel.add(greenSlider);
        RGBPanel.add(blueSlider);
        
        CMYKPanel.add(magentaSlider);
        CMYKPanel.add(cyanSlider);
        CMYKPanel.add(yellowSlider);
        CMYKPanel.add(keySlider);
        
        
            ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateColor(0);
            }
        };
   
            
        redSlider.addChangeListener(sliderListener);
        greenSlider.addChangeListener(sliderListener);
        blueSlider.addChangeListener(sliderListener);
        cyanSlider.addChangeListener(sliderListener);
        magentaSlider.addChangeListener(sliderListener);
        yellowSlider.addChangeListener(sliderListener);
        keySlider.addChangeListener(sliderListener);
        
        addDocumentListener(redTextField, "Red",0);
        addDocumentListener(greenTextField, "Green",0);
        addDocumentListener(blueTextField, "Blue",0);
        addDocumentListener(cyanTextField, "Cyan",1);
        addDocumentListener(magentaTextField, "Magenta",1);
        addDocumentListener(yellowTextField, "Yellow",1);
        addDocumentListener(blackTextField, "Key",1);
      
        
        frame.setSize(800, 600);

      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setVisible(boolean mode)
    {
        frame.setVisible(mode);
    }
        private void updateColor(int mode)
        {
            if(redSlider.getValueIsAdjusting() || blueSlider.getValueIsAdjusting() || greenSlider.getValueIsAdjusting())
            {
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                colorConverter.setRed(red);
                colorConverter.setGreen(green);
                colorConverter.setBlue(blue);
                rgbColor= new Color(colorConverter.getRed(),colorConverter.getGreen(),colorConverter.getBlue());
                colorConverter.convertRGBtoCMYK();
                float magenta =  colorConverter.getMagenta();
                float cyan =  colorConverter.getCyan();
                float yellow =  colorConverter.getYellow();
                float key =  colorConverter.getBlack();
                magentaSlider.setValue((int)(magenta));
                cyanSlider.setValue((int)(cyan));
                yellowSlider.setValue((int)(yellow));
                keySlider.setValue((int)(key));
                
            }
            else if(cyanSlider.getValueIsAdjusting() || keySlider.getValueIsAdjusting() || magentaSlider.getValueIsAdjusting() || yellowSlider.getValueIsAdjusting())
            {
         
                colorConverter.setBlack(keySlider.getValue());
                colorConverter.setCyan(cyanSlider.getValue());
                colorConverter.setYellow(yellowSlider.getValue());
                colorConverter.setMagenta(magentaSlider.getValue());
                colorConverter.convertCMYKtoRGB();
                rgbColor= new Color(colorConverter.getRed(),colorConverter.getGreen(),colorConverter.getBlue());
                redSlider.setValue((int) colorConverter.getRed());
                blueSlider.setValue((int) colorConverter.getBlue());
                greenSlider.setValue((int) colorConverter.getGreen());
   
 
            }
            else if(block==0)
            {
                if(mode==0)
                {
                    colorConverter.setRed(Integer.parseInt(redTextField.getText()));
                    colorConverter.setGreen(Integer.parseInt(greenTextField.getText()));
                    colorConverter.setBlue(Integer.parseInt(blueTextField.getText()));
                    colorConverter.convertRGBtoCMYK();
                    rgbColor= new Color(colorConverter.getRed(),colorConverter.getGreen(),colorConverter.getBlue());
                    magentaTextField.setText(Float.toString(colorConverter.getMagenta()));
                    cyanTextField.setText(Float.toString(colorConverter.getCyan()));
                    yellowTextField.setText(Float.toString(colorConverter.getYellow()));
                    blackTextField.setText(Float.toString(colorConverter.getBlack()));
                    mode=2;
                }
                else if(mode==1)
                {
                    colorConverter.setMagenta(Integer.parseInt(magentaTextField.getText()));
                    colorConverter.setCyan(Integer.parseInt(cyanTextField.getText()));
                    colorConverter.setBlack(Integer.parseInt(blackTextField.getText()));
                    colorConverter.setYellow(Integer.parseInt(yellowTextField.getText()));
                    colorConverter.convertCMYKtoRGB();
                    rgbColor= new Color(colorConverter.getRed(),colorConverter.getGreen(),colorConverter.getBlue());

                    redTextField.setText(Float.toString(colorConverter.getRed()));
                    blueTextField.setText(Float.toString(colorConverter.getBlue()));
                    greenTextField.setText(Float.toString(colorConverter.getGreen()));
                    mode=2;
            
                }
            
              
            }
                titleLabelRGB.setText("R:"+colorConverter.getRed()+" G: "+colorConverter.getGreen()+" B: "+colorConverter.getBlue());
                titleLabelCMYK.setText("C:"+colorConverter.getCyan()+" M: "+colorConverter.getMagenta()+" Y: "+colorConverter.getYellow()+" K: "+colorConverter.getBlack());
                canvasRGB.setBackground(rgbColor);
                block=1;
        }
     
        private JSlider createSlider(String label, int min, int max) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, min);
        slider.setMajorTickSpacing((max - min) / 5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }
        
   private JPanel createColorPanel(String title, Color color) {
        JPanel colorPanel = new JPanel(new BorderLayout());
        Canvas canvas = new Canvas();
        canvas.setBackground(color);

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(title);
        titlePanel.add(titleLabel);

        colorPanel.add(titlePanel, BorderLayout.NORTH);
        colorPanel.add(canvas, BorderLayout.CENTER);

        return colorPanel;
    }
    private JTextField createTextField(String label) {
        JTextField textField = new JTextField(10);
        textField.setText("0");
        
        textField.setBorder(BorderFactory.createTitledBorder(label));
        
        return textField;
    }
    
    
   // Modify your handleTextChange method to store the value to update later
private void handleTextChange(JTextField textField, String label,int mode) {
    if (textField.isFocusOwner()) {
        String newValue = textField.getText().trim();
        if (!newValue.isEmpty()) {
            try {
                String prevValue=null;
                float floatValue = Float.parseFloat(newValue);
                if (floatValue >= 0 && floatValue <= 255) {
                    if (!newValue.equals(prevValue)) {
                        prevValue = newValue;
                        // No need to updateColor here
                        // It will be updated in a separate method
                        block = 0;
                        updateColor(mode);
                        System.out.println(label + " changed: " + newValue);
                    }
                } else {
                    System.out.println("Invalid " + label + " input: " + newValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid " + label + " input: " + newValue);
            }
        }
    }
}

// Add a method to update the text fields
private void updateTextFields(int mode) {
    if (mode == 0) {
        colorConverter.setRed(Integer.parseInt(redTextField.getText()));
        colorConverter.setGreen(Integer.parseInt(greenTextField.getText()));
        colorConverter.setBlue(Integer.parseInt(blueTextField.getText()));
        colorConverter.convertRGBtoCMYK();
        rgbColor = new Color(colorConverter.getRed(), colorConverter.getGreen(), colorConverter.getBlue());
        magentaTextField.setText(String.valueOf((int) colorConverter.getMagenta()));
        cyanTextField.setText(String.valueOf((int) colorConverter.getCyan()));
        yellowTextField.setText(String.valueOf((int) colorConverter.getYellow()));
        blackTextField.setText(String.valueOf((int) colorConverter.getBlack()));
        mode = 2;
    } else if (mode == 1) {
        colorConverter.setMagenta(Integer.parseInt(magentaTextField.getText()));
        colorConverter.setCyan(Integer.parseInt(cyanTextField.getText()));
        colorConverter.setBlack(Integer.parseInt(blackTextField.getText()));
        colorConverter.setYellow(Integer.parseInt(yellowTextField.getText()));
        colorConverter.convertCMYKtoRGB();
        redTextField.setText(String.valueOf((int) colorConverter.getRed()));
        blueTextField.setText(String.valueOf((int) colorConverter.getBlue()));
        greenTextField.setText(String.valueOf((int) colorConverter.getGreen()));
        mode = 2;
    }
}

// Modify your document listener to call the updateTextFields method
private void addDocumentListener(JTextField textField, String label, int mode) {
    textField.getDocument().addDocumentListener(new DocumentListener() {
        private String prevValue = textField.getText();

        @Override
        public void insertUpdate(DocumentEvent e) {
            handleTextChange(textField, label,mode);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleTextChange(textField, label,mode);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // handleTextChange(textField, label);
        }
    });

    textField.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            updateTextFields(mode);
        }
    });
}

    
    
    
    
    
    
    
}


