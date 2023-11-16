/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import static org.apache.commons.math3.util.ArithmeticUtils.binomialCoefficient;

/**
 *
 * @author poker
 */
public class BezierCurve extends Shape{

    Color color;
    LinkedList<Point> controlPoints;
    int x1,x2,y1,y2;
    int foundPoint;
    int lol=0;
   
    int[] xDimension = new int[100];
    int[] yDimension = new int[100];
    public BezierCurve(int x1,int y1,int x2,int y2) {
        this.controlPoints = new LinkedList<>();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        controlPoints.add(new Point(x1,y1));
        controlPoints.add(new Point(x2,y2));
    }
    public BezierCurve()
    {
        
    }
    
    public void addControlPoint(Point p)
    {
        Point temp = controlPoints.getLast();
        controlPoints.removeLast();
        controlPoints.add(p);
        controlPoints.add(temp);
    }
    public void removeControlPoint(Point p)
    {
        controlPoints.remove(p);
    }
    
    @Override
    public void draw(Graphics g) 
    {
       
        if(lol==0)
        {
            lol=1;
            controlPoints.removeLast();
        }
        calculateDimensions();
        for(int i=0;i<controlPoints.size();i++)
        {
            g.setColor(Color.red);
           
            g.drawOval(controlPoints.get(i).x,controlPoints.get(i).y , 4,4);
            g.fillOval(controlPoints.get(i).x,controlPoints.get(i).y , 4,4);
        }
        g.setColor(color);
        g.drawPolyline(xDimension, yDimension,yDimension.length );
     
    }
    
   public void calculateDimensions() {
    int n = controlPoints.size() - 1;
    int numPoints = 50;

    xDimension = new int[numPoints];
    yDimension = new int[numPoints];

    for (int i = 0; i < numPoints; i++) {
        double t = i / (double) (numPoints - 1);

        int x = 0;
        int y = 0;

        for (int j = 0; j <= n; j++) {
        
            double bernstein = binomialCoefficient(n, j) * Math.pow(1 - t, n - j) * Math.pow(t, j);
            x += bernstein * controlPoints.get(j).x;
            y += bernstein * controlPoints.get(j).y;
        }

        xDimension[i] = x;
        yDimension[i] = y;
    }
}

private static int binomialCoefficient(int n, int k) {
    if (k == 0 || k == n) {
        return 1;
    } else {
        return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
    }
}
    @Override
    public void move(int x, int y) {
        System.out.println(controlPoints.size());
        controlPoints.get(foundPoint).x=controlPoints.get(foundPoint).x+x;
        controlPoints.get(foundPoint).y=controlPoints.get(foundPoint).y+y;
     
    }

    @Override
    public void resize(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean position(int x, int y) {
        int distanceThreshold = 5;
        for(int i=0;i<controlPoints.size();i++)
        {
            
            if(Math.abs(x-controlPoints.get(i).x)<=distanceThreshold && Math.abs(y-controlPoints.get(i).y)<=distanceThreshold)
            {
                foundPoint=i;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public int getX2() {
        return x2;
    }

    @Override
    public int getY2() {
        return y2;
    }
     @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color=color;
    }

    
}
