package drawbridge;

public class DiamondMine {
	public int maxMine(int[][] mat) {

		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			return 0;
		}

		int n = mat.length;
		int m = mat[0].length;
		int p = m + n - 2;

		int[][][] dp = new int[m + n][n][n];
		dp[0][0][0] = mat[0][0];

		// k = m + n - 2 the total steps when from (0,0) to (n-1, m-1)
		// dp[k][i][j] store after k steps
		// one path is at i row
		// another path is at j row
		// the maximum diamonds number

		for (int k = 1; k <= p; k++) {

			for (int i = 0; i < n; i++) {

				for (int j = i; j < n; j++) {

					if (!isValid(k, i, j, mat)) {
						continue;
					}

					int iUpjUp = getValue(k - 1, i - 1, j - 1, dp, mat);
					int iUpjStay = getValue(k - 1, i - 1, j, dp, mat);
					int iStayjUp = getValue(k - 1, i, j - 1, dp, mat);
					int iStayjStay = getValue(k - 1, i, j, dp, mat);

					if (i == j) {

						dp[k][i][j] = Math.max(iUpjUp, Math.max(iUpjStay, Math.max(iStayjUp, iStayjStay)));
						dp[k][i][j] += mat[i][k - i];

					} else {

						dp[k][i][j] = Math.max(iUpjUp, Math.max(iUpjStay, Math.max(iStayjUp, iStayjStay)));
						dp[k][i][j] += mat[i][k - i] + mat[j][k - j];

					}

				}
			}

		}

		return dp[p][m - 1][m - 1];

	}

	public int getValue(int step, int i, int j, int[][][] dp, int[][] mat) {

		if (!isValid(step, i, j, mat)) {
			return 0;
		}

		return dp[step][i][j];

	}

	public boolean isValid(int step, int rowi, int rowj, int[][] mat) {

		int n = mat.length;
		int m = mat[0].length;
		int coli = step - rowi;
		int colj = step - rowj;

		boolean res = (rowi >= 0) && (rowi < n) && (rowj >= 0) && (rowj < n) && (coli >= 0) && (coli < m) && (colj >= 0)
				&& (colj < m) && (mat[rowi][coli] != -1) && (mat[rowj][colj] != -1);

		return res;

	}

	public static void main(String[] args) {
		int[][] mat1 = { { 0, 1, -1 }, { 1, 0, -1 }, { 1, 1, 1 } };
		int[][] mat2 = { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		int[][] mat3 = { { 0, 1, 1 }, { 1, 0, -1 }, { 1, 1, -1 } };

		DiamondMine sol = new DiamondMine();
		System.out.println("Number for mat1: " + sol.maxMine(mat1));
		System.out.println("Number for mat2: " + sol.maxMine(mat2));
		System.out.println("Number for mat3: " + sol.maxMine(mat3));

	}
}
