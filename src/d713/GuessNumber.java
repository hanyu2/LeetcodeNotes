package d713;

public class GuessNumber {
	//区间
	public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return search(1, n, dp);
    }
    public int search(int start, int end, int[][] dp){
        if(start >= end){
            return 0;
        }
        if(dp[start][end] != 0){
            return dp[start][end];
        }
        int min = Integer.MAX_VALUE;
        for(int i = start; i <= end; i++){
            int k = i + Math.max(search(start, i - 1, dp), search(i + 1, end, dp));
            min = Math.min(k, min);
        }
        dp[start][end] = min;
        return min;
    }
}
