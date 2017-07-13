package d713;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	//一开始直接选择返回最后一个元素，应该是返回中间最大的，最后一个元素不一定最大
	//一开始想前找，找到比当前元素小的，直接把前面元素的个数加一赋值退出
	//failed on {1, 3, 6, 7, 9, 4, 10, 5, 6}
	//10比4大，但4不是最长， 9最长
	public static int lengthOfLIS(int[] nums) {
		if(nums.length <= 1){
            return nums.length;
        }
        int max = 1;
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            if(dp[i] == 0){
                dp[i] = 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
	//binary search
	public int lengthOfLIS2(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;
        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }
        return len;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,3,6,7,9,4,10,5,6};
		System.out.println(lengthOfLIS(nums));
	}
}
