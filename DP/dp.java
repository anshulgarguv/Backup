public class dp {

    public static int fib_rec(int n){
        if(n == 0|| n==1){
            return n;
        }

        int term1 = fib_rec(n-1);
        int term2 = fib_rec(n-2);

        return term1+term2;
    }

    public static int fib_memo(int n,int[] dp){
        if(n == 0|| n==1){
            return dp[n] =n;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        int term1 = fib_memo(n-1,dp);
        int term2 = fib_memo(n-2,dp);

        return dp[n] = term1+term2;
    }

    public static int fib_tab(int n,int[] dp){
        //figure out repitition
        //make a storage of n + 1 
        //assign meaning to the cell dp[i] = dp[i-1] + dp[i-2]
        // smallest problem at n= 0 
        // prerequisite -> base case

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public static int fib_btr(int n){
        int a = 0;
        int b = 1;
        int c= 1;
        while(n-- > 0){
            c = a + b;
            a = b;
            b = c;
        }

        return a;
    }
    public static void fib(){
        int n = 5;
        int[] dp = new int[n+1];
        int res = fib_memo(n,dp);
        System.out.print(res);

    }

    public static int climbStairs_memo(int n,int[] dp){
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        int n1= climbStairs_memo(n-1,dp);
        int n2 = climbStairs_memo(n-2,dp);
        int n3 = climbStairs_memo(n-3,dp);
        
        return dp[n] = n1 + n2 + n3;
    }


    public static int climbStairs_tab(int n,int[] dp){
        dp[0] = 1;

        for(int i =1;i<=n;i++){
            if(i >= 3){
                dp[i] = dp[i-1] + dp[i-2] + dp[i-3]; 
            }else if(i >= 2){
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }

        return dp[n];
    }


    public static int climbStairs_minMoves_rec(int i,int n,int[] jumps){
        if(i == n)return 0;
        

        int minJumps =(int)1e9;

        for(int jump = 1;jump < jumps[i] && jump + i <= n;jump++){
            minJumps = Math.min(minJumps,climbStairs_minMoves_rec(i+jump,n,jumps));
        }

        return minJumps == 1e9 ? minJumps : minJumps+1;
    }

    public static int climbStairs_minMoves_memo(int i,int n,int[] jumps,int[] dp){
        if(i == n)return dp[i] = 0;
        
        if(dp[i] != 0){
            return dp[i];
        }
        int minJumps =(int)1e9;

        for(int jump = 1;jump < jumps[i] && jump + i <= n;jump++){
            minJumps = Math.min(minJumps,climbStairs_minMoves_rec(i+jump,n,jumps));
        }

        return minJumps == 1e9 ? (dp[i] = minJumps) : (dp[i] = minJumps+1);
    }

    public static int climbStairs_minMoves_tab(int i,int n,int[] jumps,int[] dp){
        for(i = n;i>=0;i--){
            if(i == n){
                dp[i] = 0;
                continue;
            }
            int minJumps =(int)1e9;
            for(int jump = 1;jump <= jumps[i] && jump + i <= n;jump++){
                minJumps = Math.min(minJumps,dp[i+jump]);
            }

            if(minJumps == 1e9){
                dp[i] = (int)1e9;
            }else{
                dp[i] = minJumps + 1;
            }
        }

        return dp[0];
    }
    static int[][] dir = {{0,1},{1,0}};
    public static int minCostInMaze_Rec(int srcr,int srcc,int[][] maze){
        if(srcr == maze.length-1 && srcc == maze[0].length-1){
            return maze[srcr][srcc];
        }

        int minCost = (int)1e9;

        for(int d = 0;d<4;d++){
            int r = srcr + dir[d][0];
            int c = srcr + dir[d][0];
            if(r >=0 && r < maze.length && c >= 0 && c < maze[0].length){
                minCost = Math.min(minCost,minCostInMaze_Rec(r,c,maze));
            }
        }

        return minCost + maze[srcr][srcc];
    }

    public static int minCostInMaze_Memo(int srcr,int srcc,int[][] maze,int[][] dp){
        if(srcr == maze.length-1 && srcc == maze[0].length-1){
            return dp[srcr][srcc] = maze[srcr][srcc];
        }
        if(dp[srcr][srcc] != 0){
            return dp[srcr][srcc];
        }
        int minCost = (int)1e9;

        for(int d = 0;d<2;d++){
            int r = srcr + dir[d][0];
            int c = srcc + dir[d][1];
            if(r >=0 && r < maze.length && c >= 0 && c < maze[0].length){
                minCost = Math.min(minCost,minCostInMaze_Memo(r,c,maze,dp));
            }
        }

        return dp[srcr][srcc] = minCost + maze[srcr][srcc];
    }

    public static int minCostInMaze_tab(int srcr,int srcc,int[][] maze,int[][] dp){
        int n = maze.length;
        int m = maze[0].length;
        for(int i = n-1;i>=0;i--){
            for(int j = m-1;j>=0;j--){
                if(i == maze.length-1 && j == maze[0].length-1){
                    dp[i][j] = maze[i][j];
                    continue;
                }
                int minCost = (int)1e9;
                for(int d = 0;d<2;d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if(r >=0 && r < maze.length && c >= 0 && c < maze[0].length){
                        minCost = Math.min(minCost,dp[r][c]);
                    }
                }

                dp[i][j] = minCost + maze[i][j];
            }

        }

        return dp[0][0];
    }
    static int[][] dirGMine = {{-1,1},{0,1},{1,1}};
    public static int goldMine(int[][] mine){
        int finalAns = Integer.MIN_VALUE;
        for(int row = 0;row < mine.length;row++){
            Integer[][] dp = new Integer[mine.length][mine[0].length];
            //int ans = goldmine_rec(mine,row,0);
            int ans = goldmine_memo(mine,row,0,dp);
            finalAns = Math.max(ans,finalAns);
        }

        return finalAns;
    }
    public static int goldmine_rec(int[][] mine,int row,int col){
        if(col == mine[0].length){
            return mine[row][col];
        }
        int maxGold = Integer.MIN_VALUE;
        for(int d = 0;d < 3;d++){
            int r = row + dirGMine[d][0];
            int c = col + dirGMine[d][1];

            if(r >= 0 && r < mine.length && c >= 0 && c < mine[0].length){
                maxGold = Math.max(goldmine_rec(mine,row,col),maxGold);
            }
        }

        return maxGold + mine[row][col];
    }

    public static int goldmine_memo(int[][] mine,int row,int col,Integer[][] dp){
        if(col == mine[0].length-1){
            return dp[row][col] = mine[row][col];
        }
        if(dp[row][col] != null){
            return dp[row][col];
        }
        int maxGold = Integer.MIN_VALUE;
        for(int d = 0;d < 3;d++){
            int r = row + dirGMine[d][0];
            int c = col + dirGMine[d][1];

            if(r >= 0 && r < mine.length && c >= 0 && c < mine[0].length){
                maxGold = Math.max(goldmine_memo(mine,r,c,dp),maxGold);
            }
        }

        return dp[row][col] = maxGold + mine[row][col];
    }

    public static int goldmine_tab(int[][] mine,Integer[][] dp){
        int res = 0;
        for(int j = mine[0].length -1 ;j>=0;j--){
            for(int i = 0;i<mine.length;i++){
                if(j == mine[0].length -1){
                    dp[i][j] = mine[i][j];
                }else if(i == 0){
                    dp[i][j] = Math.max(dp[i][j+1],dp[i+1][j+1]) + mine[i][j];
                }else if(i == mine.length -1){
                    dp[i][j] = Math.max(dp[i][j+1],dp[i-1][j+1]) + mine[i][j];
                }else{
                    dp[i][j] = Math.max(dp[i][j+1],Math.max(dp[i-1][j+1],dp[i+1][j+1])) + mine[i][j];
                }
                res = Math.max(res,dp[i][j]);
            }
        }
        
        return res;

    }

    public static int goldmine_tab2(int[][] mine,Integer[][] dp){
        int res = 0;
        for(int j = mine[0].length -1 ;j>=0;j--){
            for(int i = 0;i<mine.length;i++){
                if(j == mine[0].length -1){
                    dp[i][j] = mine[i][j];
                    continue;
                }
                int maxGold = Integer.MIN_VALUE;
                for(int d = 0;d < 3;d++){
                    int r = i + dirGMine[d][0];
                    int c = j + dirGMine[d][1];

                    if(r >= 0 && r < mine.length && c >= 0 && c < mine[0].length){
                        maxGold = Math.max(goldmine_memo(mine,r,c,dp),maxGold);
                    }
                }

                dp[i][j] = maxGold + mine[i][j];
                res = Math.max(res,dp[i][j]);
            }
        }
        
        return res;

    }


    public static boolean targetSumSubset_Rec(int[] arr,int i,int target){
        if(target == 0)return true;
        if(i == arr.length) return false;
        boolean ans = false;
        if(target - arr[i] >= 0){
            ans = targetSumSubset_Rec(arr,i+1,target-arr[i]);
        }
        
        ans =  ans || targetSumSubset_Rec(arr,i+1,target);

        return ans;

    }

    public static boolean targetSumSubset_memo(int[] arr,int i,int target,Boolean[][] dp){
        if(target == 0)return dp[i][target] = true;
        if(i == arr.length) return dp[i][target] = false;
        if(dp[i][target] != null){
            return dp[i][target];
        }
        boolean ans = false;
        if(target - arr[i] >= 0){
            ans = targetSumSubset_memo(arr,i+1,target-arr[i],dp);
        }
        
        ans =  ans || targetSumSubset_memo(arr,i+1,target,dp);

        return dp[i][target] = ans;

    }


    // public static boolean targetSumSubset_tab(int[] arr,int i,int target,Boolean[][] dp){
    //     if(target == 0)return dp[i][target] = true;
    //     if(i == arr.length) return dp[i][target] = false;
    //     if(dp[i][target] != null){
    //         return dp[i][target];
    //     }
    //     boolean ans = false;
    //     if(target - arr[i] >= 0){
    //         ans = targetSumSubset(arr,i+1,target-arr[i]);
    //     }
        
    //     ans =  ans || targetSumSubset(arr,i+1,target);

    //     return dp[i][target] = ans;

    // }

    public static int coinChange_perm_rec(int[] coins,int target){

        if(target == 0){
            return 1;
        }
        int count = 0;
        for(int coin : coins){
            if(target - coin >= 0 ){
                count+=coinChange_perm_rec(coins,target-coin);
            }
        }

        return count;
    }


    public static int coinChange_perm_memo(int[] coins,int target,Integer[] dp){

        if(target == 0){
            return 1;
        }

        if(dp[target] != null){
            return dp[target];
        }
        int count = 0;
        for(int coin : coins){
            if(target - coin >= 0 ){
                count+=coinChange_perm_memo(coins,target-coin,dp);
            }
        }

        return dp[target] = count;
    }

    public static int coinChange_perm_tab(int[] coins,int target,int[] dp){
        dp[0] = 1;
        for(int i = 1;i<=target;i++){
            for(int coin : coins){
                if(i - coin >= 0){
                    dp[i] += dp[i-coin];
                }
            }
        }

        return dp[target];
    }

    public static int coinChange_comb_memo(int[] coins,int target,Integer[] dp,int startIndex){

        if(target == 0){
            return 1;
        }

        if(dp[target] != null){
            return dp[target];
        }
        int count = 0;
        for(int i = startIndex;i<coins.length;i++){
            if(target - coins[i] >= 0 ){
                count+=coinChange_comb_memo(coins,target-coins[i],dp,startIndex+1);
            }
        }

        return dp[target] = count;
    }


    public static int coinChange_comb_tab(int[] coins,int target,int[] dp){
        dp[0] = 1;
        for(int i = 0;i<coins.length;i++){
            for(int j = coins[i];j<dp.length;j++){
                dp[j] += dp[j-coins[i]];
            }
        }
        
        return dp[target];    
        
    }
    static int maxProfit = Integer.MIN_VALUE;
    public static int knapsack_01_rec(int[] arrProduct,int[] wtArray,int cap,int index){
        if(index == -1){
            return 0;
        }
        int ansYesCall = 0;
        if(cap - wtArray[index] >= 0){
            ansYesCall = knapsack_01_rec(arrProduct,wtArray,cap-wtArray[index],index-1) + arrProduct[index];
        }
        

        int ansNoCall = knapsack_01_rec(arrProduct,wtArray,cap,index-1);


        
         return Math.max(ansYesCall,ansNoCall);

    }

    public static int knapsack_01_memo(int[] arrProduct,int[] wtArray,int cap,int index,int[][] dp){
        if(index == -1){
            return 0;
        }
        if(dp[index+1][cap] != 0){
            return dp[index+1][cap];
        }
        int ansYesCall = 0;
        if(cap - wtArray[index] >= 0){
            ansYesCall = knapsack_01_memo(arrProduct,wtArray,cap-wtArray[index],index-1,dp) + arrProduct[index];
        }
        

        int ansNoCall = knapsack_01_memo(arrProduct,wtArray,cap,index-1,dp);


        
         return dp[index+1][cap] = Math.max(ansYesCall,ansNoCall);

    }


    public static int knapsack_unbounded_memo(int[] arrProduct,int[] wtArray,int cap,int index,int[][] dp){
        if(cap == 0 || index == -1){
            return 0;
        }
        if(dp[index+1][cap] != 0){
            return dp[index+1][cap];
        }
        int ansYesCall = 0;
        if(cap - wtArray[index] >= 0){
            ansYesCall = knapsack_unbounded_memo(arrProduct,wtArray,cap-wtArray[index],index,dp) + arrProduct[index];
        }
        

        int ansNoCall = knapsack_unbounded_memo(arrProduct,wtArray,cap,index-1,dp);


        
         return dp[index+1][cap] = Math.max(ansYesCall,ansNoCall);

    }


    public static int knapsack_unbounded_tab(int[] arrProduct,int[] wtArray,int cap){
        int[] dp = new int[cap+1];
        for(int i = 0;i<arrProduct.length;i++){
            for(int c = arrProduct[i];c<=cap;c++){
                dp[c] = Math.max(dp[c],dp[c-arrProduct[i]] + arrProduct[i] );
            }
        }

        return dp[cap];
    }

    public static int countBinaryString(int i,int n,char lastAddedValue){
        if(i==n){
            return 1;
        }
        if(i>n){
            return 0;
        }
        int count = 0;
        if(lastAddedValue == '1'){
            count += countBinaryString(i+1,n,'0');
            count+=countBinaryString(i+1,n,'1');
        }else{
            count+=countBinaryString(i+1,n,'1');
        }

        return count;
    }


    public static int countBinaryString_memo(int i,int n,char lastAddedValue,int[][] dp){
        if(i==n){
            return 1;
        }
        if(i>n){
            return 0;
        }

        if(lastAddedValue == '0'){
            if(dp[i][0] != 0){
                return dp[i][0];
            }
        }else{
            if(dp[i][1] != 0){
                return dp[i][1];
            }
        }
        int count = 0;
        if(lastAddedValue == '1'){
            count += countBinaryString_memo(i+1,n,'0',dp);
            count+=countBinaryString_memo(i+1,n,'1',dp);
        }else{
            count+=countBinaryString_memo(i+1,n,'1',dp);
        }

        return dp[i][lastAddedValue-'0'] = count;
    }

    public static int countBinaryString_tab(int n){
        int[][] dp = new int[n+1][3];
        for(int i = 0;i<=n;i++){
            for(int j = 0;j<=2;j++){
                if(i == 0){
                    dp[i][j] = 1;
                }
            }
        }

        for(int i = 1;i<=n;i++){
            for(int j = 0;j<2;j++){
                if( j == 1){
                    dp[i][j] = dp[i-1][0] + dp[i-1][1];
                }else{
                    dp[i][j] = dp[i-1][1];
                }
            }
        }

        return dp[n][1];
    }

    public static int countEncoding(String str,int index){
        if(index == 0){
            return 1;
        }
        if(index < 0){
            return 0;
        }
        int count = 0;
        count = count + countEncoding(str,index-1);

        int n1 = str.charAt(index) - '0';
        if(index >=1){
            int n2 = str.charAt(index-1);
            if(n2*10+n1 <= 26){
                count = count + countEncoding(str,index-2);
            }
        }

        return count;
    }
    public static void fun(){
        fib();
    }
    public static void main(String args[]){
        fun();
    }
}
