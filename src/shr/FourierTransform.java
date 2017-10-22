package shr;

public class FourierTransform {

    public static final int Fs = 20000;
    public static final double T = 1/Fs;
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
