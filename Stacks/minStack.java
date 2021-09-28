import java.util.*;
public class minStack{
    public class MinStack{
        Stack<Integer> data;
        int min;
        public MinStack(){
            data = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        int size(){
            return data.size();
        }

        void push(int val){
            if(min > val){
                data.push(val + val - min);
                min = val;
            }else{
                data.push(val);
            }
        }

        int pop(){
            if(data.size() == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            if(data.peek() < min){
                int val = min;
                min = 2*val - data.pop();
                return val;
            }else{
                return data.pop();
            }
        }

        int top(){
            if(data.size() == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            if(data.peek() > min){
                return data.peek();
            }else{
                return min;
            }
        }

        int min(){
            if(data.size() == 0){
                System.out.println("Stack underflow");
                return -1;
            }
            return min;
        }
    }

    public static void main(String[] arg){

    }
}