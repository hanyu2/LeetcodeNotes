package d711;

import java.util.Arrays;
import java.util.Comparator;

public class OnesAndZeroes {
	//排序使得程序更早退出
	//搜索，有m, n两个变量， 需要二维数组cache
	//当达到某个m, n如何知道那些字符串已用， 哪些没用
	int max = 0;
    public int findMaxForm(String[] strs, int m, int n) {
        boolean[] used = new boolean[strs.length];
        Arrays.sort(strs, new Comparator<String>(){
			public int compare(String s1, String s2) {
				return s2.length() - s1.length();
			}
        });
        search(strs, m, n, 0, used);
        return max;
    }
    
    public void search(String[] strs , int m, int n, int count, boolean[] used){
    	if(m < 0 || n < 0){
    		return;
    	}
        if(m >= 0 && n >= 0){
            max = Math.max(count, max);
        }
        for(int i = 0; i < strs.length; i++){
            if(!used[i]){
                int[] zerone = countZeroOne(strs[i]);
                int zeroLeft = m - zerone[0];
                int oneLeft = n - zerone[1];
                if(zeroLeft >= 0 && oneLeft >= 0){
                    used[i] = true;
                    search(strs, zeroLeft, oneLeft, count + 1, used);
                    used[i] = false;
                }
            }
        }
    }
    //01 pack
    //https://discuss.leetcode.com/topic/76103/0-1-knapsack-detailed-explanation
    public int findMaxForm2(String[] strs, int m, int n) {
    	int[][] dp = new int[m + 1][n + 1];
    	for(String s : strs){
    		int[] count = countZeroOne(s);
    		for(int i = m; i >= count[0]; i--){
    			for(int j = n; j >= count[1]; j--){
    				dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
    			}
    		}
    	}
    	return dp[m][n];
    }
    
    public int[] countZeroOne(String s){
        int[] res = new int[2];
        for(char c : s.toCharArray()){
            res[c - '0']++;
        }
        return res;
    }
}
