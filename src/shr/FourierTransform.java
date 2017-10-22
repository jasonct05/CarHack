package shr;

import java.util.*;

public class FourierTransform {

    public static final int Fs = 3000;
    public static final int INPUT_LENGTH = 8192;
    public static final int L = 2730;
    public static final int n = 15;

    public static final int MAX_FREQ = 2000;
    public static final int RANGE = 20;


    public static double[] doTransform(int[] input) {
        // sanity check
        Complex[] fftArray = FFT.fft(createComplexArray(input));
        double[] amplitude = getAmplitudes(fftArray);
        return amplitude;
    }

    private static double[] plot(double[] amplitude, double[] frequency) {
        if (amplitude.length != frequency.length) {
            throw new IllegalArgumentException();
        }
        double[] plotTable = new double[MAX_FREQ / RANGE];
        for (int i = 0; i < amplitude.length; i++) {
            if (frequency[i] <= MAX_FREQ) {
                plotTable[(int)((frequency[i] - 1) / RANGE)] += amplitude[i];
            }
        }
        return plotTable;
    }

    private static Complex[] createComplexArray(int[] input) {
        Complex[] complexArr = new Complex[input.length];
        for (int i = 0; i < input.length; i++) {
            complexArr[i] = new Complex(input[i], 0.0);
        }

        return complexArr;
    }

    private static double[] getAmplitudes(Complex[] input) {
        double[] amplitude = new double[input.length];
        for (int i = 0; i < amplitude.length; i++) {
            amplitude[i] = input[i].divides(new Complex(15, 0)).abs();
        }
        return amplitude;
    }

    private static double[] getFrequency(Complex[] fftArray) {
        double[] frequency = new double[fftArray.length];
        for (int i = 0; i < frequency.length; i++) {
            frequency[i] = Fs * fftArray[i].abs() / n;
        }
        return frequency;
    }
}
