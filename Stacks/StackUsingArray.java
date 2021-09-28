import java.util.*;
class stack {
    private int[] arr;
    private int size = 0;
    
    public stack(int capacity){
        arr = new int[capacity];
    }
    public void push(int val){
        if(size == arr.length){
            System.out.println("Stack Overflow");
            return;
        }
        arr[this.size] = val;
        size++;
    }
    public int pop(){
        if(size == 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        int val = arr[this.size-1];
        this.size--;
        return val;
    }
    public int peek(){
        if(this.size == 0){
            System.out.println("Stack Underflow");
            return -1;
        }
        
        return arr[this.size-1];
    }
    
    public boolean isEmpty(){
        return this.size == 0 ? true : false;
    }
    
    public int size(){
        return this.size;
    }
    
    public void display(){
        for(int i = 0;i<this.size;i++){
             System.out.print(arr[i] + ",");
        }
        System.out.println();
    }
    
}

class StackUsingArray {

     public static void main(String []args){
        stack st = new stack(10);
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);
        st.display();
        while(!st.isEmpty()){
            int rem = st.peek();
            st.pop();
            System.out.print(rem + " Stack ->");
            st.display();
        }
     }
}