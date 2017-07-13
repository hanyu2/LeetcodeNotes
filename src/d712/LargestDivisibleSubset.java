package d712;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
	public static List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		if(nums.length == 0){
			return res;
		}
		Arrays.sort(nums);//don't forget
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = 1;
		for(int i = 1; i < n; i++){
			for(int j = i; j >= 0; j--){
				if(nums[i] % nums[j] == 0){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		int maxIndex = 0;
		int maxLen = 1;
		for(int i = 1; i < n; i++){
			if(dp[i] > dp[maxIndex]){
				maxIndex = i;
				maxLen = dp[i];
			}
		}
		int maxValue = nums[maxIndex];
		for(int i = maxIndex; i >= 0; i--){
			if(maxValue % nums[i] == 0 && dp[i] == maxLen){
				res.add(nums[i]);
				maxValue = nums[i];
				maxLen--;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int[] nums = {2,3,8,9,27};
		largestDivisibleSubset(nums);
	}
}
