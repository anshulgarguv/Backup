import java.util.*;
public class usage {

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;
    
        public MedianPriorityQueue() {
          left = new PriorityQueue<>(Collections.reverseOrder());
          right = new PriorityQueue<>();
        }
    
        public void add(int val) {
          // write your code here
          if(left.size() == 0 && right.size() == 0){
              left.add(val);
              return;
          }else if(right.size() > 0 && val > right.peek()){
              right.add(val);
          }else{
              left.add(val);
          }
          if(left.size() - right.size() > 1){
              right.add(left.remove());
          }else if(right.size() - left.size() > 1){
              left.add(right.remove());
          }
        }
    
        public int remove() {
          // write your code here
          if(this.size() == 0){
              System.out.println("Underflow");
              return -1;
          }
          if(left.size() > right.size()){
              return left.remove();
          }else if(right.size() > left.size()){
              return right.remove();
          }else{
              return left.remove();
          }
        }
    
        public int peek() {
          // write your code here
          if(this.size() == 0){
              System.out.println("Underflow");
              return -1;
          }
          if(left.size() > right.size()){
              return left.peek();
          }else if(right.size() > left.size()){
              return right.peek();
          }else{
              return left.peek();
          }
        }
    
        public int size() {
          // write your code here
          return left.size() + right.size();
        }
      }

      
    public static void printKlargest(int[] arr,int k){
        // write your code here
      PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
      for(int i = 0;i<k;i++){
          pq1.add(arr[i]);
      }
      for(int i =k ;i<arr.length;i++){
          if(pq1.peek() <= arr[i]){
              pq1.remove();
              pq1.add(arr[i]);
          }
          
        }
      while(k > 0){
          System.out.println(pq1.peek());
          pq1.remove();
          k--;
      }
    }
    public static void ksortedArray(int[] arr,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i = 0;i<=k;i++){
            pq.add(arr[i]);
        }

        arr[0] = pq.peek();
        pq.remove();
        int j =1;
        for(int i =k+1;i<arr.length;i++){
            pq.add(arr[i]);

            arr[j] = pq.peek();
            pq.remove();
            j++;
        }

        while(pq.size() > 0){
            arr[j] = pq.peek();
            pq.remove();
            j++;
        }
    }
    public static void demo(){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(10);
        pq.add(20);
        pq.add(9);
        System.out.println(pq.peek());
        pq.add(7);
        pq.add(11);
        System.out.println(pq.peek());


        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(Collections.reverseOrder());
        pq1.add(10);
        pq1.add(9);
        System.out.println(pq1.peek());
        pq1.add(7);
        pq1.add(11);
        pq1.add(20);
        System.out.println(pq1.peek());
    }
    public static void main(String[] args){
        demo();
    }
}
