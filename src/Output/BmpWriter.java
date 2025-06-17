package Output;

import javax.imageio.ImageIO;

import Image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BmpWriter {
	
	public static String CreateBMPFIle(Image imageToPrint, String name) {
		int width = imageToPrint.getWidth();
        int height = imageToPrint.getHeight();

        // Create a BufferedImage (TYPE_INT_RGB supports 24-bit color)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Fill image with a color (e.g., solid red)
        // RGB red (0xFF0000)
        
        Image.Pixel[][] pixels = imageToPrint.getPixels();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	
            	Image.Pixel currPixel = pixels[x][y];
            	
            	// 0xRRGGBB
            	int rgb = ((int)(currPixel.r*255)<< 16) | ((int)(currPixel.g*255)<<8) | (int)(currPixel.b*255);
            	image.setRGB(x, y, rgb);

            }
        }

        // Save as BMP
        
       
        try {
  
            File outputFile = new File(name+".bmp");
            ImageIO.write(image, "bmp", outputFile);
            System.out.println("BMP file created: " + outputFile.getAbsolutePath());
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}

	
}

