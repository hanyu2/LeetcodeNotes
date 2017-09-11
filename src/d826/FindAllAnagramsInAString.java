package d826;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if(p.length() > s.length()){
            return res;
        }
        int len = p.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : p.toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c, 1);
            }
        }
        int right = 0;
        int left = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(map.containsKey(c)){
                if(map.get(c) > 0){
                    map.put(c, map.get(c) - 1);
                    right++;
                    len--;
                    if(len == 0){
                        res.add(left);
                    }
                }else{
                    while(map.get(c) == 0){
                        char l = s.charAt(left);
                        if(map.containsKey(l)){
                            map.put(l, map.get(l) + 1);
                            len++;
                            left++;
                        }
                    }
                }
            }else{
                while(left < right){
                    char l = s.charAt(left);
                    if(map.containsKey(l)){
                        len++;
                        map.put(l, map.get(l) + 1);
                    }
                    left++;
                }
                left++;
                right++;
            }
        }
        return res;
    }
	public static void main(String[] args) {
		FindAllAnagramsInAString faais = new FindAllAnagramsInAString();
		System.out.println(faais.findAnagrams("cbaebabacd", "abc").toString());
	}
}
