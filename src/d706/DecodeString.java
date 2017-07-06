package d706;

import java.util.Stack;

public class DecodeString {
	public static String decodeString(String s) {
		if (s.length() <= 1) {
			return s;
		}
		Stack<String> stack = new Stack<String>();
		for (char c : s.toCharArray()) {
			if (c != ']') {
				stack.push(String.valueOf(c));
			} else {
				StringBuilder sb = new StringBuilder();
				while (!stack.peek().equals("[")) {
					sb.insert(0, stack.pop());//注意反向字符串构造不要append
				}
				stack.pop();
				int n = 1;//注意反向数字的计算
				int num = 0;
				while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
					num = num + Integer.parseInt(stack.pop()) * n;
					n *= 10;
				}
				StringBuilder temp = new StringBuilder();
				for (int i = 0; i < num; i++) {
					temp.append(sb);
				}
				while (!stack.isEmpty() && !stack.peek().equals("[")) {
					temp.insert(0, stack.pop());
				}
				stack.push(temp.toString());
			}
		}
		//一开始直接返回stack pop，当后面有更多单个字符报错
		StringBuilder res = new StringBuilder();
		while(!stack.isEmpty()){
			res.insert(0, stack.pop());
		}
		return res.toString();
	}
	//dfs
	public String decodeString2(String s) {
		StringBuilder cur = new StringBuilder();
	    int num = 0;
	    for (int i = 0; i < s.length(); i++) {
	        if (Character.isDigit(s.charAt(i))) {
	            num = num * 10 + (s.charAt(i) - '0');
	        } else if (s.charAt(i) == '[') {
	            //find the matching ]
	            int begin = i;
	            i++;
	            int count = 1; 
	            while (count != 0) {
	                if (s.charAt(i) == '[') {
	                    count++;
	                } else if (s.charAt(i) == ']') {
	                    count--;
	                }
	                i++;
	            }
	            i--;
	            
	            String substr = decodeString(s.substring(begin + 1, i));
	            for (int k = 0; k < num; k++) {
	                cur.append(substr);
	            }
	            num = 0;
	        } else { 
	            cur.append(s.charAt(i));
	        }
	    }
	    return cur.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(decodeString("3[a]2[bc]"));
		System.out.println(decodeString("2[abc]3[cd]ef"));
		System.out.println(decodeString("100[leetcode]"));
	}
}
