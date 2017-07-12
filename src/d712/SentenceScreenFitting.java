package d712;

public class SentenceScreenFitting {
	// 注意test case 3
	/*
	 * 我刚开始想的是便利句子，每个单词分别处理，但是这种做法很不高效
	 * ，因为有可能屏幕的宽度特别大，而单词可能就一两个，那么我们这样遍历的话就太浪费时间了
	 * ，应该直接用宽度除以句子加上空格的长度之和，可以快速的得到能装下的个数
	 */	
	//http://www.cnblogs.com/grandyang/p/5975426.html
	public int wordsTyping(String[] sentence, int rows, int cols) {
		if (sentence == null || sentence.length == 0 || rows <= 0 || cols <= 0)
			return 0;
		String s = "";
		for (String str : sentence) {
			s += str + " ";
		}
		int l = s.length();
		int start = 0;
		for (int i = 0; i < rows; i++) {
			start += cols;
			if (s.charAt(start % l) == ' ') {
				start++;
			} else {
				while (start > 0 && s.charAt((start - 1) % l) != ' ') {
					start--;
				}
			}
		}
		return start / l;
	}

}
