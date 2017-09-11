package d823;

public class CoinChange2 {
	//一开始想用二维dp数组， 其实一维就可以
	//1.i-j忘记判断是否小于0
	//2.有重复， 比如4，121， 112
	//关键在于如何去重
	//开始想每一个数值 存一个嵌套数组，存每个数值所有格可能性组合集合
	//空间复杂度太大，不易判重
	//https://discuss.leetcode.com/topic/79071/knapsack-problem-java-solution-with-thinking-process-o-nm-time-and-o-m-space
	/*
	 * 1.not using the ith coin, only using the first i-1 coins to make up
	 * amount j, then we have dp[i-1][j] ways. 
	 * 2.using the ith coin, since we
	 * can use unlimited same coin, we need to know how many way to make up
	 * amount j - coins[i] by using first i coins(including ith), which is
	 * dp[i][j-coins[i]]
	 */

	public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= coins.length; i++){
            dp[i][0] = 1;//!!!
            for(int j = 1; j <= amount; j++){
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

}
