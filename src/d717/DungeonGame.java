package d717;

import java.util.Arrays;

public class DungeonGame {
	//从右下角网右上角搜，当某元素<=0， 设置为1，取每个位置最小的血
	//非常慢
	public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        if(m == 0){
            return 0;
        }
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for(int[] arr : dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        search(m - 1, n - 1, dungeon, dp, 1);
        return dp[0][0];
    }
    public static void search(int i, int j, int[][] dungeon, int[][] dp, int b){
        if(i < 0 || j < 0 ||  b - dungeon[i][j] >= dp[i][j]){//一开始写的b>=,还没有对本元素操作
            return;
        }
        int blood = b - dungeon[i][j];
        if(blood <= 0){
            dp[i][j] = 1;
            blood = 1;//一开始忘了加这句会导致宣传下去的blood不为1
        }else if(blood < dp[i][j]){//一开始写成if
            dp[i][j] = blood;
        }
        search(i - 1, j, dungeon, dp, blood);
        search(i, j - 1, dungeon, dp, blood);
    }
    //用数组遍历，不搜索
    public int calculateMinimumHP2(int[][] dungeon) {
        int m = dungeon.length;
        if(m == 0){
            return 0;
        }
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);//开始忘了初始化，[[0]] failed
        for(int i = m - 2; i >= 0; i--){
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1); 
        }
        for(int j = n - 2; j >= 0; j--){
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                dp[i][j] = Math.max(Math.min(dp[i + 1][j] - dungeon[i][j], dp[i][j + 1] - dungeon[i][j]), 1);
            }
        }
        return dp[0][0];
    }
    
    public static void main(String[] args) {
    	int[][] dungeon = {{3,0,-3},{-3,-2,-2},{3,1,-3}};
		System.out.println(calculateMinimumHP(dungeon));
	}
}
