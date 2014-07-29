package com.ces.cloud.note.base.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
  
public class GenerateImage {   
    
    
    public static  void generateImage(String sourceImage, String imageName, String title, String fileSize, File targetFile) throws IOException {
    	 int width = 220;   
         int height = 55;   
         
         File backgroudImage = new File(sourceImage, "attachment.png"); 
         Image bgImage = ImageIO.read(backgroudImage); 
         
         File _file = new File(imageName); 
         Image src = ImageIO.read(_file); 
            
         BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
         Graphics2D g2 = (Graphics2D)bi.getGraphics();   
         g2.setBackground(Color.WHITE);
         g2.clearRect(0, 0, width, height);   
         g2.setPaint(Color.BLUE);  
         Font defaultFont = new Font("宋体", Font.PLAIN, 12);
         g2.setFont(defaultFont);
         
         g2.drawImage(bgImage, 0, 0, 220, 55, null);
         g2.drawImage(src, 14, 10, 26, 31, null);
         byte[] titleBytes = title.getBytes();
         if(titleBytes.length > 27) {
        	 byte[] newTitleByte = new byte[24];
        	 for(int i =0; i< 24; i++) {
        		 newTitleByte[i] = titleBytes[i]; 
        	 }
        	 title = new String(newTitleByte) + "...";
         }
         g2.drawString(title, 50, 20); 
         g2.drawString(fileSize, 50, 40); 
            
         ImageIO.write(bi, "png", targetFile);   
    }
}