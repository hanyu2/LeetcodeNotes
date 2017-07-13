package d713;

public class CoinChange {
	//注意当本值减去coin值的值不存在的情况
	//do[j]初始为负当有结果仍然为负，注意初始值
	public static int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}
		int m = coins.length;
		int n = amount;
		int[] dp = new int[n + 1];
		dp[0] = 0;
		for (int j = 1; j <= n; j++) {
			dp[j] = Integer.MAX_VALUE;
			for (int i = 1; i <= m; i++) {
				if (j >= coins[i - 1]) {
					if(dp[j - coins[i - 1]] != Integer.MAX_VALUE){
						dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
					}
				}
			}
		}
		return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
	}
	public static void main(String[] args) {
		int[] coins = {1};
		System.out.println(coinChange(coins, 1));
	}
}
