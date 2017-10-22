package shr;

public class FourierTransform {

    public static final int Fs = 20000;
    public static final double T = 1/Fs;
    public static final int L = 2000;


    public int[] doTransform(int[] input) {

        Complex[] intToComplex = new Complex[input.length];

        for(int i = 0; i < input.length; i++) {
            intToComplex[i] = new Complex(input[i], 0.0);
        }

        Complex[] fftArray =  FFT.fft(intToComplex);

        double[] amplitude = new double[L/2];

        for(int i = 0; i < amplitude.length; i++) {
            amplitude[i] = 2 * fftArray[i].abs() / L;
        }

        double[] frequency = new double[L/2];

        for(int i = 0; i < frequency.length; i++) {
            frequency[i] = Fs * fftArray[i].abs() / L;
        }
    }


}
