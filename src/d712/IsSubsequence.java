package d712;

public class IsSubsequence {
	//01背包
	public static boolean isSubsequence(String s, String t) {
        if(t.length() < s.length()){
            return false;
        }
        int m = s.length();
        int n = t.length();
        boolean[] dp = new boolean[n + 1];
        for(int i = 0; i <= n; i++){
        	dp[i] = true;
        }
        for(int i = 1; i <= m; i++){
        	boolean[] temp = new boolean[n + 1];
        	for(int j = 1; j <= n; j++){
        		temp[j] = temp[j - 1];
        		if(s.charAt(i - 1) == t.charAt(j - 1)){
        			temp[j] |= dp[j - 1];
        		}
        	}
        	dp = temp;
        }
        return dp[n];
	}
	
	//two pointers
	 public boolean isSubsequence2(String s, String t) {
	        if (s.length() == 0) return true;
	        int indexS = 0, indexT = 0;
	        while (indexT < t.length()) {
	            if (t.charAt(indexT) == s.charAt(indexS)) {
	                indexS++;
	                if (indexS == s.length()) return true;
	            }
	            indexT++;
	        }
	        return false;
	    }
	public static void main(String[] args) {
		System.out.println(isSubsequence("axc", "ahbgdc"));
		System.out.println(isSubsequence("abc", "ahbgdc"));
	}
}
