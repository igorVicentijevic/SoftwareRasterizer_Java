package Output;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GifMaker {
    public static void createGifFromBmps(String[] bmpPaths, String outputGifPath, int delayMs, boolean loop) throws IOException {
        if (bmpPaths.length == 0) {
            throw new IllegalArgumentException("No BMP files provided.");
        }

        BufferedImage firstImage = ImageIO.read(new File(bmpPaths[0]));
        ImageOutputStream output = ImageIO.createImageOutputStream(new File(outputGifPath));
        GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), delayMs, loop);

        writer.writeToSequence(firstImage);

        for (int i = 1; i < bmpPaths.length; i++) {
            BufferedImage nextImage = ImageIO.read(new File(bmpPaths[i]));
            writer.writeToSequence(nextImage);
        }

        writer.close();
        System.out.println("GIF created: " + outputGifPath);
    }

    public static void main(String[] args) {
        try {
            String[] bmpFrames = {
                "output/Animations/firstAnim0.bmp",
                "output/Animations/firstAnim1.bmp",
                "output/Animations/firstAnim2.bmp",
                // Add more BMP frame paths
            };
            createGifFromBmps(bmpFrames, "output.gif", 500, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

