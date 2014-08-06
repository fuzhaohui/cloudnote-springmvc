package utils;
//package com.ces.bank.communication.utils;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//
//import javax.imageio.ImageIO;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//
//public class createImg {
//
//private int name_x;//文字的x轴坐标
//       private int name_y;//文字的y轴坐标
//
//private int logo_x;//小图片的x轴坐标
//       private int logo_y;//小图片的x轴坐标
//    public createImg() {
//    }
//
//
///**
//     * 根据提供的文字生成jpg图片
//     * @param backJpgPath String 背景图片路径
//     * @param Logoimage String 小图片路径
//     * @param String s 文字信息
//     * @param jpgname String 生成的新图片名称
//     */
//    public void createJpgByFont(String backJpgPath, String Logoimage,
//                                String s, String jpgname) {
//        try { //宽度                               高度
//            File fileOne = new File(backJpgPath);
//            BufferedImage bimage = ImageIO.read(fileOne);
//            Graphics2D g = bimage.createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                               RenderingHints.VALUE_ANTIALIAS_ON); //去除锯齿(当设置的字体过大的时候,会出现锯齿)
//
//     Font font = Font.decode("宋体");
//            g.setColor(Color.decode("#FF0000")); //字的颜色
//            g.setFont(font.deriveFont(Float.parseFloat("12"))); //font.deriveFont(float f)复制当前 Font 对象并应用新设置字体的大小
//            g.drawString(s, this.getName_x(),this.getName_y()); //在指定坐标除添加文字
//
//     g.dispose();//释放资源
//
//     File filelogo = new File(Logoimage);
//            BufferedImage bimageLogo = ImageIO.read(filelogo);
//            int width = bimageLogo.getWidth(); //图片宽度
//            int height = bimageLogo.getHeight(); //图片高度
//            //从图片中读取RGB
//            int[] ImageArrayOne = new int[width * height];
//            ImageArrayOne = bimageLogo.getRGB(0, 0, width, height,
//                                              ImageArrayOne, 0, width);
//            bimage.setRGB(this.getLogo_x(), this.getLogo_y(), width, height,
//                          ImageArrayOne, 0, width);
//             FileOutputStream out = new FileOutputStream(jpgname); //指定输出文件
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
//            param.setQuality(50f, true);
//            encoder.encode(bimage, param); //存盘
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            System.out.println("createJpgByFont Failed!");
//            e.printStackTrace();
//        }
//    }
//
//
//public static void main(String[] args) {
//        createImg c = new createImg();
//        String back ="C:\\images\\backImage.jpg";
//        c.setName_x(70);
//        c.setName_y(63);
//
// c.setLogo_x(383);
//        c.setLogo_y(22);
//
// c.createJpgByFont(back,"C:\\images\\logimg.gif","哈哈，终于可以合成图片了", "C:\\images\\a.jpg");
//    }
//}