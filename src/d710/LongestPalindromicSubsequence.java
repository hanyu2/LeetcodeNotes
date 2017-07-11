package d710;

public class LongestPalindromicSubsequence {
	public static int longestPalindromeSubseq(String s) {
		int len = s.length();
		int[][] dp = new int[len][len];
		for (int i = 0; i < len; i++) {
			dp[i][i] = 1;
		}
		for (int i = len - 2; i >= 0; i--) {
			for (int j = i + 1; j < len; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					if (i + 1 > j - 1) {
						dp[i][j] = 2;
					} else {
						dp[i][j] = dp[i + 1][j - 1] + 2;
					}
				} else {
					dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
				}
			}

		}
		return dp[0][len - 1];
	}

	public static void main(String[] args) {
		System.out.println(longestPalindromeSubseq("bbbab"));
		System.out.println(longestPalindromeSubseq("cbbd"));
		System.out.println(longestPalindromeSubseq("aaa"));
		System.out.println(longestPalindromeSubseq("abac"));
		System.out.println(longestPalindromeSubseq("aabaaba"));
	}
}
