package d714;

public class DecodeWays2 {
	public static int numDecodings(String s) {
        if (s.length() == 0) {
			return 0;
		}
		long[] dp = new long[s.length() + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
		int len = s.length();
		for (int i = 2; i <= len; i++) {
			char c = s.charAt(i - 1);
			if (c == '*') {
				dp[i] = dp[i - 1] * 9;
				if (s.charAt(i - 2) == '1') {
					dp[i] += dp[i - 2] * 9;
				}
				if (s.charAt(i - 2) == '2') {
					dp[i] += dp[i - 2] * 6;
				}
				if (s.charAt(i - 2) == '*') {
					dp[i] += dp[i - 2] * 15;
				}
			} else {
				if (s.charAt(i - 1) != '0') {
					dp[i] = dp[i - 1];
				}
				if (s.charAt(i - 2) == '*') {
					if (c > '6') {
						dp[i] += dp[i - 2];
					} else {
						dp[i] += dp[i - 2] * 2;
					}
				} else {
					if (c > '6') {
						if (s.charAt(i - 2) == '1') {
							dp[i] += dp[i - 2];
						}
					} else {
						if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2') {
							dp[i] += dp[i - 2];
						}
					}
				}
			}
            dp[i] %= 1000000007;
		}
		return (int)dp[len];
    }
	
	static int M = 1000000007;
	public static int numDecodings2(String s) {
		long[] dp = new long[s.length() + 1];
		dp[0] = 1;
		dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == '*') {
				dp[i + 1] = 9 * dp[i];
				if (s.charAt(i - 1) == '1')
					dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
				else if (s.charAt(i - 1) == '2')
					dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
				else if (s.charAt(i - 1) == '*')
					dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
			} else {
				dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
				if (s.charAt(i - 1) == '1')
					dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
				else if (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')
					dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
				else if (s.charAt(i - 1) == '*')
					dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
			}
		}
		return (int) dp[s.length()];
	}

	public static void main(String[] args) {
		// System.out.println(numDecodings("*8"));
		// System.out.println(numDecodings("2*"));
		System.out.println(numDecodings2("*1*1*0"));
	}
}
