package d713;

import java.util.Arrays;

public class OutOfBoundaryPaths {
	// 一开始想的是到这一步，把所有小于这一步的可能性全加上
	// 实际上会有重复计算，到这一步还剩7，把1-6全加上，当在同样位置剩3时又加一遍
	// space O(m * n * N)
	int M = 1000000007;

	public int findPaths(int m, int n, int N, int i, int j) {
		int[][][] memo = new int[m][n][N + 1];
		for (int[][] l : memo)
			for (int[] sl : l)
				Arrays.fill(sl, -1);
		return findPaths(m, n, N, i, j, memo);
	}

	public int findPaths(int m, int n, int N, int i, int j, int[][][] memo) {
		if (i == m || j == n || i < 0 || j < 0)
			return 1;
		if (N == 0)
			return 0;
		if (memo[i][j][N] >= 0)
			return memo[i][j][N];
		memo[i][j][N] = ((findPaths(m, n, N - 1, i - 1, j, memo) + findPaths(m, n, N - 1, i + 1, j, memo)) % M
				+ (findPaths(m, n, N - 1, i, j - 1, memo) + findPaths(m, n, N - 1, i, j + 1, memo)) % M) % M;
		return memo[i][j][N];
	}
	
	//O(mnN)
	/*int findPaths2(int m, int n, int N, int i, int j) {
		  int dp[51][50][50] = {};
		  for (auto Ni = 1; Ni <= N; ++Ni)
		    for (auto mi = 0; mi < m; ++mi)
		      for (auto ni = 0; ni < n; ++ni)
		        dp[Ni][mi][ni] = ((mi == 0 ? 1 : dp[Ni - 1][mi - 1][ni]) + (mi == m - 1? 1 : dp[Ni - 1][mi + 1][ni])
		            + (ni == 0 ? 1 : dp[Ni - 1][mi][ni - 1]) + (ni == n - 1 ? 1 : dp[Ni - 1][mi][ni + 1])) % 1000000007;
		  return dp[N][i][j];
		}*/
	//The number of paths for N moves is the sum of paths for N - 1 moves from the adjacent cells.
	//If an adjacent cell is out of the border, the number of paths is 1.
	//O(mn)
	public int findPaths3(int m, int n, int N, int i, int j) {
		int M = 1000000000 + 7;
		int dp[][] = new int[m][n];
		dp[i][j] = 1;
		int count = 0;
		for (int moves = 1; moves <= N; moves++) {
			int[][] temp = new int[m][n];
			for (int x = 0; x < m; x++) {
				for (int y = 0; y < n; y++) {
					if (x == m - 1)
						count = (count + dp[x][y]) % M;
					if (y == n - 1)
						count = (count + dp[x][y]) % M;
					if (x == 0)
						count = (count + dp[x][y]) % M;
					if (y == 0)
						count = (count + dp[x][y]) % M;
					temp[x][y] = (((x > 0 ? dp[x - 1][y] : 0) + (x < m - 1 ? dp[x + 1][y] : 0)) % M
							+ ((y > 0 ? dp[x][y - 1] : 0) + (y < n - 1 ? dp[x][y + 1] : 0)) % M) % M;
				}
			}
			dp = temp;
		}
		return count;
	}

	public static void main(String[] args) {
		OutOfBoundaryPaths oobp = new OutOfBoundaryPaths();
		System.out.println(oobp.findPaths3(2, 2, 2, 0, 0));
	}
}
