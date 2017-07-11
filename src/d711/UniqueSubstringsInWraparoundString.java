package d711;

public class UniqueSubstringsInWraparoundString {
	//开始想利用类似arithmeticsilces中计算sub的个数方法
	//处理重复问题，如果一开始已经计算过单个字母，后来又计算了单个字幕在连续字母中的情况
	public static int findSubstringInWraproundString(String p) {
		int[] dp = new int[p.length() + 1];
        boolean[] used = new boolean[26];
        int result = 0;
        for(int i = 0; i < p.length(); i++){
            if(i == 0){
                dp[i] = 1;
                used[p.charAt(i) - 'a'] = true;
            }else{
                if(p.charAt(i) != p.charAt(i - 1)){
                    if(((p.charAt(i - 1) - 'a' + 1) % 26) == (p.charAt(i) -'a')){
                        dp[i] = dp[i - 1] + 1;
                    }else{
                        if(!used[p.charAt(i) - 'a']){
                            dp[i] = 1;
                            used[p.charAt(i) - 'a'] = true;
                        }else{
                            dp[i] = 0;
                        }
                    }
                }else{
                    dp[i] = 0;
                }
            }
            result += dp[i];
        }
        return result;
    }
	
	public int findSubstringInWraproundString2(String p) {
        // count[i] is the maximum unique substring end with ith letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];
        
        // store longest contiguous substring ends at current position.
        int maxLengthCur = 0; 

        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
                maxLengthCur++;
            }
            else {
                maxLengthCur = 1;
            }
            
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        
        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
    }
	public static void main(String[] args) {
		System.out.println(findSubstringInWraproundString("azab"));
	}
}
