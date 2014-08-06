
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author ilrxx
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgBean{ 
public void ImgBean(){} 
public static void ImgYin(String str,String ImgName){ 
    byte[] bytes = null; 
    try{ 
        File _file = new File(ImgName); 
        Image src = ImageIO.read(_file); 
        int wideth=src.getWidth(null); 
        int height=src.getHeight(null); 
        BufferedImage image=new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB); 
        Graphics g=image.createGraphics(); 
        g.drawImage(src,0,0,wideth,height,null); 
        g.setColor(Color.RED); 
        g.setFont(new Font("宋体",Font.PLAIN,20)); 
        g.drawString(str,wideth-150,height-10); 
        g.dispose(); 
        ByteArrayOutputStream out1 = new ByteArrayOutputStream();
        saveImage(image, out1);
        bytes = out1.toByteArray();
        out1.close();
        FileOutputStream out2 = new FileOutputStream(ImgName);
        out2.write(bytes);
        out2.close();
    } 
    catch(Exception e){ 
        System.out.println(e); 
    } 
} 
public static void saveImage(BufferedImage img, OutputStream out1) throws Exception {
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out1);
    encoder.encode(img);
}

public static void main(String[] args){
    ImgYin("我要加的水印" , "d:/AU-wp1.jpg");
}
}

