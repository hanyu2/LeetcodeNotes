package d708;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		int lL = 0;
		int rL = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				lL++;
			}
			if (c == ')') {
				if (lL == 0) {
					rL++;
				} else {
					lL--;
				}
			}

		}
		Set<String> res = new HashSet<String>();
		search(0, s, lL, rL, res, 0);
		return new ArrayList<String>(res);
	}

	public void search(int index, String s, int left, int right, Set<String> res, int open) {
		// 没检查left, right, open小于0的情况
		if (index >= s.length()) {
			if (left == 0 && right == 0 && open == 0) {
				if (check(s)) {
					res.add(s);
				}
			}
			return;
		}
		if (s.charAt(index) == ')') {
			// 不要cursor和字符串都在变化， 构造新字符串
			search(index, s.substring(0, index) + s.substring(index + 1), left, right - 1, res, open + 1);
			search(index + 1, s, left, right, res, open - 1);
		} else if (s.charAt(index) == '(') {
			search(index, s.substring(0, index) + s.substring(index + 1), left - 1, right, res, open - 1);
			search(index + 1, s, left, right, res, open + 1);
		}
	}

	public boolean check(String s) {
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				count++;
			}
			if (c == ')') {
				count--;
			}
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}
	//BFS
	//用set缩小搜索范围
	//考虑字符不为( )的情况
	public static List<String> removeInvalidParentheses2(String s) {
		Queue<String> q = new LinkedList<String>();
		List<String> res = new LinkedList<String>();
		Set<String> set = new HashSet<String>();
		q.offer(s);
		set.add(s);
		while (!q.isEmpty()) {
			int size = q.size();
			boolean found = false;
			for (int i = 0; i < size; i++) {
				String str = q.poll();
				if (isValid(str)) {
					found = true;
					res.add(str);
				}
				if (found)
					continue;
				for (int j = 0; j < str.length(); j++) {
					if (str.charAt(j) != '(' && str.charAt(j) != ')')
						continue;
					String temp = str.substring(0, j) + str.substring(j + 1);
					if (!set.contains(temp)) {
						q.offer(temp);
						set.add(temp);
					}
				}
			}
			//在这一层一开始没发现的被截取后会被继续计算
			if(found){
				break;
			}
		}
		return res;
	}

	public static boolean isValid(String s) {
		int left = 0;
		int right = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				left++;
			} else if (c == ')') {
				right++;
			}
			if (right > left) {
				return false;
			}
		}
		return left == right;
	}

	public static void main(String[] args) {
		List<String> res = removeInvalidParentheses2("()())()");
	}
}
