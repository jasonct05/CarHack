package shr;

import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class PredictSound {
    // totalTime is in ms
    public static final int interval = 50; //50ms
    public static final int smoothing = 1;
    public static final int POWER_INPUT = 4096;
    public static int PredictSoundFromArray(List<Integer> input) {
        int[] inputForFourier = new int[POWER_INPUT];
        for(int i = 0; i < inputForFourier.length; i++) {
            inputForFourier[i] = input.get(i);
        }

        double[] fourierResults = FourierTransform.doTransform(inputForFourier);
        int[] roundedFourierResults = new int[fourierResults.length / 4];
        for(int i = 0; i < roundedFourierResults.length / 4; i++) {
            roundedFourierResults[i] = (int)(Math.round(fourierResults[i]));
        }
        String filePath = ImageProcessing.generateAndSaveImage(HashFunction.hashArray(roundedFourierResults));

        File img = new File(filePath);
        String result = "";
        try {
            BufferedImage image = ImageIO.read(img);
            result = DetectImage.detectImage(image);
        } catch (Exception e) {
            System.out.println("ERROR");
        }

        // {0: Unknown, 1: Ambulance, 2: Car, 3:Police Sound}
        if (result.equals("ambulance-sound")) {
            return 1;
        } else if (result.equals("police-sound")) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int findAverage(int[] input) {
        int total = 0;
        for(int i = 0; i < input.length; i++) {
            total += input[i];
        }
        return total / input.length;
    }

    public static int findFrequency(int[] elements) {
        // 1, 2, 3, 4, 5, 6
        //System.out.println("TEST ELEMENT: " + Arrays.toString(elements));
        int average = findAverage(elements);
        int count = 0;
        for (int i = 0; i <= elements.length - (smoothing * 2); i++) {
            int beforeSmoothing = 0;
            for(int j = 0; j < smoothing; j++) {
                beforeSmoothing += elements[i + j];
            }
            beforeSmoothing /= smoothing;

            int afterSmoothing = 0;
            for(int j = smoothing; j < smoothing * 2; j++) {
                afterSmoothing += elements[i + j];
            }
            afterSmoothing /= smoothing;

            if (Math.signum(beforeSmoothing - average) != Math.signum(afterSmoothing - average)) {
                count++;
                i += smoothing * 2;
            }
        }
        return count;
    }
}
