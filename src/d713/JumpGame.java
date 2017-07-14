package d713;

public class JumpGame {
	public static boolean canJump(int[] nums) {
		if(nums.length == 0){
            return false;
        }
        int max = nums[0];
        for(int i = 1; i <= max; i++){
            max = Math.max(max, nums[i] + i);
            if(max >= nums.length - 1){
                return true;
            }
        }
        return max >= nums.length - 1;
    }
	public static void main(String[] args) {
		int[] nums = {1};
		System.out.println(canJump(nums));
	}
}
