public class MegaBytesConverter {
    public static void printMegaBytesAndKiloBytes(int kiloBytes){
        if(kiloBytes < 0){
            System.out.println("Invalid Value");
        }else{
            int megaBytes = kiloBytes / 1024;
            int remainingKiloBytes = kiloBytes % 1024;
            System.out.println(kiloBytes + " KB = " +megaBytes + " MB and " + remainingKiloBytes + " KB");
        }
    }

    public static boolean shouldWakeUp(boolean barking,int hourOfDay){
        if(hourOfDay < 0 || hourOfDay > 23){
            return false;
        }
        if(barking ==true && (hourOfDay < 8 || hourOfDay > 22)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isLeapYear(int year){
       if(year < 1 || year > 9999){
            return false;
        }
//        if(year % 4 == 0){
//            if(year % 100 == 0){
//                if(year % 400 == 0){
//                    return true;
//                }else{
//                    return false;
//                }
//            }else{
//                return true;
//            }
//        }else{
//            return false;
//        }

//        if (((year % 4 == 0) && (year % 100!= 0)) || (year%400 == 0))
//            return true;
//        else
//            return false;


        if (year % 400 == 0)
            return true;

        // Else If a year is multiple of 100,
        // then it is not a leap year
        if (year % 100 == 0)
            return false;

        // Else If a year is multiple of 4,
        // then it is a leap year
        if (year % 4 == 0)
            return true;
        return false;
    }

    public static boolean areEqualByThreeDecimalPlaces(double number_one ,double number_two){
        if((int)(number_one*1000) == (int)(number_two*1000)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean hasEqualSum(int number_one,int number_two,int number_three){
        if(number_one + number_two == number_three){
            return true;
        }else{
            return false;
        }
    }

    public static boolean hasTeen(int number_one,int number_two,int number_three){
        if(isTeen(number_one) || isTeen(number_two) || isTeen(number_three)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean  isTeen(int number){

        if(number >=13 && number<=19){
            return true;
        }else{
            return false;
        }
    }
}
