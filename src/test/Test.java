package test;

public class Test {
	public static void findTargetSumWays(int[] nums, int S) {
		search(0, 0, S, nums, new int[nums.length]);
	}

	private static void search(int index, int sum, int S, int[] nums, int[] res) {
		if(index == nums.length){
			if(sum == S){
				System.out.println();
				for(int i = 0; i < res.length; i++){
					System.out.print(res[i]);
				}
			}
			return;
		}
		res[index] = nums[index];
		search(index + 1, sum + nums[index], S, nums, res);
		res[index] = -nums[index];
		search(index + 1, sum - nums[index], S, nums, res);
	}
	public static void main(String[] args) {
		int[] nums = {9,7,0,3,9,8,6,5,7,6};
		findTargetSumWays(nums, 2);
	}
}
