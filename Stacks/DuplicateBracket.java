import java.util.*;
public class DuplicateBracket{
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        //String str = "a + b";
        // Boolean ans = isDuplicate(str);
        //Boolean ans = isBalancedBracket(str);
        //System.out.print(ans);
        int[] arr = {1,3,-1,-3,5,3,6,7};
        // int[] ngr = nextGreaterElementRight(arr);
        // display(ngr);
        // int[] ngl = nextGreaterElementLeft(arr);
        // display(ngl);
        // int[] nsr = nextSmallerElementRight(arr);
        // display(nsr);
        // int[] nsl = nextSmallerElementLeft(arr);
        // display(nsl);
        // int[] stockSpanArr = stockSpan(arr);
        // display(stockSpanArr);
        // int[] dailyTemp = dailyTemperatures(arr);
        // display(dailyTemp);
        // int[] ans = nextGreaterElements(arr);
        // display(ans);
        // int filterCount = pollutionFilter(arr);
        // System.out.println(filterCount);
        //int histogram = largestAreaHistogram(arr);
        //display(histogram);
        slidingWindow(arr,3);
        //System.out.println(histogram);
    }

    public static void display(int[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
    public static void slidingWindow(int[] arr,int k){
        int[] ngeArr = nextGreaterIndexRight(arr);
        int j = 0;
        for(int i = 0;i<arr.length-k+1;i++){
            
            if(i > j){
                j = i;
            }
            while(i+k > ngeArr[j]){
                j = ngeArr[j];
            }
            System.out.println(arr[j]);
        }
        
    }
    
    public static int[] nextGreaterIndexRight(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(0);
        for(int i  = 1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            //ngeArr[st.peek()] = -1;
            ngeArr[st.peek()] = arr.length;
            st.pop();
        }

        return ngeArr;
    }
    //leetcode 85
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length ==0 || matrix[0].length == 0) return 0;
       int[] arr = new int[matrix[0].length];
        int res = 0;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                    if(matrix[i][j]=='0'){
                        arr[j] = 0;
                    }else{
                        arr[j] += matrix[i][j] - '0';
                    }
            }
           res = Math.max(res,largestAreaHistogram(arr));
       }
       
        return res;
    }
    public static int largestAreaHistogram(int[] arr){
        int[] nsl = nextSmallerElementLeft(arr);
        int[] nsr = nextSmallerElementRight(arr);
        int[] ans = new int[arr.length];
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<arr.length;i++){
            ans[i] = arr[i]*(nsr[i] - nsl[i]-1);
            if(ans[i] > max){
                max = ans[i];
            }
        }

        return max;
    }

    public static boolean isDuplicate(String str){
        Stack<Character> st = new Stack<>();
        for(int i = 0;i < str.length();i++){
            char ch = str.charAt(i);
            if(ch != ')'){
                st.push(ch);
            }else{
                if(st.peek() == '('){
                    return true;
                }
                while(st.peek() != '('){
                    st.pop();
                }
                st.pop();
            }    
        }

        return false;
    }

    public static boolean isBalancedBracket(String str){
        Stack<Character> st = new Stack<>();
        for(int i = 0;i < str.length();i++){
            char ch = str.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            }else if(ch == '}' || ch == ']' || ch == ')'){
                if(st.isEmpty()){
                    return false;
                }
                if(st.peek() == '(' && ch != ')'){
                    return false;
                }
                if(st.peek() == '{' && ch != '}'){
                    return false;
                }
                if(st.peek() == '[' && ch != ']'){
                    return false;
                }

                if(!st.isEmpty() && st.peek() == '(' && ch == ')'){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek() == '{' && ch == '}'){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek() == '[' && ch == ']'){
                    st.pop();
                }
            }
        }
        if(!st.isEmpty()){
            return false;
        }

        return true; 
    }


    public static int[] nextGreaterElementRight(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(0);
        for(int i  = 1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = -1;
            //ngeArr[st.peek()] = arr.length;
            st.pop();
        }

        return ngeArr;
    }


    public static int[] nextGreaterElementLeft(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(arr.length-1);
        for(int i  = arr.length -2 ;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = -1;
            st.pop();
        }

        return ngeArr;
    }

    public static int[] nextSmallerElementRight(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(0);
        for(int i  = 1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                //ngeArr[st.peek()] = arr[i];
                ngeArr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            //ngeArr[st.peek()] = -1;
            ngeArr[st.peek()] = arr.length; // for largest area histogram
            st.pop();
        }

        return ngeArr;
    }

    public static int[] nextSmallerElementLeft(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(arr.length-1);
        for(int i  = arr.length -2 ;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] > arr[i]){
                // ngeArr[st.peek()] = arr[i];
                ngeArr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = -1;
            st.pop();
        }

        return ngeArr;
    }

    public static int[] stockSpan(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(arr.length-1);
        for(int i  = arr.length -2 ;i>=0;i--){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = -1;
            st.pop();
        }
        // for(int i = 0;i<ngeArr.length;i++){
        //     ngeArr[i] = i - ngeArr[i];
        // }
        return ngeArr;
    }
    //leetcode 739 daily temperature
    public static int[] dailyTemperatures(int[] arr) {
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(0);
        for(int i  = 1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = 0;
            st.pop();
        }
        for(int i = 0;i<ngeArr.length;i++){
        if(ngeArr[i] != 0){
            ngeArr[i] =ngeArr[i] - i;
        }
            
        }
        return ngeArr;
    }


    //leetcode 503
    //[1,2,3,4,3][1,2,3,4,3] -- 2,3,4,-1,4,2,3,4,-1,-1
    public static int[] nextGreaterElements(int[] arr){
        int[] ngeArr = new int[arr.length];
        Stack<Integer> st = new Stack();
        st.push(0);
        for(int i  = 1;i<arr.length;i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngeArr[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        int loopCount = st.size();
        for(int stSize = 1;stSize<=loopCount;stSize++){
            for(int i = 0; i<st.peek();i++){
                if(st.size() > 0 && arr[st.peek()] < arr[i] ){
                    ngeArr[st.pop()] = arr[i];
                    break;
                }
            }
        }

        while(st.size() > 0){
            ngeArr[st.peek()] = -1;
            st.pop();
        }

        return ngeArr;
    }

    public static int pollutionFilter(int[] arr){
        float[] fArr = new float[arr.length];
        float totalPollution = 0;
        for(int i = 0;i<arr.length;i++){
            fArr[i] = arr[i];
            totalPollution = totalPollution + (float)arr[i];
        }
        float reducedPollution = 0;
        int i = 0;
        int filterCount = 0;
        while(reducedPollution < totalPollution/2 && i < fArr.length){
            if(isMax(fArr,fArr[i])){
                fArr[i] = fArr[i]/2;
                reducedPollution = reducedPollution + fArr[i];
                filterCount++;
            }else{
                i++;
            }
            if(i == fArr.length){
                i = 0;
            }
        }

        return filterCount;
    }

    public static boolean isMax(float[] fArr,float ele){
        for(int i = 0;i<fArr.length;i++){
            if(ele < fArr[i]){
                return false;
            }
        }
        return true;
    }


}