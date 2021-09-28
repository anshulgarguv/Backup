package com.company;

public class Main {
    private static final String INVALID_VALUE_MESSAGE = "Invalid Value";
    public static void main(String[] args) {
	// write your code here
        System.out.println(getDurationString(65,45));
        System.out.println(getDurationString(3945));
        System.out.println(getDurationString(-1));
    }
    private static String getDurationString(long minutes,long seconds){
        if(minutes < 0 || (seconds < 0 || seconds > 59)){
            return INVALID_VALUE_MESSAGE;
        }

        long hours = minutes/60;
        minutes = minutes % 60;
        return hours + "h "+minutes + "m "+seconds + "s ";

    }
    private static String getDurationString(long seconds){
        if(seconds < 0){
            return INVALID_VALUE_MESSAGE;
        }
        long minutes = seconds/60;
        seconds = seconds%60;
        System.out.println(minutes + "m " + seconds + " s");
        return getDurationString(minutes,seconds);
    }

    public static double area(double radius){
        if(radius < 0){
            return -1;
        }
        return radius*radius*Math.PI;
    }

    public static double area(double x,double y){
        if(x < 0 || y <0){
            return -1;
        }
        return x*y;
    }

    public static void printYearsAndDays(long minutes){
        if(minutes < 0){
            System.out.println("Invalid Value");
        }else{
            long years = minutes/(1440*365);
            long remainingMinutes = minutes%(1440*365);
            long days = remainingMinutes/1440;
//            remainingMinutes = remainingMinutes%1440;
            System.out.println(minutes + " min = "+years+ " y and "+days +" d");
        }

    }

    public static void printEqual(int number_one,int number_two,int number_three){
        if(number_one<0 || number_two < 0 || number_three<0){
            System.out.println("Invalid Value");
        }
        if(number_one == number_two && number_two==number_three){
            System.out.println("All numbers are equal");
        }
        else if(number_one != number_two && number_two != number_three && number_three!=number_one){
            System.out.println("All numbers are different");
        }else{
            System.out.println("Neither all are equal or different");
        }
    }

    public static boolean isCatPlaying(boolean summer,int temperature){
        if(summer == true && temperature <=45 && temperature>=25){
            return true;
        }else if(summer == false && temperature <=35 && temperature>=25){
            return true;
        }else{
            return false;
        }
    }
}
