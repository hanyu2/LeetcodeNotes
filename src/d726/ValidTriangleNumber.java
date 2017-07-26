package d726;

import java.util.Arrays;

public class ValidTriangleNumber {
	// failed on [2, 2, 3, 4] 计算2,2,4
	public int triangleNumber(int[] nums) {
		int count = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int len = nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[left] + nums[right];
				if (sum > len) {
					count += right - left;
					right--;
				} else {
					left++;
				}
			}
		}
		return count;
	}

	public int triangleNumber2(int[] nums) {
		int n = nums.length;
		if (n < 3)
			return 0;
		Arrays.sort(nums);
		int ans = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			for (int left = i + 1, right = i + 2; right < nums.length; right++) {
				while (left < right && nums[i] + nums[left] <= nums[right])
					left++;
				ans += right - left;
			}
		}
		return ans;
	}
}
