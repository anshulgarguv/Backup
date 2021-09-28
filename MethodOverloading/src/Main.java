public class Main {
    public static void main(String[] args) {
        double centimeters = MethodOverloading.calcFeetAndInchesToCentimeters(-157);
        if(centimeters < 0.0){
            System.out.println("Invalid Parameters");
        }
    }
}
