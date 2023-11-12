/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafikakomputerowaprojekt;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author poker
 */
public class ImageModifier {
    
    private int[] histogram = new int[256];
    private int threshold = 0;
    
    public int getThreshold()
    {
        return threshold;
    }
    public BufferedImage convertToGrayScale(BufferedImage image,int mode)
    {
        int avg=0;
        BufferedImage grayScaleImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = pixel & 0xFF;
                
                if(mode==1)
                {
                    avg = (r + g + b) / 3;
                }
                
                if(mode==2)
                {
                    avg=(int) (0.216*r + 0.7153*g + 0.0722*b);
                }
                

                int grayPixel = (avg << 16) | (avg << 8) | avg;
                    
                grayScaleImage.setRGB(j,i, grayPixel);
                
            }
        }
        return grayScaleImage;
        
    }
    public BufferedImage changeBrightness(BufferedImage image, float factor)
    {
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = Math.min((int) (((pixel >> 16) & 0xFF)*factor),255);
                int g = Math.min((int) (((pixel >> 8) & 0xFF)*factor),255);
                int b = Math.min((int) ((pixel & 0xFF)*factor),255);
                
                int alteredPixel = (r << 16) | (g << 8) | b;
                    
                alteredImage.setRGB(j,i, alteredPixel);
                
            }
        }
        return alteredImage;
    }
    
    public BufferedImage addition(BufferedImage image,BufferedImage image2)
    {           
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = ((pixel >> 16) & 0xFF);
                int g = ((pixel >> 8) & 0xFF);
                int b = (pixel  & 0xFF);
                int pixel2=image2.getRGB(j, i);
                int r2 = ((pixel2 >> 16) & 0xFF);
                int g2 = ((pixel2 >> 8) & 0xFF);
                int b2 = (pixel2  & 0xFF);
                
                r=Math.min(255,r+r2);
                g=Math.min(255,g+g2);
                b=Math.min(255,b+b2);
       
                int alteredPixel = (r << 16) | (g << 8) | b;
                    
                alteredImage.setRGB(j,i, alteredPixel);
                
            }
        }
        return alteredImage;
    }
    public BufferedImage substraction(BufferedImage image,BufferedImage image2)
    {           
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = ((pixel >> 16) & 0xFF);
                int g = ((pixel >> 8) & 0xFF);
                int b = (pixel  & 0xFF);
                int pixel2=image2.getRGB(j, i);
                int r2 = ((pixel2 >> 16) & 0xFF);
                int g2 = ((pixel2 >> 8) & 0xFF);
                int b2 = (pixel2  & 0xFF);
                
                r=Math.max(0,r-r2);
                g=Math.max(0,g-g2);
                b=Math.max(0,b-b2);
            
                int alteredPixel = (r << 16) | (g << 8) | b;
                    
                alteredImage.setRGB(j,i, alteredPixel);
                
            }
        }
        return alteredImage;
    }
      
    public BufferedImage multiplication(BufferedImage image,BufferedImage image2)
    {           
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = ((pixel >> 16) & 0xFF);
                int g = ((pixel >> 8) & 0xFF);
                int b = (pixel  & 0xFF);
                int pixel2=image2.getRGB(j, i);
                int r2 = ((pixel2 >> 16) & 0xFF);
                int g2 = ((pixel2 >> 8) & 0xFF);
                int b2 = (pixel2  & 0xFF);
                
                r=(r*r2)/255;
                g=(g*g2)/255;
                b=(b*b2)/255;
                    
                int alteredPixel = (r << 16) | (g << 8) | b;
                    
                alteredImage.setRGB(j,i, alteredPixel);
                
            }
        }
        return alteredImage;
    }
      
    public BufferedImage division(BufferedImage image,BufferedImage image2)
    {           
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
        {
            for(int j=0;j<image.getWidth();j++)
            {
                int pixel=image.getRGB(j, i);
                int r = ((pixel >> 16) & 0xFF);
                int g = ((pixel >> 8) & 0xFF);
                int b = (pixel  & 0xFF);
                int pixel2=image2.getRGB(j, i);
                int r2 = ((pixel2 >> 16) & 0xFF);
                int g2 = ((pixel2 >> 8) & 0xFF);
                int b2 = (pixel2  & 0xFF);
                
                r=(r2!=0) ? (r/r2)*255 : 0;
                g=(g2!=0) ? (g/g2)*255 :0;
                b=(b2!=0) ? (b/b2)*255 :0;
                
                int alteredPixel = (r << 16) | (g << 8) | b;
                    
                alteredImage.setRGB(j,i, alteredPixel);
                
            }
        }
        return alteredImage;
    }
    public BufferedImage averageFilter(BufferedImage image)
    {
           BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
            int pixel = getAverageNearbyPixel(image, x, y);
            alteredImage.setRGB(x, y, pixel);
        }
    }
        return alteredImage;
    }
    
    private int getAverageNearbyPixel(BufferedImage image,int x,int y)
    {
        int pixel=0;
        int average=0;
        int count =0;
        int avgR = 0, avgG = 0, avgB = 0;
        
        for (int i=-1;i<=3;i++)
        {
            for(int j=-1;j<=3;j++)
            {
                if(x+i<image.getWidth() && y+j<image.getHeight() && x+i>=0 && y+j>=0)
                {
                    pixel=image.getRGB(x+i, y+j);
                    avgR += (pixel >> 16) & 0xFF;
                    avgG += (pixel >> 8) & 0xFF;
                    avgB += pixel & 0xFF;
                    count++;
                }
                
            }
        }
        avgR /= count;
        avgG /= count;
        avgB /= count;

        average = (avgR << 16) | (avgG << 8) | avgB;
        return average;
    }

    public BufferedImage medianFilter(BufferedImage image)
    {
        BufferedImage alteredImage =new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) 
        {
            for (int x = 0; x < image.getWidth(); x++) 
            {
                int pixel = getMedianNearbyPixel(image, x, y);
                alteredImage.setRGB(x, y, pixel);
            }
        }
        return alteredImage;
    }
    private int getMedianNearbyPixel(BufferedImage image,int x,int y)
    {
       
        LinkedList<Integer> pixelList = new LinkedList();
        
        
        for (int i=-1;i<=4;i++)
        {
            for(int j=-1;j<=4;j++)
            {
                if(x+i<image.getWidth() && y+j<image.getHeight() && x+i>=0 && y+j>=0)
                {
                    pixelList.add(image.getRGB(x+i, y+j));
                }
                
            }
        }
        Collections.sort(pixelList);
        
        int median= pixelList.get(pixelList.size()/2);
        return median;
    }
    
   public BufferedImage sobelFilter(BufferedImage image) 
   {
       BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

       int[][] kernelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
       int[][] kernelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};

       for (int y = 1; y < image.getHeight() - 1; y++) 
       {
            for (int x = 1; x < image.getWidth() - 1; x++) 
            {
                int sumX = 0;
                int sumY = 0;

                for (int j = -1; j <= 1; j++) 
                {
                    for (int i = -1; i <= 1; i++) 
                    {
                        int pixel = image.getRGB(x + i, y + j);
                        int grayValue = (pixel >> 16) & 0xFF; 

                        sumX += grayValue * kernelX[j + 1][i + 1];
                        sumY += grayValue * kernelY[j + 1][i + 1];
                    }
                }

                int magnitude = (int) Math.sqrt(sumX * sumX + sumY * sumY);
                int newPixel = (255 << 24) | (magnitude << 16) | (magnitude << 8) | magnitude;

                alteredImage.setRGB(x, y, newPixel);
            }
        }

        return alteredImage;
    }
   
   
   public BufferedImage gaussianBlur(BufferedImage image)
   {
       BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    
 
       double[][] kernel = 
       {
        {1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0},
        {2.0 / 16.0, 4.0 / 16.0, 2.0 / 16.0},
        {1.0 / 16.0, 2.0 / 16.0, 1.0 / 16.0}
       };

       for (int y = 1; y < image.getHeight() - 1; y++) 
       {
           for (int x = 1; x < image.getWidth() - 1; x++)
           {
                int sumR = 0;
                int sumG = 0;
                int sumB = 0;

                for (int j = -1; j <= 1; j++) 
                {
                    for (int i = -1; i <= 1; i++) 
                        {
                            int pixel = image.getRGB(x + i, y + j);
                            sumR += ((pixel >> 16) & 0xFF) * kernel[j + 1][i + 1];
                            sumG += ((pixel >> 8) & 0xFF) * kernel[j + 1][i + 1];
                            sumB += (pixel & 0xFF) * kernel[j + 1][i + 1];
                        }
                }

                int newR = (int) Math.min(Math.max(0, sumR), 255);
                int newG = (int) Math.min(Math.max(0, sumG), 255);
                int newB = (int) Math.min(Math.max(0, sumB), 255);

                int newPixel = (255 << 24) | (newR << 16) | (newG << 8) | newB;

                alteredImage.setRGB(x, y, newPixel);
            }
       }

        return alteredImage;
    }
   public int[] createHistogram(BufferedImage image)
   {
       histogram = new int[256];
       for (int y = 0; y < image.getHeight(); y++) 
       {
            for (int x = 0; x < image.getWidth(); x++) 
            {
                int pixel=image.getRGB(x, y);
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = pixel & 0xFF;   
                int avg=(int) (0.216*r + 0.7153*g + 0.0722*b);
                
                //avg = Math.min(255, Math.max(0, avg));
                histogram[avg]++;

            }
       }
       this.histogram=histogram;
       return histogram;
   }
   
   public BufferedImage highPassSharpening(BufferedImage image) {
    BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    int[][] kernel = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};

    for (int y = 1; y < image.getHeight() - 1; y++) {
        for (int x = 1; x < image.getWidth() - 1; x++) {
            int sumR = 0;
            int sumG = 0;
            int sumB = 0;
            int pixel=0;

            for (int j = -1; j <= 1; j++) {
                for (int i = -1; i <= 1; i++) {
                    pixel = image.getRGB(x + i, y + j);
                    sumR += ((pixel >> 16) & 0xFF) * kernel[j + 1][i + 1];
                    sumG += ((pixel >> 8) & 0xFF) * kernel[j + 1][i + 1];
                    sumB += (pixel & 0xFF) * kernel[j + 1][i + 1];
                }
            }

            int newR = Math.min(Math.max(0, ((pixel >> 16) & 0xFF) + sumR), 255);
            int newG = Math.min(Math.max(0, ((pixel >> 8) & 0xFF) + sumG), 255);
            int newB = Math.min(Math.max(0, (pixel & 0xFF) + sumB), 255);

            int newPixel = (255 << 24) | (newR << 16) | (newG << 8) | newB;

            alteredImage.setRGB(x, y, newPixel);
        }
    }

    return alteredImage;
}
   public BufferedImage normalizeImage(BufferedImage image,int mode)
   {
        BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        int min=255;
        int max=0;
        int width=image.getWidth();
        int height = image.getHeight();
    
        

        
        for(int i=0;i<image.getHeight();i++)
            {
                for(int j=0;j<image.getWidth();j++)
                   {
                       int pixel=image.getRGB(j, i);
            
                       if(pixel<min)
                       {
                           min=pixel;
                       }
                       else if(pixel>max)
                       {
                           max=pixel;
                       }
                   }
               }
        
  
             int cdf=0;
        int[] equalizedHistogram = new int[256];
          for (int i = 0; i < 256; i++) {
        cdf += histogram[i];
        equalizedHistogram[i] = (int) (255.0 * cdf / (height*width));
    }
        
        for(int i=0;i<image.getHeight();i++)
            {
                for(int j=0;j<image.getWidth();j++)
                   {
                       int pixel=image.getRGB(j, i);
                       int alteredPixel=pixel;
                       int r = (pixel >> 16) & 0xFF;
                       int g = (pixel >> 8) & 0xFF;
                       int b = pixel & 0xFF;   
                       int avg=(int) (0.216*r + 0.7153*g + 0.0722*b);
                       
                       if(max==min)
                       {
                           alteredPixel=pixel;
                       }
                       else
                       {
                            if(mode==1)
                            {
                                
                                alteredPixel=(int) ((255.0/(max-min))*(pixel-min));
                            }
                            else if(mode==2)
                            {
                                alteredPixel = equalizedHistogram[avg];
                            
                            }
                            
                            
                       }
                      

                                   alteredImage.setRGB(j, i, (alteredPixel << 16) | (alteredPixel << 8) | alteredPixel); 
                   }
               }
        return alteredImage;
   }
 

   public BufferedImage binarization(BufferedImage image,int level)
   {   
       BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
            {
                for(int j=0;j<image.getWidth();j++)
                   {
                       int pixel=image.getRGB(j, i);
                       int r = (pixel >> 16) & 0xFF;
                       int g = (pixel >> 8) & 0xFF;
                       int b = pixel & 0xFF;   
                       int avg=(int) (0.216*r + 0.7153*g + 0.0722*b);
                       if(avg<level)
                       {
                           avg=0;
                       }
                       else
                       {
                           avg=255;
                       }
                       alteredImage.setRGB(j, i, (avg << 16) | (avg << 8) | avg); 

                   }
            }
       return alteredImage;
   }
     public BufferedImage selectDarkest(BufferedImage image,int level)
   {   
       level = (level) * (image.getWidth() * image.getHeight())/100;
       int darkestPixels[][] = new int[image.getWidth()*image.getHeight()][4];
       int count=0;
           System.out.println(level);
       BufferedImage alteredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i=0;i<image.getHeight();i++)
            {
                for(int j=0;j<image.getWidth();j++)
                   {
                       int pixel=image.getRGB(j, i);
                       
                       int r = (pixel >> 16) & 0xFF;
                       int g = (pixel >> 8) & 0xFF;
                       int b = pixel & 0xFF;   
                       int avg=(int) (0.216*r + 0.7153*g + 0.0722*b);
                       darkestPixels[count][0]=avg;
                       darkestPixels[count][1]=j;
                       darkestPixels[count][2]=i;
                       darkestPixels[count][3]=pixel;
                       alteredImage.setRGB(j,i,255<<16 | 255<<8 | 255);
                       count++;
                       

                   }
            }
        columnWiseSorting(darkestPixels,1);
        
    
        for(count=0;count<level;count++)
        {
            int j =darkestPixels[count][1];
            int i =darkestPixels[count][2];
         
            alteredImage.setRGB(j, i, darkestPixels[count][3]);

        }
       
                       
                      
                   
                
            
        
         
        
        
       return alteredImage;
   }
     
    private void columnWiseSorting(int arr[][], int colmn) {
    Arrays.sort(arr, new Comparator<int[]>() {
        @Override
        public int compare(int[] frst, int[] scnd) {
            int comparison = Integer.compare(frst[colmn - 1], scnd[colmn - 1]);
            if (comparison != 0) {
                return comparison;
            }

            return Integer.compare(frst[0], scnd[0]);
        }
    });
}
    private static double calculateMedian(int[] histogram, int start, int end) 
    {
        int sum = 0;
        int count = 0;

        for (int i = start; i <= end; i++) 
        {
            sum += i * histogram[i];
            count += histogram[i];
        }

        return (count == 0) ? 0 : (double) sum / count;
    }
    public BufferedImage iterativeMeanSelection(BufferedImage image)
    {
        int initialThreshold=130;
        for(int i=0;i<100;i++)
        {
            double meanBackground = calculateMedian(histogram, 0, initialThreshold);
            double meanForeground = calculateMedian(histogram, initialThreshold + 1, histogram.length - 1);
            initialThreshold = (int) ((meanBackground + meanForeground) / 2);
        }
        this.threshold=initialThreshold;
        return binarization(image,initialThreshold);

    }
        
        
    }
    

  



    


