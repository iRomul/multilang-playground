package io.github.iromul.ascii;

import java.util.stream.Stream;

public class Main {
    public static void printMsgWithProgressBar(String message, int length, long timeInterval) {
        char incomplete = '░'; // U+2591 Unicode Character
        char complete = '█'; // U+2588 Unicode Character
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
        System.out.println(message);
        for (int i = 0; i < length; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
//            System.out.println("One " + i);
            System.out.print(progressBar);
            try {
                Thread.sleep(timeInterval);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static void main(String[] args) {
        printMsgWithProgressBar("Loading", 25, 10);

        System.out.println("12345");
        System.out.print("\b\b\b\b");

        System.out.println();

        System.out.print("12345");
        System.out.print("\b\b\b\b");

        System.out.print('/');
        System.out.print('\b');
        System.out.print('-');
        System.out.print('\b');
        System.out.print('\\');
        System.out.print('\b');

        System.out.println();

        System.out.println("ABCD");
        System.out.print("1234");
        System.out.print("\r\r");
        System.out.println("XYZ0");
    }
}