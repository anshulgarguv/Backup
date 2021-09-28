import java.util.*;
public class bst {
    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            this.left = this.right = null;
        }

        public Node(int data,Node left,Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    public static Node construct(Integer[] arr,int lo,int hi){
        if(lo >hi ){
            return null;
        }
        int mid = (lo+hi) /2;
        Node nn = new Node(arr[mid]);

        nn.left = construct(arr,lo,mid-1);
        nn.right = construct(arr,mid+1,hi);

        return nn;
    } 
    public static class Pair{
        Node node;
        int state;
        Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }
    public static int inorderItr(Stack<Pair> ls){
        while(ls.size() > 0){
            Pair p = ls.peek();
            if(p.state == 0){
                if(p.node.left != null){
                    ls.push(new Pair(p.node.left,0));
                }
                p.state++;
            }else if(p.state == 1){
                if(p.node.right!=null){
                    ls.push(new Pair(p.node.right,0));
                }
                p.state++;
                return p.node.data;
            }else{
                ls.pop();
            }
        }

        return -1;
    }
    public static int revInorderItr(Stack<Pair> rs){
        while(rs.size() > 0){
            Pair p = rs.peek();
            if(p.state == 0){
                if(p.node.right != null){
                    rs.push(new Pair(p.node.right,0));
                }
                p.state++;
            }else if(p.state == 1){
                if(p.node.left!=null){
                    rs.push(new Pair(p.node.left,0));
                }
                p.state++;
                return p.node.data;
            }else{
                rs.pop();
            }
        }

        return -1;
    }
    public static void targetSumPair(Node node,int data){
        Stack<Pair> ls = new Stack<Pair>();
        Stack<Pair> rs = new Stack<Pair>();

        ls.push(new Pair(node,0));
        rs.push(new Pair(node,0));
        int left = node.left.data;
        int right = node.right.data;

        while(left < right){
            if(left + right > data){
                right = revInorderItr(rs);
            }else if(left + right < data){
                left = inorderItr(ls);
            }else{
                System.out.print(left + " " + right);
            }
        }
    }
    public static void display(Node node){
        if(node == null){
            return;
        }
        if(node.left != null){
            System.out.print(node.left.data);
        }else{
            System.out.print("null");
        }
        System.out.print("<---");
        if(node != null){
            System.out.print(node.data);
        }
        System.out.print("--->");
        if(node.right!=null){
            System.out.print(node.right.data);
        }else{
            System.out.print("null");
        }
        System.out.println();
        display(node.left);
        display(node.right);
    }
    
    public static int size(Node node){
        if(node == null){
            return 0;
        }
        int lsize = size(node.left);
        int rsize = size(node.right);

        return lsize+rsize+1;
    }

    public static int sum(Node node){
        if(node == null){
            return 0;
        }
        int ltree = sum(node.left);
        int rtree = sum(node.right);

        return ltree+rtree+node.data;
    }

    public static int max(Node node){
        if(node == null){
            return Integer.MIN_VALUE;
        }
        //int lmax = max(node.left);
        int rmax = max(node.right);
        
        //int m = Math.max(lmax,rmax);

        return Math.max(rmax,node.data);
    }

    public static int min(Node node){

        if(node == null){
            return Integer.MAX_VALUE;
        }
        int lmin = min(node.left);
        //int rmin = min(node.right);
        
        //int m = Math.min(lmin,rmin);

        return Math.min(lmin,node.data);
    }

    public static void printPair(Node node,Node root,int data){
        if(node == null){
            return;
        }
        int d1 = node.data;
        findOther(root,d1,data-d1);
        printPair(node.left,root,data);
        printPair(node.right,root,data);
        
    }
    
    public static void findOther(Node node,int d1,int d2){
        if(node == null) return;
        if(d2 > node.data){
            findOther(node.right,d1,d2);
        }else if(d2 < node.data){
            findOther(node.left,d1,d2);
        }else{
           if(d2 > d1){
               System.out.println(d1 + " "+ d2);
           } 
        }
    }
    public static boolean find(Node node,int data){
        if(node.data == data){
            return true;
        }

        boolean res  = false;
        if(node.right!= null && data > node.data && res == false){
            res = find(node.right,data);
        }else if(node.left!= null && res == false && data < node.data){
            res = find(node.left,data);
        }

        return res;
    }
    public static void  fun(){
        Integer[] arr = {10,20,30,40,50,60,70,80,90,100};
        Node root = construct(arr,0,arr.length-1);
        display(root);
        System.out.println(max(root));
        System.out.println(min(root));
        System.out.println(sum(root));
        System.out.println(size(root));
        System.out.println(find(root,500));
    }
    public static void main(String[] args){

        fun();
    }
}
