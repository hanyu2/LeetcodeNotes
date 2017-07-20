package d720;

public class TwoSum2 {
	// binary search O(nlogn)
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				int sum = nums[mid] + nums[i];
				if (sum == target) {
					res[0] = i + 1;
					res[1] = mid + 1;
					return res;
				} else if (sum < target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return res;
	}
	//two pointers
	public int[] twoSum2(int[] nums, int target) {
		int[] res = new int[2];
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int sum = nums[left] + nums[right];
			if (sum == target) {
				res[0] = left + 1;
				res[1] = right + 1;
				return res;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return res;
	}
}
