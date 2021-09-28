import java.util.*;
public class stringRecursion{
    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
    public static void main(String args[]){
        //printSS("yvTA","");
        //printKPC("78","");
        //printStairPath(3,"");
        //printMazePaths(0,0,1,1,"");
        //printMazePathsWithJump(0,0,2,2,"");
        //printPermutations("abc","");
        printEncodings("655196","");
    }
    public static void printSS(String str, String ans) {
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }
        char ch = str.charAt(0);
        String roq = str.substring(1);
        printSS(roq,ans + ch);
        printSS(roq,ans + "");
    }

    public static void printKPC(String str, String asf) {
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }
        int chNumber = str.charAt(0) - '0';
        String roq = str.substring(1);
        String code = codes[chNumber];
        for(int i = 0;i < code.length();i++){
            printKPC(roq,asf + code.charAt(i));
        }

    }

    public static void printStairPath(int n,String path ){
        if(n<0){
            return;
        }
        if(n == 0){
            System.out.println(path);
            return;
        }

        printStairPath(n-1,path + "1");
        printStairPath(n-2,path + "2");
        printStairPath(n-3,path + "3");
    }


    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
        if(sr > dr || sc > dc){
            return;
        }
        if(sr==dr && sc == dc){
           System.out.println(psf);
            return; 
        }
        printMazePaths(sr,sc+1,dr,dc,psf + "h");
        printMazePaths(sr+1,sc,dr,dc,psf + "v");


	}

    public static void printMazePathsWithJump(int sr, int sc, int dr, int dc, String psf) {
        if(sr==dr && sc == dc){
           System.out.println(psf);
            return; 
        }
        int i =1;
        while(i + sc <= dc){
            printMazePathsWithJump(sr,sc+i,dr,dc,psf + "h" + i);
            i++;
        }
        int j =1;
        while(j + sr <= dr){
            printMazePathsWithJump(sr+j,sc,dr,dc,psf + "v" + j);
            j++;
        }

        int k = 1;
        while(k + sr <= dr && k + sc <= dc){
            printMazePathsWithJump(sr + k,sc + k,dr,dc,psf+"d"+k);
            k++;
        }

	}

    public static void printPermutations(String str,String ans) {
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }

        for(int i  = 0;i<str.length();i++){
            char ch = str.charAt(i);
            String roq = str.substring(0,i) + str.substring(i+1);
            printPermutations(roq,ans+ ch);
        }
    }

    public static void printEncodings(String str,String ans){
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }

        int numch1 = str.charAt(0) - '0';
        if(numch1 == 0){
            return;
        }
        String roq = str.substring(1);
        char toAdd = (char)(numch1 + 'a' - 1);
        printEncodings(roq,ans + toAdd);

        if(str.length() > 1){
            String firstTwo = str.substring(0,2);
            String roq2 = str.substring(2);
            int firstTwoNum = Integer.parseInt(firstTwo);
            if(firstTwoNum <=26){
                char code = (char)('a' + firstTwoNum -1);
                printEncodings(roq2,ans+code );
            }
        }

    }
}