package shr;

import java.awt.Color;
import java.util.*;

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
        int[] roundedFourierResults = new int[fourierResults.length / 2];
        for(int i = 0; i < roundedFourierResults.length / 2; i++) {
            roundedFourierResults[i] = (int)(Math.round(fourierResults[i]));
        }
        ImageProcessing.generateAndSaveImage(HashFunction.hashArray(roundedFourierResults));
        // todo: fix machine learning algorithm right here
        // {0: Police, 1: Firetruck, 2: Car, 3:Unclassified}
        return 0;
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
        //System.out.println(count);
        return count;
    }
}
