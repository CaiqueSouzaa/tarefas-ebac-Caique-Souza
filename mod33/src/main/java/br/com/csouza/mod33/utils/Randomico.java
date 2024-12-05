package br.com.csouza.mod33.utils;

import java.util.Random;

public class Randomico {
    private static final Random random = new Random();
    private static final String abc = "abcdefghijklmopqrstuvwxyz";

    public static String text(int size) {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length())));
        }

        return sb.toString();
    }

    public static Double rDouble() {
        return random.nextDouble(1000);
    }
}
