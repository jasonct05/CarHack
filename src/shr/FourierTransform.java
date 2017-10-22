package shr;

public class FourierTransform {

    public static final int Fs = 20000;
    public static final double T = 1 / Fs;
    public static final int L = 6000;
    public static final int n = 15;
    public static final int INPUT_LENGTH = 32768;


    public int[] doTransform(int[] input) {
        // sanity check
        if (input < INPUT_LENGTH) {
            throw new IllegalArgumentException();
        }

        Complex[] fftArray = FFT.fft(createComplexArray(input));
        double[] amplitude = getAmplitudes(fftArray);
        double[] frequency = getFrequency(fftArray);
    }

    /*
    public static final int MAX_FREQ = 2000;
    public static final int RANGE = 20;
    public static final int INDEX = amplitude.length;

    double[] plotTable = new double[MAX_FREQ / RANGE];

    for(int i = 0; i < INDEX; i++) {
        if (frequency[i] <= MAX_FREQ) {
            plotTable[(frequency[i] - 1) / RANGE] = amplitude[i];
        }
    }
    */

    private static Complex[] createComplexArray(int[] input) {
        Complex[] complexArr = new Complex[INPUT_LENGTH];
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
        double[] frequency = new double[L / 2];
        for (int i = 0; i < frequency.length; i++) {
            frequency[i] = Fs * fftArray[i].abs() / n;
        }
        return frequency;
    }
}
