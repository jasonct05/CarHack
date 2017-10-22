package shr;

import java.awt.Color;
public class PredictSound {
    public static int PredictSoundFromArray(int[] input) {
        Color[] hashedColor = HashFunction.hashArray(input);
        ImageProcessing.generateAndSaveImage(HashFunction.hashArray(input));

        // todo: fix machine learning algorithm right here
        // {0: Police, 1: Firetruck, 2: Car, 3:Unclassified}
        return 0;
    }
}
