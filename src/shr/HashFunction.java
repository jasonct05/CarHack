package shr;

import java.awt.Color;

public class HashFunction {

    public static Color[] hashArray(int[] input) {
        Color[] result = new Color[input.length];

        for (int i = 0; i < input.length; i++) {
            result[i] = hash(input[i]);
        }

        return result;
    }

    public static Color hash(int input) {
        return new Color(redFunction(input), greenFunction(input), blueFunction(input));
    }

    public static int greenFunction(int amp) {
        return (int) (Math.sqrt(amp) * 5) % 255;
    }

    public static int redFunction(int amp) {
        return (amp * 10) % 255;
    }

    public static int blueFunction(int amp) {
        return (amp * amp) % 255;
    }
}
