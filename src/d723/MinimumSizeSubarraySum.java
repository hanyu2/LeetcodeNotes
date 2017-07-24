package d723;

public class MinimumSizeSubarraySum {
	//最后判断是否有结果存在
	public int minSubArrayLen(int s, int[] nums) {
        int right = 0;
        int left = -1;
        int sum = 0;
        int len = nums.length;
        for(; right < nums.length; right++){
            sum += nums[right];
            while(left <= right && sum >= s){
                len = Math.min(len, right - left);
                sum -= nums[++left];
            }
        }
        return left == -1 ? 0 : len;
    }
}
