package d717;

public class PalindromePartitioning2 {
	public static int minCut(String s) {
		if(s.length() <= 1){
        	return 0;
        }
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] isPal = new boolean[n][n];

        for(int i = n - 1; i >= 0; i--){
        	dp[i] = n - i - 1;
        	for(int j = i + 1; j < n; j++){
        		if(s.charAt(i) == s.charAt(j)){
        			if(j - i < 2 || isPal[i + 1][j - 1]){
        				isPal[i][j] = true;
        				//注意j越界
                        if(j == n - 1){
                            dp[i] = 0;
                        }else{
                            dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                        }
        			}
        		}
        	}
        }
        return dp[0];
    }

	public static void main(String[] args) {
		//System.out.println(minCut("abbaba"));
		System.out.println(minCut("cdd"));
	}
}
