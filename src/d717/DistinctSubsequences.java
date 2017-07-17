package d717;

public class DistinctSubsequences {
	public static int numDistinct(String s, String t) {
		int n = s.length();
		int m = t.length();
		if(m > n){
			return 0;
		}
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i <= n; i++){//初始化 一开始只初始化了dp[0][0] failed on "ccc" "c"
            dp[0][i] = 1;
        }
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				if(s.charAt(j - 1) == t.charAt(i - 1)){
					dp[i][j] = dp[i - 1][j - 1] +  dp[i][j - 1];
				}else{
					dp[i][j]  = dp[i][j - 1];
				}
			}
		}
		return dp[m][n];
	}
	public static void main(String[] args) {
		System.out.println(numDistinct("rabbbit", "rabbit"));
	}
}
