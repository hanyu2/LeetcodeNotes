package d706;

public class LongestIncreasingPath {
	int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int len = search(matrix, i, j, m, n, dp);
                dp[i][j] = len;
                max = Math.max(max, len);
            }
        }
        return max;
    }
    public int search(int[][] matrix, int i, int j, int m, int n, int[][] dp){
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        int max = 0;
        for(int[] dir : directions){
            int x = dir[0] + i;
            int y = dir[1] + j;
            if(x >= 0 && x < m && y >= 0 && y < n){
                if(matrix[x][y] > matrix[i][j]){
                    int len = search(matrix, x, y, m, n, dp);
                    max = Math.max(max, len);
                }
            }
        }
        dp[i][j] = max + 1;
        return dp[i][j];
    }
}
