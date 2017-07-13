package d712;

public class CombinationSum4 {
	//01
	public static int combinationSum4(int[] nums, int target) {
		int m = nums.length;
		int n = target;
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= m; j++){
				if(i >= nums[j - 1]){
					dp[i] += dp[i - nums[j - 1]];
				}
			}
		}
		return dp[n];
	}
	public static void main(String[] args) {
		int[] nums = {1, 2, 3};
		System.out.println(combinationSum4(nums, 4));
	}
}
