package shr;

import java.awt.Color;
import java.util.*;

public class PredictSound {
    // totalTime is in ms
    public static final int interval = 50; //50ms
    public static int PredictSoundFromArray(List<Integer> input, int totalTime) {
        /*
        int average = findAverage(input); // average amplitude
        int inputInterval = totalTime / input.size();
        int sampleSize = interval / inputInterval;
        int numberOfBlocks = totalTime / interval;
        int[] frequencyInput = new int[numberOfBlocks];
        for(int i = 0; i < numberOfBlocks; i++) {
            int[] elements = new int[sampleSize];
            for(int j = i * sampleSize; j < sampleSize; j++) {
                elements[j] = input[i + j];
                findFrequency(elements, sampleSize);
            }
        }
        */






        Color[] hashedColor = HashFunction.hashArray(input);
        ImageProcessing.generateAndSaveImage(HashFunction.hashArray(input));

        // todo: fix machine learning algorithm right here
        // {0: Police, 1: Firetruck, 2: Car, 3:Unclassified}
        return 0;
    }

    public static int findAverage(List<Integer> input) {
        int total = 0;
        for(int i = 0; i < input.length; i++) {
            total += input.get(i);
        }
        return total / input.size();
    }

    public static int
}
