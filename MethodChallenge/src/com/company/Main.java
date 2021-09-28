package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int position = calculateHighScorePosition(1500);
        displayHighScorePosition("Anshul",position);

        position = calculateHighScorePosition(900);
        displayHighScorePosition("Arun",position);

        position = calculateHighScorePosition(400);
        displayHighScorePosition("Viv",position);

        position = calculateHighScorePosition(50);
        displayHighScorePosition("Ash",position);

        position = calculateHighScorePosition(1000);
        displayHighScorePosition("Pant",position);
    }

    public static void displayHighScorePosition(String playerName,int position){
        System.out.println(playerName + " managed to get in to position " + position + " on high score table");
    }

    public static int calculateHighScorePosition(int playerScore){

//        if(playerScore >= 1000){
//            return 1;
//        }else if(playerScore >= 500){
//            return 2;
//        }else if(playerScore >= 100){
//            return 3;
//        }
//        return 4;
        int position = 4;
        if(playerScore >=1000){
            position = 1;
        }else if(playerScore >= 500){
            position = 2;
        }else if(playerScore >= 100);{
            position = 3;
        }

        return position;
    }
}
