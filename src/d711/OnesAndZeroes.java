package d711;

import java.util.Arrays;
import java.util.Comparator;

public class OnesAndZeroes {
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
    public int[] countZeroOne(String s){
        int[] res = new int[2];
        for(char c : s.toCharArray()){
            res[c - '0']++;
        }
        return res;
    }
}
