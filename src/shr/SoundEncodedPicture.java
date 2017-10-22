package shr;

import javax.swing.*;
import java.awt.*;

public class SoundEncodedPicture extends JPanel {
    private Color[] colorArray;
    private int length, height;

    public SoundEncodedPicture(Color[] color, int length, int height) {
        this.colorArray = color;
        this.length = length;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        for(int i = 0; i < this.colorArray.length; i++) {
            g2.setColor(this.colorArray[i]);
            g2.fillRect(i * (this.length / this.colorArray.length) * 4, 0, (this.length / this.colorArray.length) * 4, this.height);
        }
    }
}
