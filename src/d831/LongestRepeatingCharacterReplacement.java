package d831;

public class LongestRepeatingCharacterReplacement {
	public int characterReplacement(String s, int k) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int max = 0;
		int[] ch = new int[26];
		char[] str = s.toCharArray();
		for (int i = 0, j = 0; i < s.length(); i++) {
			while (j < s.length()) {
				ch[str[j] - 'A']++;
				if (count(ch) > k) { // If exceed k, break
					ch[str[j] - 'A']--;
					break;
				}
				j++;
			}
			max = Math.max(max, j - i);
			ch[str[i] - 'A']--;
		}
		return max;
	}

	// Count the number of character that is different to the longest character
	public int count(int[] ch) {
		int max = 0;
		int sum = 0;
		for (int val : ch) {
			sum += val;
			max = Math.max(max, val);
		}
		return sum - max;
	}

	public int characterReplacement2(String s, int k) {
		int maxLen = 0;
		for (int l = 0; l < 26; l++) {
			char c = (char) ('A' + l); // repeated char we are looking for
			int i = 0, j = 0, count = 0;
			while (j < s.length()) {
				char cur = s.charAt(j);
				if (cur != c)
					count++;

				// make the substring valid again
				while (count > k) {
					if (s.charAt(i) != c)
						count--;
					i++;
				}

				// update maximun len
				maxLen = Math.max(maxLen, j - i + 1);
				j++;
			}
		}
		return maxLen;
	}
}
