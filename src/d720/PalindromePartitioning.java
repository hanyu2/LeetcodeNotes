package d720;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(s.length() == 0){
            return res;
        }
        search(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    public void search(String s, int index, List<String> list, List<List<String>> res){
        if(index == s.length()){
            res.add(list);
            return;
        }
        for(int i = index + 1; i <= s.length(); i++){
            String sub = s.substring(index, i);
            if(isPalin(sub)){
                List<String> temp = new ArrayList<String>(list);
                temp.add(sub);
                search(s, i, temp, res);
            }
        }
    }
    
    public boolean isPalin(String s){
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    //dp
    public List<List<String>> partition2(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<String>(), dp, s, 0);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if(pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for(int i = pos; i < s.length(); i++) {
            if(dp[pos][i]) {
                path.add(s.substring(pos,i+1));
                helper(res, path, dp, s, i+1);
                path.remove(path.size()-1);
            }
        }
    }
    
    
}
