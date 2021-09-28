import java.util.*;
public class btree{
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

    static class Pair{
        Node node;
        int state;
        public Pair(Node node,int state){
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr){
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<Pair>();
        st.push(new Pair(root,0));
        int indx = 0;
        while(st.size() > 0){
            Pair p = st.peek();
            if(p.state == 0){
                indx++;
                if(arr[indx] != null){
                    Node nn = new Node(arr[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn,0));
                }
                p.state++;
            }else if(p.state == 1){
                indx++;
                if(arr[indx] != null){
                    Node nn = new Node(arr[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn,0));
                }
                p.state++;
            }else{
                st.pop();
            }
        }

        return root;
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
        }else{
            System.out.print("null");
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
        int leftsize = size(node.left);
        int rightsize = size(node.right);
        return leftsize + rightsize + 1;
    }

    public static int max(Node node){
        if(node == null){
            return Integer.MIN_VALUE;
        }

        int leftMax = max(node.right);
        int rightMax = max(node.right);
        int m = Math.max(leftMax,rightMax);
        return Math.max(m,node.data);
    }

    public static int sum(Node node){
        if(node == null){
            return 0;
        }
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);

        return leftSum + rightSum + node.data;

    }

    public static int height(Node node) {
        // write your code here
        if(node == null){
            return -1;
        }
        int leftHt = height(node.left);
        int rightHt = height(node.right);
        int ht = Math.max(leftHt,rightHt);
        return ht+1;
    }

    public static ArrayList preOrder(Node node){
        if(node == null){
            ArrayList<Integer> al = new ArrayList<Integer>();
            return al;
        }

        ArrayList<Integer> lal = preOrder(node.left);
        ArrayList<Integer> ral = preOrder(node.right);

        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(node.data);
        ans.addAll(lal);
        ans.addAll(ral);
        
        return ans;
    }

    public static ArrayList postOrder(Node node){
        if(node == null){
            ArrayList<Integer> al = new ArrayList<Integer>();
            return al;
        }

        ArrayList<Integer> lal = postOrder(node.left);
        ArrayList<Integer> ral = postOrder(node.right);

        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.addAll(lal);
        ans.addAll(ral);
        ans.add(node.data);
        
        return ans;
    }

    public static void levelOrder(Node node){
        Queue<Node> qu = new ArrayDeque<Node>();
        qu.add(node);
        while(qu.size() > 0){
            int sz = qu.size();
            while(sz-- > 0){
                Node rem = qu.remove();
                System.out.print(rem.data + " ");
                if(rem.left != null){
                    qu.add(rem.left);
                }
                if(rem.right != null){
                    qu.add(rem.right);
                }
            }
            System.out.println();
        }
    }

    public static boolean isBST(Node node){
        if(node == null) return true;
        
        int lmax = max(node.left);
        int rmin = min(node.right);
        
        if(lmax > node.data || rmin < node.data){
            return false;
        }
        
        boolean res = isBST(node.left);
        res = res && isBST(node.right);
        
        return res;
        
    }

    public static int min(Node node){
        if(node == null){
            return Integer.MAX_VALUE;
        }

        int leftMin = min(node.right);
        int rightMin = min(node.right);
        int m = Math.min(leftMin,rightMin);
        return Math.min(m,node.data);
    }

    public static class BSTPair{
        int min;
        int max;
        boolean isBST;
        public BSTPair(){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
        }

    }
    public static class BSTPairForLargestBST{
        int min;
        int max;
        boolean isBST;
        int size;
        public BSTPairForLargestBST(){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }
    public static BSTPair isBST2(Node node){
        BSTPair lres = isBST2(node.left);
        BSTPair rres = isBST2(node.left);
        boolean status = lres.max < node.data && rres.min > node.data;
        BSTPair myRes = new BSTPair();
        myRes.min = Math.min(node.data,Math.min(lres.min,rres.min));
        myRes.max = Math.max(node.data,Math.max(lres.max,rres.max));
        myRes.isBST = lres.isBST && rres.isBST && status ;

        return myRes;
    }

    public static class BalancePair{
        int height;
        boolean isBalance;
        public BalancePair(){
            this.height = -1;
            this.isBalance = true;
        }
    }
    static int size = 0;
    static Node bstNode = null;
    public static BSTPairForLargestBST largestBSTSubtree(Node node){
        if(node == null) return new BSTPairForLargestBST();
        BSTPairForLargestBST lres = largestBSTSubtree(node.left);
        BSTPairForLargestBST rres = largestBSTSubtree(node.right);
        
        boolean status = lres.max < node.data && rres.min > node.data;
        BSTPairForLargestBST myRes = new BSTPairForLargestBST();
        myRes.min = Math.min(node.data,Math.min(lres.min,rres.min));
        myRes.max = Math.max(node.data,Math.max(lres.max,rres.max));
        myRes.isBST = lres.isBST && rres.isBST && status ;
        myRes.size = lres.size + rres.size + 1;

        if(myRes.isBST && myRes.size > size){
            size = myRes.size;
            bstNode =  node;
        }
        return myRes;
        
        
    }
    public static BalancePair isBalance(Node node){
        if(node == null) return new BalancePair();

        BalancePair lres = isBalance(node.left);
        BalancePair rres = isBalance(node.right);
        boolean factor = Math.abs(lres.height - rres.height) <= 1;
        BalancePair mres = new BalancePair();
        mres.height  = Math.max(lres.height,rres.height) +1;
        mres.isBalance = factor && lres.isBalance && rres.isBalance;

        return mres;
    }
    public static void  fun(){
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = construct(arr);
        display(root);
        // System.out.println(size(root));
        // System.out.println(max(root));
        // System.out.println(sum(root));
        // System.out.println(height(root));
        // System.out.println(preOrder(root));
        // System.out.println(postOrder(root));
        levelOrder(root);
    }
    public static void main(String[] args){

        fun();
    }
}