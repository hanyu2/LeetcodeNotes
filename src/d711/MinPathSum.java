package d711;

public class MinPathSum {
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 1; i < n; i++){
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        for(int j = 1; j < m; j++){
            grid[j][0] = grid[j][0] + grid[j - 1][0];
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }
}
