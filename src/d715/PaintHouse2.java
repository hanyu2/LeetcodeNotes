package d715;

public class PaintHouse2 {
	// 类似paint house 1
	// 每一轮记录两个上一轮最小的值

	public static int minCostII(int[][] costs) {
		// Write your code here
		int m = costs.length;
		if (m == 0) {
			return 0;
		}
		int n = costs[0].length;
		int[][] dp = new int[m][n];
		for (int i = 0; i < n; i++) {
			dp[0][i] = costs[0][i];
		}
		for (int i = 1; i < m; i++) {
			int min1 = Integer.MAX_VALUE;
			int min2 = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (dp[i - 1][j] < min1) {
					min2 = min1;
					min1 = dp[i - 1][j];
				} else if (dp[i - 1][j] < min2) {
					min2 = dp[i - 1][j];
				}
			}
			for (int j = 0; j < n; j++) {
				if (dp[i - 1][j] == min1) {
					dp[i][j] = costs[i][j] + min2;
				} else {
					dp[i][j] = costs[i][j] + min1;
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(dp[m - 1][i], min);
		}
		return min;
	}

	public int minCostII2(int[][] costs) {
		if (costs == null || costs.length == 0)
			return 0;

		int n = costs.length, k = costs[0].length;
		// min1 is the index of the 1st-smallest cost till previous house
		// min2 is the index of the 2nd-smallest cost till previous house
		int min1 = -1, min2 = -1;
		for (int i = 0; i < n; i++) {
			int last1 = min1, last2 = min2;
			min1 = -1;
			min2 = -1;
			for (int j = 0; j < k; j++) {
				if (j != last1) {
					// current color j is different to last min1
					costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
				} else {
					costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
				}
				if (min1 < 0 || costs[i][j] < costs[i][min1]) {
					min2 = min1;
					min1 = j;
				} else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
					min2 = j;
				}
			}
		}
		return costs[n - 1][min1];
	}

	public static void main(String[] args) {
		int[][] costs = { { 14, 2, 11 }, { 11, 14, 5 }, { 14, 3, 10 } };
		System.out.println(minCostII(costs));
	}
}
