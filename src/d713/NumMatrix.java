package d713;

public class NumMatrix {
	int[][] sum;

	public NumMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		sum = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			int rowSum = 0;
			for (int j = 1; j <= n; j++) {
				sum[i][j] = sum[i - 1][j] + rowSum + matrix[i - 1][j - 1];
				rowSum += matrix[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
	}

	public static void main(String[] args) {
		int[][] matrix = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 },
				{ 1, 0, 3, 0, 5 } };
		NumMatrix nm = new NumMatrix(matrix);
		System.out.println(nm.sumRegion(1, 1, 2, 2));
		System.out.println(nm.sumRegion(2, 1, 4, 3));
	}
}
