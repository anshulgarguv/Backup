package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        byte testByte = 84;
        short testShort = testByte;
        int testInt = 42873682;

        long testLong = 50000L + 10L*(testByte + testShort +testInt);
        System.out.println(testLong);
    }
}
