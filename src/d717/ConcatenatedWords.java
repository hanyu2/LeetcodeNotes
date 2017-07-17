package d717;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
	public static List<String> findAllConcatenatedWords2InADict(String[] words) {
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet(Arrays.asList(words));

		for (String word : words) {
			set.remove(word);
			if (dfs(word, set, ""))
				list.add(word);
			set.add(word);
		}
		return list;
	}

	private static boolean dfs(String word, Set<String> set, String previous) {
		if (!previous.equals(""))
			set.add(previous);
		if (set.contains(word))
			return true;

		for (int i = 1; i < word.length(); i++) {
			String prefix = word.substring(0, i);
			if (set.contains(prefix) && dfs(word.substring(i, word.length()), set, previous + prefix)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String[] words = { "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" };
		findAllConcatenatedWords2InADict(words);
	}
}
