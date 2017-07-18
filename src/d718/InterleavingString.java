package d718;

import java.util.HashMap;
import java.util.Map;

public class InterleavingString {
	//开始想最简单搜索
	//注意边界
	//cache用map存储中间结果
	//2D DP 
	//https://leetcode.com/articles/interleaving-strings/#approach-3-using-2-d-dynamic-programming-accepted
	public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
        	return false;
        }
        Map<String, Boolean> map = new HashMap<String, Boolean>(); 
        return search(s1, 0, s2, 0, s3, 0, map);
    }

	private boolean search(String s1, int p1, String s2, int p2, String s3, int p3, Map<String, Boolean> map) {
        if(p1 > s1.length() || p2 > s2.length()){
            return false;
        }
		if(p3 == s3.length()){
			return true;
		}
		String s = p1 + "#" + p2;
		if(map.containsKey(s)){
			return map.get(s);
		}
		if(p1 < s1.length() && s1.charAt(p1) == s3.charAt(p3)){
			if(search(s1, p1+1, s2, p2, s3, p3+1, map)){
				map.put(s, true);
				return true;
			}
		}
		if(p2 < s2.length() && s2.charAt(p2) == s3.charAt(p3)){
			if(search(s1, p1, s2, p2+1, s3, p3+1, map)){
				map.put(s, true);
				return true;
			}
		}
		map.put(s, false);
		return false;
	}
}
