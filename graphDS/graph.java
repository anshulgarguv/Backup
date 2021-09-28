import java.util.*;
public class graph {
    public static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph,int src,int nbr,int wt){
        graph[src].add(new Edge(src,nbr,wt));
        graph[nbr].add(new Edge(nbr,src,wt));
    }

    public static void display(ArrayList<Edge>[] graph){
        for(int i = 0;i<graph.length;i++){
            String str = "";
            str = str +"["+i+"]" + "-->";
            for(Edge e : graph[i]){
                str = str+"(" + e.src+"->"+e.nbr+"@"+e.wt+"),";
            }
            System.out.println(str);
        }
    }

    public static void printPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited,String path,int totalWt){
        if(src == dest){
            System.out.println(path + "@"+totalWt);
            return;
        }
        visited[src] = true;
        for(Edge e : graph[src]){
            if(visited[e.nbr] == false){
                printPath(graph,e.nbr,dest,visited,path + ""+ e.nbr,totalWt+e.wt);
            }
            
        }
        visited[src] = false;
        
    }

    public static boolean hasPath(ArrayList<Edge>[] graph,int src,int dest,boolean[] visited){
        if(src == dest){
            return true;
        }
        visited[src] = true;
        for(Edge e : graph[src]){
            if(visited[e.nbr] == false){
                boolean hasP = hasPath(graph,e.nbr,dest,visited);
                if(hasP == true){
                    return true;
                }
            }
            
        }
        visited[src] = false;
        return false;
        
    }
    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;
  
        Pair(int wsf, String psf){
           this.wsf = wsf;
           this.psf = psf;
        }
  
        public int compareTo(Pair o){
           return this.wsf - o.wsf;
        }
     }
    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
        if(src == dest){
               //psf+=dest;
               if(wsf > lpathwt){
                   lpathwt = wsf;
                   lpath = psf;
               }
               if(wsf < spathwt){
                   spathwt = wsf;
                   spath = psf;
               }
   
               if(wsf > criteria){
                   if(wsf < cpathwt){
                       cpathwt = wsf;
                       cpath = psf;
                   }
               }
   
               if(wsf < criteria){
                   if(wsf > fpathwt){
                       fpathwt = wsf;
                       fpath = psf;
                   }
               }
   
               if(pq.size() < k){
                   pq.add(new Pair(wsf,psf));
               }else{
                   if(pq.peek().wsf < wsf){
                       pq.remove();
                       pq.add(new Pair(wsf,psf));
                   }
               }
               return;
           }
           visited[src] = true;
           for(Edge e : graph[src]){
               if(visited[e.nbr] == false){
                   multisolver(graph,e.nbr,dest,visited,criteria,k,psf + ""+ e.nbr,wsf+e.wt);
               }
               
           }
           visited[src] = false;
           
      }
    
    public static ArrayList<ArrayList<Integer>> getConnectedComps(ArrayList<Edge>[] graph){
        // boolean[] visited =  new boolean[graph.length];
        // ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        // for(int i = 0;i<graph.length;i++){
        //     if(visited[i] == false){
        //         if(graph[i].size() == 0){
        //             ans.add(new ArrayList<Integer>(Arrays.asList(i)));
        //         }else{

        //             ArrayList<Integer> srcNodes =  new ArrayList<Integer>();
        //             srcNodes.add(i);
        //             visited[i] = true;
        //             for(Edge e : graph[i]){
        //                 srcNodes.add(e.nbr);
        //                 visited[e.nbr] = true;
        //             }
        //         }
        //     }
        // }

        ArrayList<ArrayList<Integer>> comps = new ArrayList<ArrayList<Integer>>();
        int n = graph.length;
        boolean[] visited =  new boolean[n];  
        for(int v = 0;v<n;v++){
            if(visited[v] == false){
                ArrayList<Integer> comp = gcc2(graph,v,visited);
                comps.add(comp);
                // ArrayList<Integer> comp = new ArrayList<Integer>();
                // getConnectedComp(graph,v,visited,comp);
                // comps.add(comp);
            }
        }  
        return comps;

    }

    public static void getConnectedComp(ArrayList<Edge>[] graph,int v,boolean[] visited,ArrayList<Integer> comp){
        visited[v] = true;
        comp.add(v);
        for(Edge e : graph[v]){
            int nbr = e.nbr;
            if(visited[nbr] == false){
                getConnectedComp(graph,nbr,visited,comp);
            }
        }

    }

    public static ArrayList<Integer> gcc2(ArrayList<Edge>[] graph,int v,boolean[] visited){
        ArrayList<Integer> mres = new ArrayList<Integer>();
        mres.add(v);
        visited[v] = true;
        for(Edge e : graph[v]){
            int nbr = e.nbr;
            if(visited[nbr] == false){
                ArrayList<Integer> rres = gcc2(graph,nbr,visited);
                for(int val : rres){
                    mres.add(val);
                }
            }
        }
        return mres;
    }

    public static boolean isConnectedGraph(ArrayList<Edge>[] graph){
        boolean[] visited =  new boolean[graph.length];
        visited[0] = true;
        ArrayList<Integer> comp = new ArrayList<Integer>();
        getConnectedComp(graph,0,visited,comp);
        
        if(comp.size() == graph.length){
            return true;
        }

        return false;
    }

    public static int island(int[][] arr){
        int count = 0;
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr[0].length;j++){
                if(arr[i][j] == 0){
                    count++;
                    getConnectedIsland(arr,i,j);
                }
            }
        }

        return count;
    }

    public static void getConnectedIsland(int[][] arr,int i,int j){
        arr[i][j] = 1;
        if(i+1<arr.length && j<arr[0].length && arr[i+1][j] == 0){
            getConnectedIsland(arr,i+1,j);
        }

        if(i<arr.length && j+1<arr[0].length && arr[i][j+1] ==0){
            getConnectedIsland(arr,i,j+1);
        }

        if(i-1<arr.length && j<arr[0].length && arr[i-1][j] == 0){
            getConnectedIsland(arr,i-1,j);
        }
        
        if(i<arr.length && j-1<arr[0].length && arr[i][j-1] == 0){
            getConnectedIsland(arr,i,j-1);
        }
    }
      public static void fun(){
        // int n = 7;
        // ArrayList<Edge>[] graph = new ArrayList[n];
        // for(int i = 0;i<graph.length;i++){
        //     graph[i] = new ArrayList<Edge>();
        // }
        // int[][] data = {
        //     {0,3,40},
        //     {0,1,10},
        //     {1,2,10},
        //     {2,3,10},
        //     {3,4,2},
        //     {4,5,3},
        //     {5,6,3},
        //     {4,6,8}
        // };

        // for(int i = 0;i<data.length;i++){
        //     addEdge(graph,data[i][0],data[i][1],data[i][2]);
        // }

        // //display(graph);
        // int src = 0;
        // int dest = 6;
        // boolean[] visited = new boolean[graph.length];
        // printPath(graph,src,dest,visited,src+"",0);

        // boolean ans = hasPath(graph,src,dest,visited);
        // System.out.print(ans);
        
    }

    public static int getNumberOfPerfectFriends(ArrayList<Edge>[] graph){
        int ans = 0;
        ArrayList<Integer> comps = new ArrayList<Integer>();
        int n = graph.length;
        boolean[] visited =  new boolean[n];  
        for(int v = 0;v<n;v++){
            if(visited[v] == false){
                ArrayList<Integer> comp = new ArrayList<Integer>();
                getConnectedCompForFriends(graph,v,visited,comp);
                comps.add(comp.size());
            }
        }
        
        int sum = comps.get(comps.size()-1);
        for(int i = comps.size()-2;i>=0;i--){
            ans = ans + sum*comps.get(i);
            sum = sum + comps.get(i);
        }
        return ans;
    }

    public static void getConnectedCompForFriends(ArrayList<Edge>[] graph,int v,boolean[] visited,ArrayList<Integer> comp){
        visited[v] = true;
        comp.add(v);
        for(Edge e : graph[v]){
            int nbr = e.nbr;
            if(visited[nbr] == false){
                getConnectedCompForFriends(graph,nbr,visited,comp);
            }
        }

    }
    public static class BFSPair{
        int vtx;
        String psf;
        BFSPair(int vtx,String psf){
            this.vtx = vtx;
            this.psf = psf;
        }
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph){
        int n = graph.length;
        boolean[] vis = new boolean[n];
        for(int v = 0;v < n;v++){
            if(vis[v] == false){
                //boolean res = bfsForCycle(graph,v,vis);
                boolean res = dfsForCycle(graph,v,vis,-1);
                if(res==true){
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean bfsForCycle(ArrayList<Edge>[] graph,int src,boolean[] vis){
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, "" + src));
        //boolean[] vis = new boolean[graph.length];
        while(qu.size() > 0){
            BFSPair rem = qu.remove();
            if(vis[rem.vtx] == true){
                return true;
            }else{
                vis[rem.vtx] = true;
            }

            System.out.print(rem.vtx + "@" + rem.psf);

            for(Edge e : graph[rem.vtx]){
                if(vis[e.nbr] == false){
                    qu.add(new BFSPair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
        return false;
    }
    
    public static boolean dfsForCycle(ArrayList<Edge>[] graph, int src,boolean[] vis,int parent){
        vis[src] = true;
        for(Edge e:graph[src]){
            int nbr = e.nbr;
            if(vis[nbr] == true && nbr!=parent){
                return true;
            }
            if(vis[nbr] == false){
                boolean res = dfsForCycle(graph,nbr,vis,src);
                if(res == true)return true;
            }
            
        }
        return false;
    }

    public static void bfs(ArrayList<Edge>[] graph,int src){
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, "" + src));
        boolean[] vis = new boolean[graph.length];
        while(qu.size() > 0){
            BFSPair rem = qu.remove();
            if(vis[rem.vtx] == true){
                continue;
            }else{
                vis[rem.vtx] = true;
            }

            System.out.print(rem.vtx + "@" + rem.psf);

            for(Edge e : graph[rem.vtx]){
                if(vis[e.nbr] == false){
                    qu.add(new BFSPair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph){
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis,-1);

        for(int v = 0;v<n;v++){
            if(vis[v] == -1){
                boolean res = isBipartiteComp(graph,v,vis);
                if(res == false) return false;
            }
        }

        return true;
    }
    static class BPair{
        int vtx;
        int level;
        BPair(int vtx,int level){
            this.vtx = vtx;
            this.level = level;
        }
    }
    public static boolean isBipartiteComp(ArrayList<Edge>[] graph,int src,int[] vis){
        Queue<BPair> qu = new ArrayDeque<>();
        qu.add(new BPair(src,0));

        while(qu.size() > 0){
            BPair rem = qu.remove();
            
            if(vis[rem.vtx] != -1){
                //already discovered
                if(vis[rem.vtx] == rem.level){
                    continue;
                }else{
                    return false;
                }
            }
            vis[rem.vtx] = rem.level;
            for(Edge e: graph[rem.vtx]){
                int nbr = e.nbr;
                if(nbr == -1){
                    qu.add(new BPair(nbr,rem.level +1));
                }
            }
        }

        return true;
    }

    public static void spreadOfInfection(ArrayList<Edge>[] graph,int src,int t){
        Queue<BFSPair> qu = new LinkedList<>();
        qu.add(new BFSPair(src, "" + src));
        boolean[] vis = new boolean[graph.length];
        int level = 1;
        int ans = 0;
        while(qu.size() > 0 && level <=t){
            int size = qu.size();
            while(size-- > 0){
                
                BFSPair rem = qu.remove();
                if(vis[rem.vtx] == true){
                    continue;
                }else{
                    vis[rem.vtx] = true;
                    ans++;
                }

                System.out.print(rem.vtx + "@" + rem.psf);

                for(Edge e : graph[rem.vtx]){
                    if(vis[e.nbr] == false){
                        qu.add(new BFSPair(e.nbr, rem.psf + e.nbr));
                    }
                }
            }
            level++;
        }
        System.out.print(ans);
    }
    static class DPair implements Comparable<DPair>{
        int vtx;
        String psf;
        int wsf;
        DPair(int vtx,String psf,int wsf){
            this.vtx = vtx;
            this.psf = psf;
            this.wsf = wsf;
        }
        public int compareTo(DPair o){
            return this.wsf - o.wsf;
        }
    }
    public static void dijkstra(ArrayList<Edge>[] graph,int src){
        PriorityQueue<DPair> pq =  new PriorityQueue<>();
        pq.add(new DPair(src,src+"",0));
        boolean[] vis = new boolean[graph.length];
        while(pq.size() > 0){
            DPair rem = pq.remove();
            if(vis[rem.vtx] == true){
                continue;
            }
            vis[rem.vtx] = true;
            System.out.println(rem.vtx + " via "+rem.psf+" @ "+rem.wsf);
            for(Edge e : graph[rem.vtx]){
                int nbr = e.nbr;
                if(vis[nbr] == false){
                    pq.add(new DPair(nbr,rem.psf + nbr,rem.wsf + e.wt));
                }
            }
        }
    }

    static class Phelper implements Comparable<Phelper>{
        int v;
        int parent;
        int wt;
        Phelper(int v,int parent,int wt){
            this.v =v;
            this.parent = parent;
            this.wt = wt;
        }

        public int compareTo(Phelper o){
            return this.wt - o.wt;
        }
    }

    public static void prims(ArrayList<Edge>[] graph){
        PriorityQueue<Phelper> pq = new PriorityQueue<>();
        pq.add(new Phelper(0,-1,0));
        boolean[] vis = new boolean[graph.length];
        while(pq.size() > 0){
            Phelper rem = pq.remove();

            if(vis[rem.v] == true){
                continue;
            }
            vis[rem.v] = true;

            if(rem.parent != -1){
                System.out.println("["+rem.v + "-"+rem.parent+"@"+rem.wt+"]");
            }

            for(Edge e : graph[rem.v]){
                if(vis[e.nbr] == false){
                    pq.add(new Phelper(e.nbr,rem.v,e.wt));
                }
            }
        }

    }

    public static void tsHelper(ArrayList<Edge>[] graph,int src,boolean[] vis,Stack<Integer> st){
        vis[src] = true;
        for(Edge e:graph[src]){
            if(vis[e.nbr] == false){
                tsHelper(graph,e.nbr,vis,st);
            }
        }
        st.push(src);
    }
    public static void topologicalSort(ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[graph.length];
        Stack<Integer> st = new Stack<>();
        for(int i = 0;i<graph.length;i++){
            if(vis[i] == false){
                tsHelper(graph,i,vis,st);
            }
        }

        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }

    public static boolean isDirectedCyclic(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // code here
        boolean[] vis = new boolean[V];
        boolean[] mypath = new boolean[V];
        for(int i =0;i<V;i++){
                boolean ans = cycleRecur(i,adj,vis,mypath);
                if(ans == true){
                    return true;
                }
            
        }
        
        return false;
        
    }
    
    public static boolean cycleRecur(int vtx,ArrayList<ArrayList<Integer>> adj,boolean[] vis,boolean[] mypath){
        vis[vtx] = true;
        mypath[vtx] = true;
        for(Integer it : adj.get(vtx)){
            
            if(mypath[it] == true){
                return true;
            }
            else if(vis[it] == false){
                boolean res = cycleRecur(it,adj,vis,mypath);
                if(res == true) return true;
            }
            // if(vis[it] == true){
            //     return false;
            // }
            
            
        }
        
        mypath[vtx] = false;
        
        return false;
    }
    public static void main(String args[]){
        fun();
    }
}
