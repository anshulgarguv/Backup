package com.company;

public class Main {

    public static void main(String[] args) {
        int myValue = 10000;

        int myMinIntValue = Integer.MIN_VALUE;
        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println("Integer Minimum Value = " + myMinIntValue);
        System.out.println("Integer Maximum Value = " + myMaxIntValue);

        byte testByte = 84;
        short testShort = 32364;
        int testInt = 42873682;

        long testLong = 50000 + 10*(testByte + testShort +testInt);
    }
}
