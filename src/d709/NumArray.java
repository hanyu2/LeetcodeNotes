package d709;

public class NumArray {
	int[] sums;

	public NumArray(int[] nums) {
       sums = new int[nums.length + 1];
       for(int i = 0; i < nums.length; i++){
           sums[i + 1] = sums[i] + nums[i];
       }
    }

	public int sumRange(int i, int j) {
		return this.sums[j + 1] - this.sums[i];
	}
}
