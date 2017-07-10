package d709;

public class NumArray {
	//首先dfs，只要记录前一个和前两个的颜色
	//当n==0，count++做完要加return
	static int count = 0;
    public static int numWays(int n, int k) {
        // Write your code here
        if(n == 1){
            return k;
        }
        if(n == 2){
            return k * k;
        }
        for(int i = 1; i <= k; i++){
            for(int j = 1; j <= k; j++){
                search(i, j, n - 2, k);
            }
        }
        return count;
    }
    
    public static void search(int first, int second, int n, int k){
        if(n == 0){
            count++;
        }        
        for(int i = 1; i <= k; i++){
            if(i == first && i == second){
                continue;
            }
            search(second, i, n - 1, k);
        }
    }
    
  //State: f[i][j] is total number of ways we can paint the fence;
    //Function: f[i][0] = f[i - 1][0] * k - 1 + f[i - 1][1] * (k - 1)
    //          f[i][1] = f[i - 1][0] + f[i - 1][1]
    //Initialize: f[0][0] = k, f[0][1] = k; f[1][0] = k * (k - 1), f[1][1] = k;
    //Result: f[n - 1][0] + f[n - 1][1];
    public int numWays2(int n, int k) {
        //f[i][0]表示跟前面不一样颜色，f[i][1]表示跟前面一样颜色
        int[][] dp = new int[n][2];
        if(n == 1){
            return k;
        }
        if(n == 2){
            return k * k;
        }
        dp[1][0] = (k - 1) * k;
        dp[1][1] = k;
        for(int i = 2; i < n; i++){
        	////跟前面不一样颜色的话，在这轮有k - 1种可能性
            dp[i][0] = dp[i - 1][0] * (k - 1) + dp[i - 1][1] * (k - 1);
            //跟前面一样颜色的话，在这轮有1种可能性，且前一轮不能与前前一轮一样颜色
            dp[i][1] = dp[i - 1][0] ;
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }
    //因为这个dp的解法里，我们只用到变量i - 1和i，所以我们可以进定步把空间复杂度降为o(1):
    public int numWays3(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        int diff = k * (k - 1);
        int same = k;
        for (int i = 2; i < n; i++) {
            int tmp = diff;
            diff = (diff + same) * (k - 1);
            same = tmp;
        }
        return same + diff;
    }
    
    public static void main(String[] args) {
		System.out.println(numWays(3, 2));
	}
}
