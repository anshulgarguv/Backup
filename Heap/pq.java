import java.util.*;
class priorityqueue{
    private ArrayList<Integer> data;
    private boolean flag;
    // if flag is false - min priority..flag is true than max priority
    priorityqueue(){
        data = new ArrayList<>();
        flag = false;
    }

    priorityqueue(boolean flag){
        data = new ArrayList<>();
        this.flag = flag;
    }

    priorityqueue(int[] arr){
        data = new ArrayList<>();
        this.flag = false;
        processArray(arr);
    }

    priorityqueue(int[] arr,boolean flag){
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);
    }

    private void processArray(int[] arr){
        //Complexity more than nlogn
        // for(int i = 0;i<arr.length;i++){
        //     add(arr[i]);
        // }
        
        //Complexity is nearly O(n)
        for(int val : arr){
            data.add(val);
        }
        for(int i = arr.length-1;i>=0;i--){
            downheapify(i);
        }
    }

    private int checkPriority(int child,int parent){
        if(flag == true){
            if(data.get(child) > data.get(parent)){
                return 1;
            }
        }else{
            if(data.get(child) < data.get(parent)){
                return 1;
            }
        }

        return 0;
    }

    public void add(int val){
        data.add(val);
        upheapify(data.size() -1);
    }

    public int remove(){
        if(data.size() == 0){
            System.out.println("Underflow");
            return -1;
        }
        int temp = data.get(data.size()-1);
        data.set(data.size() -1,data.get(0));
        data.set(0,temp);

        int val = data.remove(data.size() -1);

        downheapify(0);
        return val;
    }

    public void downheapify(int index){
        if(index == data.size() -1){
            return;
        }
        int child1Index = 2*index + 1;
        int child2Index = 2*index  + 2;
        int swapIndex = 0;
        if(data.get(child1Index) < data.get(child2Index)){
            swapIndex = child1Index;
        }else if(data.get(child2Index) < data.get(child1Index)){
            swapIndex = child2Index;
        }
        if(data.get(swapIndex) < data.get(index)){
            int temp = data.get(index);
            data.set(index,data.get(swapIndex));
            data.set(swapIndex,temp);
            downheapify(swapIndex);
        }
    }

    public int peek(){
        if(data.size() == 0){
            System.out.println("Underflow");
            return -1;
        }

        return data.get(0);
    }
    public void upheapify(int index){
        if(index == 0){
            return;
        }
        int pi = (index -1)/2;
        if(checkPriority(index, pi) > 0){
            int temp = data.get(index);
            data.set(index,data.get(pi));
            data.set(pi, temp);

            upheapify(pi);
        }
    }

    public int size(){
        return data.size();
    }


}

public class pq {
    
}
