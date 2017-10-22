package shr;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.*;

public class ImageProcessing {
    public static final int length = 4096 / 4;
    public static final int height = 400;

    public static String generateAndSaveImage(Color[] colorArray) {
        // draw the image
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SoundEncodedPicture pic = new SoundEncodedPicture(colorArray, length, height);
        pic.setPreferredSize(new Dimension(length ,height));
        frame.add(pic, BorderLayout.CENTER);

        // pack layout to natural sizes of components, then display
        frame.pack();
        frame.setVisible(true);

        // save the image
        BufferedImage image = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        frame.paint(graphics2D);

        Random r = new Random();
        String filePath = "src/data/train/test_data" + r.nextInt(1000) + ".jpg";
        try {
            ImageIO.write(image, "jpg", new File(filePath));
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e);
        }
        frame.dispose();
        return filePath;
    }
}
