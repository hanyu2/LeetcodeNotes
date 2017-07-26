package d725;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDirectory {
	//pre, next不能一样
	//set不能直接add，要判断元素有没有，否则会重复计算加入元素的indegree
	public static String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();
		int[] indegree = new int[26];
		StringBuilder sb = new StringBuilder();
		Queue<Character> q = new LinkedList<Character>();
		for (String word : words) {
			for (int i = 0; i < word.length() - 1; i++) {
				char pre = word.charAt(i);
				char next = word.charAt(i + 1);
				if (pre == next) {
					continue;
				}
				Set<Character> set;
				if (map.containsKey(pre)) {
					set = map.get(pre);
				} else {
					set = new HashSet<Character>();
				}
				if (!set.contains(next)) {
					set.add(next);
					indegree[next - 'a']++;
					map.put(pre, set);
				}
			}
		}
		for (char c : map.keySet()) {
			if (indegree[c - 'a'] == 0) {
				q.offer(c);
			}
		}
		while (!q.isEmpty()) {
			char c = q.poll();
			sb.append(c);
			if (map.containsKey(c)) {//有的元素可能没有out degree
				for (char next : map.get(c)) {
					indegree[next - 'a']--;
					if (indegree[next - 'a'] == 0) {
						q.offer(next);
					}
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			if (indegree[i] != 0) {
				return "";
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
		System.out.println(alienOrder(words));
	}
}
