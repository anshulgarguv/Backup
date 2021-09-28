//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
public class infiniteMaze {
   
    public static void main(String args[]) throws IOException{
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String indices = reader.readLine();
        // String[] splittedArray = indices.split(" ");
        // int m = Integer.parseInt(splittedArray[0]);
        // int n = Integer.parseInt(splittedArray[1]);
        // char[][] matrix = new char[m][n];
        // int srcx = 0;
        // int srcy = 0;
        // for(int i = 0 ;i<m;i++){
        //     String row = reader.readLine();
        //     String[] colArray = row.split("");
        //     for(int j = 0;j<n;j++){
        //         if(colArray[j] == "S"){
        //             srcx = j;
        //             srcy = i;
        //         }
        //         matrix[i][j] = colArray[j].charAt(0);
        //     }
        // }

        char[][] matrix =
        {{'#','#','.','#'},
        {'#','#','.','#'},
        {'#','.','.','#'},
        {'#','.','#','#'}, 
        {'#','.','.','#'}  
    };
        boolean ans = findPath(matrix,2,1,2,1);
        if(ans == true){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }
    }
    static int[] dirx = {0,1,0,-1};
    static int[] diry = {-1,0,1,0};
    public static boolean findPath(char[][] matrix,int srcx,int srcy,int destx,int desty){
        int n = matrix[0].length;
        int m = matrix.length;
        if(srcx != destx && desty != srcy && destx == calcModx(srcx,n) && desty == calcMody(srcy,m)){
            System.out.print("----Here---");
            return true;
        }
        int x = calcModx(srcx,n);
        int y = calcModx(srcy,m);
        matrix[y][x] = '#';
        for(int d = 0;d<4;d++){
            int nbrx = srcx + dirx[d];
            int nbry = srcy + diry[d];
            
            if(nbrx >= -n && nbrx < 2*n && nbry >= -m && nbry < 2*m){
                int modx = calcModx(nbrx,n);
                int mody = calcModx(nbry,m);
                if(matrix[mody][modx] == '.'){
                    
                    boolean hasP = findPath(matrix,nbrx,nbry,destx,desty);
                    if(hasP == true){
                        return true;
                    }
                }   
                
            }
        }
        
        return false;
        
    }

    public static int calcModx(int x,int n){
        if(x < 0){
            x = (2*n + x)%n;
        }else{
            x = x%n;
        }

        return x;
    }

    public static int calcMody(int y,int m){
        if(y<0){
            y = (2*m + y) %m;
        }else{
            y = y%m;
        }

        return y;
    }
}
