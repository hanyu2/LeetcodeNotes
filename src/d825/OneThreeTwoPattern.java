package d825;

import java.util.Stack;

public class OneThreeTwoPattern {
	//http://www.cnblogs.com/fcyworld/p/6160687.html
	public boolean find132pattern(int[] nums) {
        if(nums.length < 3){
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int third = Integer.MIN_VALUE;
        for(int i = nums.length - 1; i >= 0; i--){
            if(nums[i] < third){
                return true;
            }else{
                while(!stack.isEmpty() && nums[i] > stack.peek()){
                    third = stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

	public static void main(String[] args) {
		int[] nums = {1,0,1,-4,-3};
		OneThreeTwoPattern ott = new OneThreeTwoPattern();
		System.out.println(ott.find132pattern(nums));
	}
}
class Wrap{
	int index;
	int val;
	public Wrap(int index, int val){
		this.index = index;
		this.val = val;
	}
}
