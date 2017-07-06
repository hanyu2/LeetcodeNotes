package d706;

import java.util.List;

public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for(int i = 1; i <= s.length(); i++){
            if(wordDict.contains(s.substring(0, i))){
            	dp[i - 1] = true;
            }
        }
        for(int i = 0; i < s.length(); i++){
        	if(dp[i]){
	        	for(int j = i + 1; j <= s.length(); j++){
	        		if(wordDict.contains(s.substring(i + 1, j))){
	        			dp[j - 1] = true;
	        		}
	        	}
        	}
        }
        return dp[s.length() - 1];
    }
}
