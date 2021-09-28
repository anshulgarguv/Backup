import java.util.*;
public class celebrityProblem{
    public static void main(String[] args){
        // int[][] arr = {{1,0,1,0,1},{1,1,1,1,0},{0,0,0,0,0},{1,0,1,1,0},{0,1,1,1,0}};
        // int ans = celebrity(arr);
        // if(ans == -1){
        //     System.out.println("none");
        // }else{
        //     System.out.println(ans);
        // }

    //    int[][] arr = { {1, 8},
    //                     {11, 19},
    //                     {25, 27},
    //                     {5, 6},
    //                     {22, 28},
    //                     {27, 30}};
    //      mergeOverlappingIntervals(arr);      
            smallestNumberFollowingPattern("ddddiiii");         
    }
    // public static Pair implements Comparable<Pair> {
    //     int start = 0;
    //     int end = 0;

    //     public Pair(int start,int end){
    //         this.start = start;
    //         this.end = end;
    //     }

    //     public int compareTo(Pair o){
    //         return this.start - o.start;
    //     }
    // }
    public static void celebrity(int[][] arr){
        Stack<Integer> st = new Stack<Integer>();
        for(int i = 0;i < arr.length;i++){
            st.push(i);
        }

        while(st.size() > 1){
            int val1 = st.pop();
            int val2 = st.pop();
            if(arr[val1][val2] == 1){
                st.push(val2);
            }else{
                st.push(val1);
            }
        }

        for(int i = 0;i<arr.length;i++){
            if(arr[st.peek()][i] == 1){
                System.out.println("none");
                return;
            }
        }

        for(int i = 0;i<arr.length;i++){
            if(arr[i][st.peek()] == 0 && i != st.peek()){
                System.out.println("none");
                return;
            }
        }

        System.out.println(st.peek());
    }

    public static void mergeOverlappingIntervals(int[][] arr){
        // Pair[] pairs = new Pair[arr.length];
        // for(int i = 0;i<arr.length;i++){
        //     pairs[i] = new Pair(arr[i][0],arr[i][1]);
        // }
        // Arrays.sort(pairs);
        java.util.Arrays.sort(arr, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        Stack<Integer> st = new Stack<Integer>();
        st.push(arr[0][0]);
        st.push(arr[0][1]);
        for(int i = 1;i<arr.length;i++){
            if(st.peek() < arr[i][0]){
                //print
                int val1 = st.pop();
                int val2 = st.pop();
                System.out.print(val2 + " ");
                System.out.print(val1);
                System.out.println();
                st.push(arr[i][0]);
                st.push(arr[i][1]);
                continue;
            }else{
                if(st.peek() < arr[i][1]){
                    st.pop();
                    st.push(arr[i][1]);
                }
                

            }
        }

        if(st.size() > 0){
            int val1 = st.pop();
            int val2 = st.pop();
            System.out.print(val2 + " ");
            System.out.print(val1);
        }
    }

    public static void smallestNumberFollowingPattern(String str){
        int count = 1;
        Stack<Integer> st = new Stack<Integer>();
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == 'd'){
                st.push(count);
                count++;
            }else{
                st.push(count);
                count++;
                while(st.size() > 0){
                    System.out.print(st.pop());
                }
            }
        }
        
        st.push(count);
        while(st.size() > 0){
            System.out.print(st.pop());
        }
    }

    public static void maximumContigousSubstring(){
        int[] startIndex = {};
        int[] endIndex = {};

        
    }
}