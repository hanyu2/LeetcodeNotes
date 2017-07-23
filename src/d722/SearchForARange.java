package d722;

public class SearchForARange {
	public static int[] searchRange(int[] nums, int target) {
		int[] res = { -1, -1 };
		if (nums.length == 0) {
			return res;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) >> 1;
			if (target > nums[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		if (nums[right] != target) {
			return res;
		} else {
			res[0] = left;
		}

		right = nums.length - 1;
		while (left < right) {
			int mid = (left + right) / 2 + 1;
			if (target < nums[mid]) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		res[1] = right;
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 5, 7, 7, 8, 8, 10 };
		System.out.println(searchRange(nums, 8));
	}
}
