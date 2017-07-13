package d712;

public class IntegerBreak {
	public static int integerBreak(int n) {
		int[] dp = new int[n + 1];
		if (n == 2) {
			return 1;
		}
		dp[2] = 2;
		if (n == 3) {
			return 2;
		}
		dp[3] = 3;
		for (int i = 4; i <= n; i++) {
			int max = 0;
			for (int j = 2; j <= i / 2; j++) {
				int k = j * dp[i - j];
				max = Math.max(max, k);
			}
			dp[i] = max;
		}
		return dp[n];
	}

	public int integerBreak2(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; 2 * j <= i; j++) {
				dp[i] = Math.max(dp[i], (Math.max(j, dp[j])) * (Math.max(i - j, dp[i - j])));
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(integerBreak(10));
	}
}
