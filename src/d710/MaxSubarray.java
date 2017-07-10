package d710;

public class MaxSubarray {
	//记录0到i的和
	//如果和小于0， 0到i一定不会在subarray中
	public int maxSubArray(int[] nums) {
		int maxSum = nums[0];
		int currSum = 0;
		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];
			if (maxSum < currSum)
				maxSum = currSum;
			if (currSum < 0)
				currSum = 0;
		}
		return maxSum;
	}
}
