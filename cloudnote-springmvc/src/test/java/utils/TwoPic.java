package utils;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/********图片水印效果************/
public class TwoPic
{
    public void createOneByTwo()
    {
        BufferedImage bi1 = null;
        int x = 10;
        int y = 10;
        BufferedImage bi2 = null;
        try
        {
            bi1 = javax.imageio.ImageIO.read(new File("d:/AU-wp1.jpg"));
            bi2 = javax.imageio.ImageIO.read(new File("d:/AU-wp2.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Graphics g = bi1.getGraphics();
        // g.drawImage(bi2, x, y, null);
        g.drawImage(bi2, bi1.getWidth() - bi2.getWidth() - 10,
                    bi1.getHeight() - bi2.getHeight() - 10, null);
        FileOutputStream out = null; //输出到文件流
        try
        {
            out = new FileOutputStream("d:\\p12.jpg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi1);
            param.setQuality(1f, false);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bi1);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
// 　　p1背景图，p2印章，p为输出图。p2要是gif或png等支持透明图象的。
//
// 　　x,y 为在背景图放置印章的坐标（ 左上角开始算）。
//
//
// 　　在Java2D中设置画图的透明度：
        float alpha = 0.5f; // 透明度
        Graphics2D g2d = bi2.createGraphics();
        // 设置透明
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_ATOP, alpha)); // 开始
        // g2d.drawImage(); // 画图
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER)); // 结束
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        TwoPic twoPic = new TwoPic();
        twoPic.createOneByTwo();
    }
}