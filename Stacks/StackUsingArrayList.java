import java.util.*;
class stack {
    private ArrayList<Integer> arrList;
    private int size = 0;
    
    public stack(){
        arrList = new ArrayList<Integer>();
    }
    public void push(int val){
        arrList.add(val);
    }
    public int pop(){
        if(arrList.size() == 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        int index = arrList.size();
        int val = arrList.remove(index-1);
        return val;
    }
    public int peek(){
        if(arrList.size() == 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        int peekIndex = arrList.size() - 1;
        return arrList.get(peekIndex);
    }
    
    public boolean isEmpty(){
        return arrList.size() == 0 ? true : false;
    }
    
    public int size(){
        return arrList.size();
    }
    
    public void display(){
        for(Integer a : arrList){
            System.out.print(a.toString() + " ,");
        }
        System.out.println();
    }
    
}

class StackUsingArrayList {

     public static void main(String []args){
        stack st = new stack();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        st.display();
        // while(!st.isEmpty()){
        //     int rem = st.peek();
        //     st.pop();
        //     System.out.print(rem + " Stack ->");
        //     st.display();
        // }
        st.pop();
        st.pop();
        st.pop();
        int peak = st.peek();
        System.out.print("peak---"+peak);
        System.out.print(" Stuck ->");
        st.display();
     }
}