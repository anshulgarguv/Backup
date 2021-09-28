class linkedlist{
    private class Node{
        private int data;
        private Node next;
        public Node(){
            this.data = 0;
            this.next = null;
        }

        public Node(int data){
            this.data = data;
            this.next = null;
        }
        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }
    }
    private class ListNode{
        int val = 0;
        ListNode next = null;
        ListNode random = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public linkedlist(){
        this.head = this.tail = null;
        this.size = 0;
    }

    public void addFirst(int val){
        if(this.size == 0){
            Node n = new Node(val);
            this.head = n;
            this.tail = n;
            this.size++;
        }else{
            Node n = new Node(val,this.head);
            this.head = n;
            this.size++;
        }
        
    }
    public void addLast(int val){
        if(this.size == 0){
            Node n = new Node(val);
            this.head = n;
            this.tail = n;
            this.size++;
        }else{
            Node n = new Node(val);
            this.tail.next = n;
            tail = n;
            this.size++;
        }
        
    }
    public void addAt(int val,int index){
        if(index == 0){
           addFirst(val);
        }else if(index == this.size){
            addLast(val);
        }else{
            Node temp = this.head;
            int i = 0;
            while(i<index-1){
                temp = temp.next;
                i++;
            }
            Node n = new Node(val);
            n.next = temp.next;
            temp.next = n;
            this.size++;
        }
        
    }

    public int getFirst(){
        return this.head.data;
    }
    public int getLast(){
        return this.tail.data;
    }
    public int getAt(int index){
        if(index < 0 || index >= this.size){
            return -1;
        }
        Node temp = this.head;
        int i = 0;
        while(i<index){
            temp = temp.next;
            i++;
        }
        return temp.data;
    }

    public int removeFirst(){
        if(this.size ==0){
            return -1;
        }else if(this.size ==  1){
            int val  = this.head.data;
            this.head = this.tail = null;
            this.size--;
            return val;
        }else{
            int val = this.head.data;
            this.head = this.head.next;
            this.size--;
            return val;
        }
        
    }
    public int removeLast(){
        if(this.size ==0){
            return -1;
        }else if(this.size ==  1){
            int val  = this.tail.data;
            this.head = this.tail = null;
            this.size--;
            return val;
        }else{
            int val = this.tail.data;
            Node temp = this.head;
            int i = 0;
            while(i<this.size-2){
                temp = temp.next;
                i++;
            }
            this.tail = temp;
            temp.next = null;
            this.size--;
            return val;
        }
        
        

    }
    public int removeAt(int index){
        if(index < 0 || index >= this.size){
            return -1;
        }else if(index == 0){
            removeFirst();
        }else if(index == this.size -1){
            removeLast();
        }else{
            Node temp = this.head;
            int i = 0;
            while(i<index-1){
                temp = temp.next;
                i++;
            }

            int val = temp.next.data;
            temp.next = temp.next.next;
            this.size--;
            return val;
        }
        
        return -1;
    }

    public int size(){
        return this.size;
    }
    public void display(){
        Node temp = this.head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public linkedlist mergeTwoSortedLinkedList(linkedlist l1,linkedlist l2){
        linkedlist ln = new linkedlist();
        Node temp1 = l1.head;
        Node temp2 = l2.head;
        while(temp1 != null && temp2 != null){
            if(temp1.data < temp2.data){
                ln.addLast(temp1.data);
                temp1 = temp1.next;
            }else{
                ln.addLast(temp2.data);
                temp2 = temp2.next;
            }
        }

        while(temp1 != null){
            ln.addLast(temp1.data);
            temp1 = temp1.next;
        }
        while(temp2 != null){
            ln.addLast(temp2.data);
            temp2 = temp2.next;
        }

        return ln;
    }

    public Node mergeLinkedList(linkedlist l1,linkedlist l2){
        Node temp1 = l1.head;
        Node temp2 = l2.head;
        Node dummy = new Node(-1);
        Node temp = dummy;
        while(temp1 != null && temp2 != null){
            if(temp1.data < temp2.data){
                temp.next = temp1;
                temp = temp.next;
                temp1 = temp1.next;
            }else{
                temp.next = temp2;
                temp = temp.next;
                temp2 = temp2.next;
            }
        }

        if(temp1 == null){
            temp.next = temp2;
        }else{
            temp.next = temp1;
        }

        return dummy.next;
    }
    public boolean IsPalindrome() {
      // write your code here
      int left = 0;
        int right = this.size -1;
        while(left < right){
            Node rnode = getNthNode(right);
            Node lnode = getNthNode(left);
            if(rnode.data != lnode.data){
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }

    public boolean IsPalindromeOn() {
        Node head1 = this.head;

        Node mid = getMidNode(head1);
        Node head2 = mid.next;
        mid.next = null;

        head2 = reverse(head2);
        Node t1 = head1;
        Node t2  = head2;
        boolean res = true;
        while(t1 != null && t2 != null){
            if(t1.data != t2.data){
                res = false;
                break;
            }
            t1 = t1.next;
            t2 = t2.next;
        }

        head2 = reverse(head2);
        mid.next = head2;

        return res;
    }
    public void fold(){
        if (this.head == null){
            return;
        }

        Node head1 = this.head;
        Node mid = getMidNode(head1);
        Node head2 = mid.next;
        mid.next = null;
        head2 = reverse(head2);
        Node t1 = head1;
        Node t2  = head2;

        while(t1 != null && t2 != null){
            Node  n1 = t1.next;
            Node  n2 = t2.next;
            t1.next = t2;
            t1 = n1;
            t2.next = n1;
            t2 = n2;
        }
        t1 = head1;

        while(t1.next != null){
            t1 = t1.next;
        }
        this.head = head1;
        this.tail = t1;
    }


    public Node reverse(Node node){
        Node prev = null;
        Node curr = node;
        while(curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr= next;
            
        }

        return prev;
    }

    private Node getMidNode(Node node){
        Node slow = node;
        Node fast = node.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public Node  getNthNode(int idx){
        Node temp = head;
        for (int i = 0; i < idx; i++) {
          temp = temp.next;
        }
        return temp;
    }
    public void kreverse(int k){
        linkedlist prev = null;
        while(this.size > 0){
            linkedlist curr = new linkedlist();
            if(this.size >= k){
                //removeFirst from this and addFirst in curr
                for(int i = 0;i<k;i++){
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addFirst(data);
                }
            }else{
                while(this.size > 0){
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addLast(data);
                }
            }

            if(prev == null){
                prev.head = curr.head;
                prev.tail = curr.tail;
                prev.size = curr.size;
            }else{
                prev.tail.next = curr.head;
                prev.tail = curr.tail;
                prev.size += curr.size;
            }
        }

        this.head = prev.head;
        this.tail = prev.tail;
        this.size = prev.size;
    }

    public linkedlist addListRecursion(linkedlist one,linkedlist two){
        linkedlist res = new linkedlist();
        int carry = addHelper(one.head,one.size,two.head,two.size,res);

        if(carry>0){
            res.addFirst(carry);
        }

        return res;
    }

    public static int addHelper(Node one,int i1,Node two,int i2,linkedlist res){
        if(one == null && two == null){
            return 0;
        }
        int d1 = one.data;
        int d2 = two.data;
        int sum = 0;
        if(i1>i2){
            int carry = addHelper(one.next,i1 -1,two,i2,res);
            sum = d1+carry;
        }else if(i1<i2){
            int carry = addHelper(one,i1,two.next,i2-1,res);
            sum = d2+carry;
        }else{
            int carry = addHelper(one.next,i1-1,two.next,i2-1,res);
            sum = d1+d2+carry;
        }
        res.addFirst(sum%10);
        return sum/10;
    }

    public  ListNode copyRandomList(ListNode head){
        ListNode dummy = new ListNode(-1);
        ListNode t1 = dummy;
        ListNode t2 = head;
        while(t2!= null){
            ListNode nn = new ListNode(t2.val);
            t1.next = nn;
            t1 = nn;
            t2= t2.next;

        }

        ListNode head2= dummy.next;
        t1 = head;
        t2 = head2;
        while(t1 != null && t2 != null){
            ListNode n1 = t1.next;
            ListNode n2 = t2.next;
            t1.next = t2;
            t2.next = n1;
            t1 = n1;
            t2 = n2;
        }

        t1 = head;
        while(t1 != null){
            t1.next.random = t1.random == null ? null : t1.random.next;
        }

        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        t1 = dummy1;
        t2 = dummy2;
        ListNode temp = head;
        while(temp != null){
            t1.next = temp;
            t2.next = temp.next;
            t1 = t1.next;
            t2 = t2.next;
            temp = temp.next.next;
        }
        
        t1.next = null;
        t2.next = null;
        

        return dummy2.next;
    }
}
public class creation{
    public static void main(String[] args){
        linkedlist list = new linkedlist();
        list.addLast(10);
        list.addLast(20);
        list.display();
        list.addLast(30);
        list.addFirst(9);

        list.display();
        list.addFirst(7);
        list.addLast(40);
        list.display();

        System.out.println(list.removeFirst());
        list.addAt(40,2);
        list.display();

        System.out.println(list.removeAt(3));
        System.out.println(list.getAt(3));
        list.addLast(90);
        list.addLast(85);
        list.addLast(40);
        list.addLast(70);
        list.addLast(60);

        list.display();

        System.out.println(list.size());
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeAt(3);
        list.display();

        list.removeLast();
        list.removeLast();
        list.display();
    }
}