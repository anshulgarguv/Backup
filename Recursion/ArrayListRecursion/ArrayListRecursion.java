import java.util.*;
public class ArrayListRecursion{
    public static String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
    public static void main(String[] args) throws Exception {
        // ArrayList<String> ans = gss("abc");
        // System.out.print(ans);
        // ArrayList<String> ans = getKPC("78");
        // System.out.print(ans);
        // ArrayList<String> ans = getStairPaths(3);
        // System.out.print(ans);
        // ArrayList<String> ans = getMazePaths(0,0,2,2);
        // System.out.print(ans);
        ArrayList<String> ans = getMazePathsWithJump(0,0,2,2);
        System.out.print(ans);
    }

    public static ArrayList<String> gss(String str) {
        if(str.length() == 0){
            ArrayList<String> emptyArrayList = new ArrayList<String>();
            emptyArrayList.add("");
            return emptyArrayList;
        }

        char ch = str.charAt(0);
        String roq = str.substring(1);
        ArrayList<String> rres = gss(roq);
        ArrayList<String> res = new ArrayList<String>();
        for(String s : rres){
            res.add("" + s);
        }
        for(String s : rres){
            res.add(ch + s);
        }
        
        return res;
    }

    public static ArrayList<String> getKPC(String str) {
        if(str.length() == 0){
            ArrayList<String> emptyArrayList = new ArrayList<String>();
            emptyArrayList.add("");
            return emptyArrayList;
        }
        int firstDigit = str.charAt(0) - '0';
        String roq = str.substring(1);
        String code = codes[firstDigit];
        ArrayList<String> rres = getKPC(roq);
        ArrayList<String> res = new ArrayList<String>();
        for(String s : rres){
            for(int i = 0;i<code.length();i++){
                res.add(code.charAt(i) + s);
            }
        }
        return res;
    } 

    public static ArrayList<String> getStairPaths(int n){
        if(n<0){
            ArrayList<String> emptyArrayList = new ArrayList<String>();
            return emptyArrayList;
        }
        if(n == 0){
            ArrayList<String> emptyArrayList = new ArrayList<String>();
            emptyArrayList.add("");
            return emptyArrayList;
        }
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<String> rres = getStairPaths(n-1);
        for(String s: rres){
            ans.add("1" +s);
        }
        rres = getStairPaths(n-2);
        for(String s: rres){
            ans.add("2" +s);
        }
        rres = getStairPaths(n-3);
        for(String s: rres){
            ans.add("3" +s);
        }

        return ans;
    }

    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if(sr > dr || sc > dc){
            ArrayList<String> emptyArrayList = new ArrayList<String>();
            return emptyArrayList;
        }
        if(sr==dr && sc == dc){
           ArrayList<String> emptyArrayList = new ArrayList<String>();
            emptyArrayList.add("");
            return emptyArrayList;
        }
        ArrayList<String> ans = new ArrayList<String>();
        ArrayList<String> rres = getMazePaths(sr,sc+1,dr,dc);
        for(String s : rres){
            ans.add("h" + s);
        }
        rres = getMazePaths(sr+1,sc,dr,dc);
        for(String s : rres){
            ans.add("v" + s);
        }
        return ans;

	}

    public static ArrayList<String> getMazePathsWithJump(int sr, int sc, int dr, int dc) {
        if(sr==dr && sc == dc){
           ArrayList<String> emptyArrayList = new ArrayList<String>();
            emptyArrayList.add("");
            return emptyArrayList;
        }
        ArrayList<String> ans = new ArrayList<String>();
        int i =1;
        ArrayList<String> rres =  new ArrayList<String>();
        while(i + sc <= dc){
            rres = getMazePathsWithJump(sr,sc+i,dr,dc);
            for(String s : rres){
                ans.add("h" +i + s);
            }
            i++;
            
        }
        int j =1;
        while(j + sr <= dr){
            rres = getMazePathsWithJump(sr+j,sc,dr,dc);
            for(String s : rres){
                ans.add("v" + j+ s);
            }
            j++;
            
        }

        int k = 1;
        while(k + sr <= dr && k + sc <= dc){
            rres = getMazePathsWithJump(sr + k,sc + k,dr,dc);
            for(String s : rres){
                ans.add("d" +k+ s);
            }
            k++;
            
        }
        return ans;
	}  
}