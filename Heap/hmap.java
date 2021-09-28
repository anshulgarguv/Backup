import java.util.*;
class HashMap{
    private class Node{
        String key;
        int val;
        Node(String key,int val){
            this.key = key;
            this.val = val;
        }
    }

    private int size = 0;
    private LinkedList<Node>[] bucket;
    private void init(int cap){
        bucket = new LinkedList<Node>[cap];
        for(int i = 0;i<cap;i++){
            bucket[i] =  new LinkedList<>();
        }
    }
    public HashMap(){
        init(4);
    }

    private int hashfunction(String key){
        int bi = Math.abs(key.hashCode()) % bucket.length;

        return bi;
    }

    private int searchInBucket(String key,int bi){
        int di = -1;
        for(Node node : bucket[bi]){
            di++;
            if(node.key.equals(key) == true){
                return di;
            }
        }

        return -1;
    }
    public void put(String key,int val){
        int bi = hashfunction(key);

        int di = searchInBucket(key,bi);

        if(di == -1){
            bucket[bi].addLast(new Node(key,val));
        }else{
            bucket[bi].get(di).val = val;
        }

        int n = size;
        int N = bucket.length;
        double lambda = n*1.0 / N;

        if(lambda > 2){
            rehash();
        }
    }
    private void rehash(){
        LinkedList<Node>[] ob = bucket;
        init(2*ob.length);
        for(int i = 0;i<ob.length;i++){
            for(Node node : ob[i]){
                put(node.key,node.val);
            }
        }

    }
    public  int remove(String key){
        int bi = hashfunction(key);
        int di = searchInBucket(key,bi);

        if(di == -1){
            return -1;
        }else{
            Node rem = bucket[bi].remove(di);

            return rem.val;
        }
    }

    public int get(String key){
        int bi = hashfunction(key);
        int di = searchInBucket(key,bi);

        if(di == -1){
            return -1;
        }else{
            Node ans = bucket[bi].get(di);
            return ans.val;
        }
    }

    public boolean containsKey(String key){
        int bi = hashfunction(key);
        int di = searchInBucket(key,bi);

        if(di == -1){
            return false;
        }else{
            return true;
        }
    }

    public ArrayList<String> keySet(){
        ArrayList<String> list = new ArrayList<>();
        for(int bi = 0;bi<bucket.length;bi++){
            for(Node node : bucket[bi]){
                list.add(node.key);
            }
        }

        return list;
    }

    public void display(){
        for(int bi = 0;bi<bucket.length;bi++){
            for(Node node : bucket[bi]){
                System.out.print("[" + node.key+ " = "+node.val+"]");
            }
        }

    }
    public int size(){
        return this.size;
    }
}
public class hmap {
    public static void main(String[] args){

    }
}
