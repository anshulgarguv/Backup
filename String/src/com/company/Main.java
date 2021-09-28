package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //byte
        //short
        //int
        //long
        //float
        //double
        //char
        //boolean
        String myString = "This is a string";
        System.out.println("myString is equal to "+myString);
        myString = myString + ", and this is more";
        System.out.println("myString is equal to "+myString);
        myString = myString + " \u00A9 2019";
        System.out.println("myString is equal to "+myString);
        String lastString = "10";
        int myInt = 50;
        lastString = lastString + myInt;
        System.out.println("LastString is equal to "+lastString);
        double doubleNumber = 120.47d;
        lastString = lastString + doubleNumber;
        System.out.println("LastString is equal to " + lastString);
    }
}
