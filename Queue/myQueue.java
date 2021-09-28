import java.util.*;
class QueueToStackAdapter {
    Queue<Integer> mainQ;
    Queue<Integer> helperQ;

    public QueueToStackAdapter() {
      mainQ = new ArrayDeque<>();
      helperQ = new ArrayDeque<>();
    }

    int size() {
      // write your code here
      return mainQ.size();
    }

    void push(int val) {
      // write your code here
      mainQ.add(val);
    }

    int pop() {
      // write your code here
      for(int i = 0;i<mainQ.size()-1;i++){
          helperQ.add(mainQ.remove());
      }
      
      int val =  mainQ.remove();
      mainQ = helperQ;
      return val;
    }

    int top() {
      // write your code here
      for(int i = 0;i<mainQ.size()-1;i++){
          helperQ.add(mainQ.peek());
      }
      
      int val =  mainQ.peek();
      helperQ.add(mainQ.peek());
      mainQ = helperQ;
      return val;
    }
  }
class Queue{
    private int[] data;
    private int front = 0;
    private int rear = 0;
    public Queue(int capacity){
        this.data = new int[capacity];
    }
    public void add(int val){
        if(this.rear == data.length){
            System.out.println("Queue overflow");
            return -1;
        }
        int index = (this.front + this.rear)% data.length;
        data[index] = val;
        this.rear++;
    }
    public int remove(){
        if(data.length == 0){
            System.out.println("Queue undeflow");
            return -1;
        }
        int val = data[front];
        this.front = (this.front + 1)% data.length;
        this.rear--;
        return val;
    }
    public int peek(){
        if(data.length == 0){
            System.out.println("Queue undeflow");
            return -1;
        }
        return data[front];
    }
    public int size(){
        return this.rear;
    }

    public int isEmpty(){
        return this.rear == 0 ? true : false;
    }

    @Override
    public String toString(){
        String str = "";
        str += "[";
        for(int i = 0;i<this.rear - 1;i++){
            int index =  (i + this.front)% data.length;
            
            str += data[index]) +", ";
        }
        str += data[(rear -1 + + front)% data.length] +"]";

        return str;
    }

}

public class myQueue{
    public static void demo(){
        // Queue<Integer> qu = new ArrayDeque<>();
        // qu.add(10);
        // qu.add(20);
        // System.out.println(qu);
        // qu.add(30);
        // qu.add(40);
        // System.out.println(qu);
        // System.out.println(qu.remove());
        // System.out.println(qu);
        // System.out.println(qu.peek());
        // System.out.println(qu.size());
        QueueToStackAdapter st = new QueueToStackAdapter();
        st.add(10);
        st.add(20);
        st.add(30);
        
    }
    public static void main(String[] args){
        demo();
        generate(10);
    }

    public static ArrayList<String> generate(int N)
    {
        // Your code here
        ArrayList<String> res = new ArrayList<>();
        Queue<String> qu = new ArrayDeque<>();
        qu.add("1");
        for(int i= 1;i<N;i++){
            String rem = qu.remove();
            res.add(rem);
            qu.add(rem + '0');
            qu.add(rem + '1');
        }

        return res;
    }
}