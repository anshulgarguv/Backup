package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int result = 1 + 2;
        System.out.println("1 + 2 = " + result);
        int previousResult = result;
        System.out.println("previousResult = "+previousResult);
        result = result - 1;
        System.out.println("3 - 1 = " + result);
        System.out.println("previousResult = "+previousResult);

        result = result * 10;
        System.out.println("2 * 10 = " + result);

        result = result/5;
        System.out.println("20/5 = " + result);

        result = result % 3;
        System.out.println("4 % 3 = " + result);

        result++;
        System.out.println("1 + 1 = " + result);

        result--;
        System.out.println("2 - 1 = " + result);

        result += 2;
        System.out.println("1 + 2 = " + result);

        result *= 10;
        System.out.println("3 * 10 = " + result);

        result /= 3;
        System.out.println("30 / 3 = " + result);

        result -= 2;
        System.out.println("10 - 2 = " + result);

        boolean isAlien = false;
        if(isAlien == false) {
            System.out.println("It is not a alien!");
            System.out.println("And I am scared of Aliens");
        }

        boolean isCar = false;
        if(isCar == true){
            System.out.println("This is not supposed to happen");
        }

        double myFirstValue = 20.00d;
        double mySecondValue = 80.00d;
        double myValuesTotal = (myFirstValue + mySecondValue)*100.00;
        System.out.println("MyValuesTotal = " + myValuesTotal);
        double theRemainder = myValuesTotal % 40.00d;
        System.out.println("Remainder = " + theRemainder);
        boolean isNoRemainder = theRemainder == 0 ? true : false;
        System.out.println("isNoRemainder = " + isNoRemainder);
        if(!isNoRemainder){
            System.out.println("Got Some Remainder");
        }
    }
}
