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
    public static final int length = 4096 * 2;
    public static final int height = 400;

    public static void generateAndSaveImage(Color[] colorArray) {
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

        final String dir = System.getProperty("user.dir");
        Random r = new Random();
        try {
            ImageIO.write(image, "jpg", new File("data/train/SAMPLE_OUTPUT.jpg")); // + r.nextInt(1000) + ".jpg"));
        } catch (Exception e) {
            System.err.println("Caught Exception: " + e);
        }
        frame.dispose();
    }
}
