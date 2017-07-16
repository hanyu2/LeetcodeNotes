package d716;

public class SplitArrayLargestSum {
	// https://discuss.leetcode.com/topic/61324/clear-explanation-8ms-binary-search-java
	public int splitArray(int[] nums, int m) {
		int max = 0;
		long sum = 0;
		for (int i = 0; i < nums.length; i++) {
			max = Math.max(max, nums[i]);
			sum += nums[i];
		}
		if (m == 1) {
			return (int) sum;
		}
		long left = max;
		long right = sum;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (valid(mid, nums, m)) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return (int) left;
	}

	public boolean valid(long target, int[] nums, int m) {
		long sum = 0;
		int count = 1;
		for (int num : nums) {
			sum += num;
			if (sum > target) {
				sum = num;
				count++;
				if (count > m) {
					return false;
				}
			}
		}
		return true;
	}
}
