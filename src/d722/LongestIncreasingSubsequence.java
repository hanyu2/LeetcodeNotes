package d722;

public class LongestIncreasingSubsequence {
	//1.不能在binary search之前加判断，比如当前元素小于最后一个元素，len为0
	//会造成无法插入更大的数
	//2.search的时候要把当前的len传过去
	public static int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		int len = 0;
		for (int num : nums) {
			int index = search(dp, len, num);
			if(index == len){
				dp[len] = num;
				len++;
			}else{
				dp[index] = num;
			}
		}
		return len;
	}

	public static int search(int[] dp, int len,int target) {
		int left = 0;
		int right = len - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (dp[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(lengthOfLIS(nums));
	}
}
