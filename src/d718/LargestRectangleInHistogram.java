package d718;

public class LargestRectangleInHistogram {
	// 开始想维持两个数组
	// left是本元素到左最长长度，right是本元素到右最长长度
	// left扫的时候，如果本元素比左边小，本元素等于左边加一
	// 否则找左边第一个比他小的加1，否则是1
	// 有bug， [5, 6, 2, 3]
	public static int largestRectangleArea(int[] nums) {
		int n = nums.length;
		int[] left = new int[n];
		int[] right = new int[n];
		left[0] = 1;
		for (int i = 1; i < n; i++) {
			if (nums[i] <= nums[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				int j = i - 2;
				for (; j >= 0; j--) {
					if (nums[i] <= nums[j]) {
						left[i] = left[j] + 1;
						break;
					}
				}
				if (j == -1) {
					left[i] = 1;
				}
			}
		}
		right[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			if (nums[i] <= nums[i + 1]) {
				right[i] = right[i + 1];
			} else {
				int j = i + 2;
				for (; j < n; j++) {
					if (nums[i] <= nums[j]) {
						right[i] = right[j] + 1;
						break;
					}
				}
				if (j == n) {
					right[i] = 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(nums[i] * (left[i] + right[i] - 1), max);
		}
		return max;
	}

	public static int largestRectangleArea2(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int[] lessFromLeft = new int[height.length]; 
		int[] lessFromRight = new int[height.length];
		lessFromRight[height.length - 1] = height.length;
		lessFromLeft[0] = -1;
		for (int i = 1; i < height.length; i++) {
			int p = i - 1;
			while (p >= 0 && height[p] >= height[i]) {
				p = lessFromLeft[p];
			}
			lessFromLeft[i] = p;
		}
		for (int i = height.length - 2; i >= 0; i--) {
			int p = i + 1;
			while (p < height.length && height[p] >= height[i]) {
				p = lessFromRight[p];
			}
			lessFromRight[i] = p;
		}
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 1, 5, 6, 2, 3 };
		System.out.println(largestRectangleArea(nums));
	}

}
