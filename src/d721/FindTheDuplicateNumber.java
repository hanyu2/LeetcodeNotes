package d721;

public class FindTheDuplicateNumber {
	//O(nlogn)
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		int low = matrix[0][0];
		int hi = matrix[n - 1][n - 1];
		while (low < hi) {
			int mid = low + (hi - low) / 2;
			int count = 0;
			for (int i = 0; i < n; i++) {
				int j = 0;
				while (j < n && matrix[i][j] <= mid) {
					j++;
				}
				count += j;
			}
			if (count < k) {
				low = mid + 1;
			} else {
				hi = mid;
			}
		}
		return low;
	}
	//所有的数值小于数组方位，因为有重复数字，所以会从多个有相同数值的点跳到同一个点
	public int findDuplicate(int[] nums) {
		if (nums.length > 1) {
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while (fast != slow) {
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return -1;
	}
}
