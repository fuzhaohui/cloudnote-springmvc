package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CreateImage {
	public static void main(String[] args) {
		generateImage("src/main/webapp/images/doc.png", "欢迎使用有道去笔记中摇滚乐要魂牵梦萦魂牵梦萦魂牵梦萦.doc", "2012/11/07 18:57, 30.50KB", "D:/image1.png");
		generateImage("src/main/webapp/images/ppt.png", "欢迎使用有道去笔记.doc", "2012/11/07 18:57, 30.50KB", "D:/image2.png");
		generateImage("src/main/webapp/images/excel.png", "欢迎使用有道去笔记.doc", "2012/11/07 18:57, 30.50KB", "D:/image3.png");
		generateImage("src/main/webapp/images/txt.png", "欢迎使用有道去笔记.doc", "2012/11/07 18:57, 30.50KB", "D:/image4.png");
		generateImage("src/main/webapp/images/pdf.png", "欢迎使用有道去笔记.doc", "2012/11/07 18:57, 30.50KB", "D:/image5.png");
	}

	public static void generateImage(String imageName, String title,
			String fileSize, String targetFile) {

		try {
			int width = 220;
			int height = 55;

			File file = new File(targetFile);

			File backgroudImage = new File("src/main/webapp/images/attachment.png");
			System.out.println(backgroudImage.getAbsolutePath());
			Image bgImage = ImageIO.read(backgroudImage);

			File _file = new File(imageName);
			Image src = ImageIO.read(_file);

			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setBackground(Color.WHITE);
			g2.clearRect(0, 0, width, height);
			g2.setPaint(Color.BLUE);
			Font defaultFont = new Font("宋体", Font.PLAIN, 12);
			g2.setFont(defaultFont);

			g2.drawImage(bgImage, 0, 0, 220, 55, null);
			g2.drawImage(src, 14, 10, 26, 31, null);

			g2.drawString(title, 50, 20);
			g2.drawString(fileSize, 50, 40);

			ImageIO.write(bi, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}