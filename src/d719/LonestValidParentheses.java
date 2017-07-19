package d719;

import java.util.Stack;

public class LonestValidParentheses {
	// 一开始想用stack，()(()不好处理
	public int longestValidParentheses(String s) {
		if (s.length() <= 1) {
			return 0;
		}
		Stack<Character> stack = new Stack<Character>();
		int max = 0;
		int len = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (stack.isEmpty()) {
				if (c == '(') {
					stack.push(c);
				} else if (c == ')') {
					len = 0;
				}
			} else {
				if (c == '(') {
					stack.push(c);
				}
				if (c == ')') {
					if (stack.peek() == '(') {
						stack.pop();
						len += 2;
						max = Math.max(max, len);
					} else {
						stack.push(c);
					}
				}
			}
		}
		max = Math.max(max, len);
		return max;
	}

	// 二维数组
	// ()() ()(()无法解决
	public static int longestValidParentheses2(String s) {
		if (s.length() <= 1) {
			return 0;
		}
		int n = s.length();
		int[][] dp = new int[n + 1][n + 1];
		for (int i = n - 1; i > 0; i--) {
			for (int j = i + 1; j <= n; j++) {
				if (s.charAt(j) == ')') {
					if (s.charAt(i - 1) == '(') {
						dp[i][j] = dp[i][j - 2] + 2;
					}
				} else {

				}
			}
		}
		return dp[0][n - 1];
	}
	//只push左括号的index
	public int longestValidParentheses3(String s) {
		if (s.length() <= 1) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int left = -1;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					left = i;
				} else {
					stack.pop();
					if (stack.isEmpty()) {
						max = Math.max(max, i - left);
					} else {
						max = Math.max(max, i - stack.peek());
					}
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestValidParentheses2("()()"));
		System.out.println(longestValidParentheses2("()(()"));
		System.out.println(longestValidParentheses2(")()())"));
	}
}
