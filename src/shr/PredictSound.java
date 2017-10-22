package shr;

import java.awt.Color;
import java.util.*;

public class PredictSound {
    // totalTime is in ms
    public static final int interval = 50; //50ms
    public static final int smoothing = 3;
    public static int PredictSoundFromArray(List<Integer> input, int totalTime) {
        int average = findAverage(input); // average amplitude
        int inputInterval = totalTime / input.size();
        int sampleSize = interval / inputInterval;
        int numberOfBlocks = totalTime / interval;
        int[] frequencyResults = new int[numberOfBlocks];
        for(int i = 0; i < numberOfBlocks; i++) {
            int[] elements = new int[sampleSize];
            for(int j = 0; j < sampleSize; j++) {
                elements[j] = input.get(i * sampleSize + j);
                frequencyResults[i] = findFrequency(elements, average);
            }
        }

        ImageProcessing.generateAndSaveImage(HashFunction.hashArray(frequencyResults));

        // todo: fix machine learning algorithm right here
        // {0: Police, 1: Firetruck, 2: Car, 3:Unclassified}
        return 0;
    }

    public static int findAverage(List<Integer> input) {
        int total = 0;
        for(int i = 0; i < input.size(); i++) {
            total += input.get(i);
        }
        return total / input.size();
    }

    public static int findFrequency(int[] elements, int average) {
        // 1, 2, 3, 4, 5, 6
        int count = 0;
        for (int i = 0; i <= elements.length - (smoothing * 2); i++) {
            int beforeSmoothing = 0;
            for(int j = 0; j < smoothing; j++) {
                beforeSmoothing += elements[i];
            }
            beforeSmoothing /= smoothing;

            int afterSmoothing = 0;
            for(int j = smoothing; j < smoothing * 2; j++) {
                afterSmoothing += elements[i];
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
