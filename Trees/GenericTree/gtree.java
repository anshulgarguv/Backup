import java.util.*;

public class gtree{

    public static class Node{
        int data;
        ArrayList<Node> children;

        public Node(){
            this.data = 0;
            this.children = new ArrayList<>();
        }

        public Node(int data){
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(Integer[] arr){
        Node root = null;
        Stack<Node> st = new Stack<>();

        for(int i = 0;i<arr.length;i++){
            Integer data = arr[i];
            if(data != null){
                Node nn = new Node(data);
                if(st.size() == 0){
                    root = nn;
                    st.push(nn);
                }else{
                    st.peek().children.add(nn);
                    st.push(nn);
                }
            }else{
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node root){

        String str  = "[" + root.data + "] -> ";
        for(Node child : root.children){
            str = str + child.data + ", ";
        }
        System.out.println(str + " .");

        for(int i = 0;i<root.children.size();i++){
            Node child = root.children.get(i);
            display(child);
        }
    }

    public static int size(Node root){
        int sz= 0;
        for(Node child: root.children){
            sz += size(child);
        }
        return sz+1;
    }

    public static int max(Node root){
        int mx = Integer.MIN_VALUE;
        for(Node child : root.children){
            mx = Math.max(mx,max(child));
        }
        return Math.max(mx,root.data);
    }

    public static int min(Node root){
        int mn = Integer.MAX_VALUE;
        for(Node child : root.children){
            mn = Math.min(mn,max(child));
        }
        return Math.min(mn,root.data);
    }

    public static int height(Node root){
        int ht = -1;

        for(Node child : root.children){
            ht = Math.max(height(child),ht);
        }

        return ht+1;
    }

    public static void traversal(Node root){
        
        System.out.println("Node Pre "+root.data);
        for(Node child : root.children){
            System.out.println("Edge Pre "+root.data+"--"+child.data);
            traversal(child);
            System.out.println("Edge Post "+root.data+"--"+child.data);
        }
        System.out.println("Node Post "+root.data);
    }

    public static void levelOrder(Node node){
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while(qu.size() > 0){
            // get + remove
            Node rem = qu.remove();
            //print
            System.out.print(rem.data+" ");
            //add children
            for(Node child : rem.children){
                qu.add(child);
            }
        }
        System.out.print(".");
    }

    public static void levelOrderLineWise(Node node){
        Queue<Node> qu = new ArrayDeque<>();
        Queue<Node> childQu = new ArrayDeque<>();
        qu.add(node);
        while(qu.size() > 0){
            // get + remove
            Node rem = qu.remove();
            //print
            System.out.print(rem.data+" ");
            //add children
            for(Node child : rem.children){
                childQu.add(child);
            }

            //if qu becomes empty childQu becomes main qu(swap)
            if(qu.size() == 0){
                System.out.println();
                Queue<Node> temp = qu;
                qu = childQu;
                childQu = temp;
            }
        }
    }

    public static void levelOrderLineWiseApproach2(Node node){
        Queue<Node> qu = new LinkedList<>();
        qu.add(node);
        qu.add(null);
        while(qu.size() > 0){
            if(qu.peek() != null){
                Node rem = qu.remove();
                //print
                System.out.print(rem.data+" ");
                //add children
                for(Node child : rem.children){
                    qu.add(child);
                }
            }else{
                qu.remove();
                System.out.println();
                if(qu.size() > 0){
                    qu.add(null);
                }
            }
            
            
        }
    }

    public static void levelOrderLineWiseApproach3(Node node){
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);

        while(qu.size() > 0){
            int sz = qu.size();

            while(sz-- > 0){
                Node rem = qu.remove();
                System.out.print(rem.data+" ");
                for(Node child : rem.children){
                    qu.add(child);
                }
            }
            System.out.println();  
        }
    }

    public static void levelOrderZigZag(Node node){
        int level = 1;
        Stack<Node> mainS = new Stack<>();
        Stack<Node> childS = new Stack<>();
        mainS.push(node);
        
        while(mainS.size() > 0){
            //while(mainS.size() > 0){
                Node rem = mainS.pop();
                System.out.print(rem.data+" ");
                if(level % 2 == 1){
                    for(int i = 0;i<rem.children.size();i++){
                        childS.push(rem.children.get(i));
                    }
                }else{
                    for(int i = rem.children.size()-1;i>=0;i--){
                        childS.push(rem.children.get(i));
                    }
                }
            //}
            if(mainS.size() == 0){
                System.out.println();
                level++;
                Stack<Node> temp = mainS;
                mainS = childS;
                childS = temp;  
            }
            
        }
    }

    public static void mirror(Node node){
        for(Node child : node.children){
            mirror(child);
        }
        // reverse children of current node
        int left = 0;
        int right = node.children.size() -1;
        while(left < right){
            Node temp = node.children.get(left);
            node.children.set(left,node.children.get(right));
            node.children.set(right,temp);
            left++;
            right--;
        }
    }

    public static void removeLeaf(Node node){
        //int size = node.children.size();
        for(int i =node.children.size()-1;i>=0;i--){
            Node child = node.children.get(i);
            if(child.children.size() == 0){
                node.children.remove(child);
            }
        }
        for(Node child : node.children){
            removeLeaf(child);
        }
    }

    public static void linearize(Node node){

        for(Node child : node.children){
            linearize(child);
        }

        for(int i = node.children.size() -2;i>=0;i--){
            Node last = node.children.get(i+1);
            Node slast = node.children.get(i);
            node.children.remove(i+1);
            Node tail = getTail(slast);
            tail.children.add(last);
        }
    }

    public static Node getTail(Node node){
        Node tail = node;
        while(tail.children.size() > 0){
            tail = tail.children.get(0);
        }
        return tail;
    }

    public static Node linearize2(Node node){
        if(node.children.size() == 0){
            return node;
        }
        Node lastNode = node.children.get(node.children.size()-1);
        Node tail = linearize2(lastNode);

        for(int i = node.children.size() -2;i>=0;i--){
            Node rem = node.children.remove(i+1);
            Node stail = linearize2(node.children.get(i));
            stail.children.add(rem);
        }

        return tail;
    }


    public static Node linearize3(Node node){
        if(node.children.size() == 0){
            return node;
        }
        // Node lastNode = node.children.get(node.children.size()-1);
        // Node tail = linearize2(lastNode);
        Node tail = node;
        for(int i = 0;i<node.children.size()-1;i++){
            tail = linearize3(node.children.get(i));
            Node second = node.children.get(i+1);
            tail.children.add(second);
        }
        tail = linearize3(node.children.get(node.children.size() -1));
        for(int i = node.children.size()-1;i>0;i--){
            node.children.remove(i);
        }
        return tail;
    }

    public static boolean find(Node node,int data){
        if(node.data == data){
            return true;
        }
        boolean res = false;
        for(Node child:node.children){
            res = find(child,data);
            if(res == true){
                return true;
            }
        }

        return res;
    }

    public static ArrayList<Integer> nodeToPath(Node node,int data){
        if(node.data == data){
            ArrayList<Integer> bres = new ArrayList<Integer>();
            bres.add(node.data);
            return bres;
        }
        for(Node child:node.children){
            ArrayList<Integer> rres = nodeToPath(child,data);
            if(rres.size() > 0){
                rres.add(node.data);
                return rres;
            }
        }
        return new ArrayList<Integer>();
    }

    public static int lca(Node node,int d1,int d2){
        ArrayList<Integer> path1 = nodeToPath(node,d1);
        ArrayList<Integer> path2 = nodeToPath(node,d2);
        // System.out.println(path1);
        // System.out.println(path2);
        int i = 0;
        int j = 0;
        if(path1.size() > path2.size()){
            i = path1.size() - path2.size();
        }else{
            j = path2.size() - path1.size();
        }
        // System.out.println(i);
        // System.out.println(j);
        while(path1.get(i) != path2.get(j) && i < path1.size() && j < path2.size()){
            i++;
            j++;
        }
        System.out.println(path1.get(i));
        return path1.get(i);
    }

    public static int distanceBetweenNodes(Node node,int d1,int d2){
        ArrayList<Integer> path1 = nodeToPath(node,d1);
        ArrayList<Integer> path2 = nodeToPath(node,d2);
        int dist = 0;
        int i = 0;
        int j = 0;
        if(path1.size() > path2.size()){
            i = path1.size() - path2.size();
            dist = dist + i;
        }else{
            j = path2.size() - path1.size();
            dist = dist + j;
        }
        while(path1.get(i) != path2.get(j) && i < path1.size() && j < path2.size()){
            i++;
            j++;
            dist += 2;
        }
        dist++;
        System.out.println(dist);
        return dist;
    }

    public static boolean shape(Node n1,Node n2){
        if(n1.children.size() != n2.children.size()){
            return false;
        }
        boolean res = true;
        // for(Node child:n1.children){
        //     res = traverse(child);
        //     if(res = false){
        //         return res;
        //     }
        // }

        // for(Node child:n2.children){
        //     res = traverse(child);
        //     if(res = false){
        //         return res;
        //     }
        // }
        for(int i = 0;i<n1.children.size();i++){
            Node child1 = n1.children.get(i);
            Node child2 = n2.children.get(i);
            res = shape(child1,child2);
            if(res == false){
                return res;
            }
        }

        return res;
    }

    public static boolean mirror(Node n1,Node n2){
        if(n1.children.size() != n2.children.size()){
            return false;
        }
        boolean res = true;
        int k = n1.children.size();
        for(int i =0;i<n1.children.size();i++){
            //Node child1 = n1.children.get(i);
            //Node child2 = n2.children.get(k-i-1);
            mirror(n1.children.get(i),n2.children.get(k));
            if(res == false){
                return res;
            }
        }

        return res;
    }

    public static boolean isSymmetric(Node node){
        return mirror(node,node);
    }
    static int diameter = 0;
    public static int heightForDiameter(Node node){
        int maxHt = -1;
        int smaxHt = -1;

        for(Node child : node.children){
            int ht = heightForDiameter(child);
            if(ht >= maxHt){
                smaxHt = maxHt;
                maxHt = ht;
            }else{
                smaxHt = ht;
            }

        }
        diameter = Math.max(diameter,maxHt+smaxHt+2);
        return maxHt + 1;
    }

    public static class Pair{
        Node node;
        int state;
        public Pair(Node node,int state){
            this.node =node;
            this.state = state;
        }
    }

    public static void IterativePreandPostOrder(Node node) {
    // write your code here
        Stack<Pair> st =  new Stack<Pair>();
        st.push(new Pair(node,0));
        ArrayList<Integer> preOrder = new ArrayList<Integer>();
        ArrayList<Integer> postOrder = new ArrayList<Integer>();
        while(st.size() > 0){
            Pair p = st.peek();
            if(p.state == 0){
                preOrder.add(p.node.data);
                p.state++;
            }else if(p.state <= p.node.children.size()){
                Node child = p.node.children.get(p.state -1);
                p.state++;
                st.push(new Pair(child,0));
            }else{
                postOrder.add(p.node.data);
                st.pop();
            }
        }

        for(int val : preOrder){
            System.out.println(val + " ");
        }
        System.out.println();
        for(int val : postOrder){
            System.out.println(val + " ");
        }
        System.out.println();
    }

    //-------Multisolver 1----//
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int size = 0;
    static int height = 0;

    public static void multiSolution(Node node){
        // multisolver(node,0);
        // System.out.println(min);
        // System.out.println(max);
        // System.out.println(height);
        // System.out.println(size);

        multiSolver res = multiSolver2(node);
        System.out.println(res.min);
        System.out.println(res.max);
        System.out.println(res.height);
        System.out.println(res.size);
    }
    
    public static void multisolver(Node node,int depth){
        min = Math.min(node.data,min);
        max = Math.max(node.data,max);
        height = Math.max(height,depth);
        size++;
        for(Node child :node.children){
            multisolver(child,depth +1);
        }
    }

    //--------Mutlisolver-----
    public static class multiSolver{
         int min;
         int max;
         int size;
         int height;

         public multiSolver(int min,int max,int height,int size){
             this.min = min;
             this.max = max;
             this.height = height;
             this.size = size;
         }

         public multiSolver(){

         }
    }

    public static multiSolver multiSolver2(Node node){
        multiSolver myres = new multiSolver(node.data,node.data,-1,1);

        for(Node child :node.children){
            multiSolver rres = multiSolver2(child);
            myres.min = Math.min(myres.min,rres.min);
            myres.max = Math.max(myres.max,rres.max);
            myres.height = Math.max(myres.height,rres.height);
            myres.size = myres.size + rres.size;
        }

        myres.height += 1;
        return myres;
    }
    static Node pred = null;
    static Node succ = null;
    static int state = 0;
    public static void predecessorAndSuccessor(Node node,int data){
        if(state == 0){
            if(node.data == data){
                state++;
            }else{
                pred = node;
            }
        }else if(state ==1){
            succ = node;
            state++;
        }else{
            
        }

        for(Node child : node.children){
            predecessorAndSuccessor(child,data);
        }
    }

    static int ceil;
    static int floor;
    public static void ceilfloor(Node node,int data){
        if(node.data < data){
            floor = Math.max(floor,node.data);
        }
        if(node.data > data){
            ceil = Math.min(ceil,node.data);
        }

        for(Node child :node.children){
            ceilfloor(child,data);
        }
    }

    public static int kthLargest(Node node,int k){
        int data = Integer.MAX_VALUE;
        for(int i =0;i<k;i++){
            floor = Integer.MIN_VALUE;
            ceilfloor(node,data);
            data = floor;
        }

        return data;
    }
    static int maxSum = Integer.MIN_VALUE;
    static int nodeData = 0;
    public static int maxSubtreeSum(Node node){
        int sum = 0;
        for(Node child : node.children){
            int childSum = maxSubtreeSum(child);
            sum = sum + childSum;
        }
        sum += node.data;
        if(sum >maxSum){
            nodeData = node.data;
            maxSum =  sum; 
        }
        return sum;
    }

    public static int treeSum(Node node){
        int sum = 0;
        for(Node child : node.children){
            int childSum = treeSum(child);
            sum = sum + childSum;
        }
        sum += node.data;
        System.out.println(node.data + "---->" + sum);
        return sum;
    }

    public static int diameter1(Node node){
        int mh = -1;
        int smh = -1;
        for(Node child :node.children){
            int ht = height(child);
            if(ht >= mh){
                smh = mh;
                mh = ht;
            }else if(ht > smh){
                smh = ht;
            }
        }

        int dfc = 0;
        for(Node child : node.children){
            dfc = Math.max(diameter1(child),dfc);
        }

        return Math.max(dfc,mh+smh +2);
    }
    public static void fun(){
        Integer[] data = {10,20,50,null,60,null,null,30,70,null,80,110,
                            null,120,null,null,90,null,null,40,100,null,null,null};
        
        //Integer[] data = {10,20,null,30,50,null,60,null,null,40,null,null};
        Node root = construct(data);
        //display(root);
        // System.out.println();
        // mirror(root);
        // display(root);
        //System.out.println(size(root));
        //System.out.println(max(root));
        //System.out.println(min(root));
        //System.out.println(height(root));
        //traversal(root);
        //levelOrder(root);
        //levelOrderLineWise(root);
        //levelOrderLineWiseApproach2(root);
        //levelOrderLineWiseApproach3(root);
        //levelOrderZigZag(root);
        //System.out.println();
        //removeLeaf(root);
        //linearize(root);
        //linearize3(root);
        //System.out.println(find(root,110));
        // ArrayList<Integer> ans = nodeToPath(root,80);
        // System.out.println(ans);
        //lca(root,50,120);
        //distanceBetweenNodes(root,50,120);
        //predecessorAndSuccessor(root,80);
        //display(root);
        //multiSolution(root);
        //treeSum(root);
        IterativePreandPostOrder(root);
    }

    public static void main(String[] args){
        fun();
    }
}