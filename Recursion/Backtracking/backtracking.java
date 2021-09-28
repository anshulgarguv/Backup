import java.util.*;
public class backtracking {
    // public static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    public static char[] chArr = {'t','l','d','r'};
    public static int[][] dir = {
        {-2,1},
        {-1,2},
        {1,2},
        {2,1},
        {2,-1},
        {1,-2},
        {-1,-2},
        {-2,-1}
        
    };
    public static void main(String[] args) throws Exception {
        int[] arr = {10,
                    20,
                    30,
                    40,
                    50};
        //printTargetSumSubsets(arr,0,"",0,60);
        int[][] maze = {{0,1,0,0,0,0},
                        {0,1,0,1,1,0},
                        {0,1,0,1,1,0},
                        {0,0,0,0,0,0},
                        {1,1,0,1,1,0},
                        {1,1,0,0,0,0}};
        // floodfill(maze, 0, 0, "");   
        // floodFillUsingDirArray(maze, 0, 0, "");
        int[][] chess =  new int[5][5];
        //printKnightsTour(chess,2,0,1);
        printKnightsTourUsingDirArray(chess,2,0,1);

    }
    public static void printTargetSumSubsets(int[] arr, int idx, String set, int sos, int tar) {
        if(idx == arr.length){
            if(sos == tar){
                System.out.println(set + ".");
            }
            return;
        }

        printTargetSumSubsets(arr,idx + 1,set + arr[idx] + ", ",sos + arr[idx],tar);
        printTargetSumSubsets(arr,idx+1,set,sos,tar);

    }

    public static void floodfill(int[][] maze, int sr, int sc, String asf) {
        if(sr < 0 || sr >= maze.length || sc < 0 || sc >= maze.length || maze[sr][sc] == 1){
            return;
        }
        if(sr == maze.length - 1&& sc == maze.length -1 ){
            System.out.println(asf);
            return;
        }
        maze[sr][sc]  = 1;
        floodfill(maze,sr-1,sc,asf + "t");
        floodfill(maze,sr,sc-1,asf + "l");
        floodfill(maze,sr+1,sc,asf + "d");
        floodfill(maze,sr,sc+1,asf + "r");
        maze[sr][sc]  = 0;
    }

    public static void floodFillUsingDirArray(int[][] maze, int sr, int sc, String asf){
        if(sr == maze.length - 1&& sc == maze.length -1 ){
            System.out.println(asf);
            return;
        }
        for(int d = 0;d<dir.length;d++){
            int rr = sr + dir[d][0];
            int cc = sc + dir[d][1];

            if(rr>=0 && rr<maze.length && cc>=0 && cc<maze.length && maze[rr][cc] != 1){
                maze[sr][sc]  = 1;
                floodFillUsingDirArray(maze,rr,cc,asf + chArr[d]);
                maze[sr][sc]  = 0;
            }
        }
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove) {
        if(r<0 || c < 0 || r>= chess.length || c >= chess.length || r>= chess.length || chess[r][c] > 0 ){
            return;
        } else if(upcomingMove == chess.length * chess.length){
            chess[r][c] = upcomingMove;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }
        chess[r][c] = upcomingMove;
        printKnightsTour(chess,r-2,c+1,upcomingMove + 1);
        printKnightsTour(chess,r-1,c+2,upcomingMove + 1);
        printKnightsTour(chess,r+1,c+2,upcomingMove + 1);
        printKnightsTour(chess,r+2,c+1,upcomingMove + 1);
        printKnightsTour(chess,r+2,c-1,upcomingMove + 1);
        printKnightsTour(chess,r+1,c-2,upcomingMove + 1);
        printKnightsTour(chess,r-1,c-2,upcomingMove + 1);
        printKnightsTour(chess,r-2,c-1,upcomingMove + 1);
        chess[r][c] = 0;

    }

    public static void printKnightsTourUsingDirArray(int[][] chess, int r, int c, int upcomingMove){
        if(upcomingMove == chess.length * chess.length){
            chess[r][c] = upcomingMove;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }
        chess[r][c] = upcomingMove;
        for(int d = 0;d<dir.length;d++){
            int rr = r + dir[d][0];
            int cc = c + dir[d][1];

            if(rr>=0 && rr<chess.length && cc >= 0 && cc < chess.length && chess[rr][cc] == 0){
                
                printKnightsTourUsingDirArray(chess,rr,cc,upcomingMove+1);
                
            }
        }
        chess[r][c] = 0;
    }
    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}