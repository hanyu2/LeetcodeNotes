package d717;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak2 {
	public List<String> wordBreak(String s, List<String> wordDict) {
		if (s.length() == 0 || wordDict.size() == 0) {
			return  new ArrayList<String>();
		}
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		return search(0, s, map, wordDict);
	}

	private List<String> search(int index, String s, Map<Integer, List<String>> map, List<String> wordDict) {
		if(index == s.length()){
			List<String> temp = new ArrayList<String>();
			temp.add("");
			map.put(index, temp);
			return temp;
		}
		if(map.containsKey(index)){
			return map.get(index);
		}
		String str = s.substring(index);
		List<String> res = new ArrayList<String>();
		for(String word : wordDict){
			if(str.startsWith(word)){
				List<String> last = search(index + word.length(), s, map, wordDict);
				for(String la : last){
					String newString = word + " " + la;
					res.add(newString.trim());
				}
			}
		}
		map.put(index, res);
		return res;
	}
}
