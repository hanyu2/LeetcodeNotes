package d717;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
	public static List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<String>();
		if (words.length <= 1) {
			return res;
		}
		Set<String> set = new HashSet<String>(Arrays.asList(words));
		for (String word : words) {
			if (word.length() == 0) {
				continue;
			}
			set.remove(word);
			if (search(word, set)) {
				res.add(word);
			}
			set.add(word);
		}
		return res;
	}

	public static boolean search(String word, Set<String> set) {
		if (word.length() == 0) {
			return true;
		}
		if (set.contains(word)) {
			return true;
		}
		for (int i = 1; i <= word.length(); i++) {
			String prefix = word.substring(0, i);
			if (set.contains(prefix)) {
				if (search(word.substring(i), set)) {
					set.add(word);
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String[] words = { "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" };
		findAllConcatenatedWordsInADict(words);
	}
}
