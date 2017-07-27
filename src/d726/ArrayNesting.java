package d726;

import java.util.HashSet;
import java.util.Set;

public class ArrayNesting {
	public static int arrayNesting(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < n; i++){
            set.add(i);
        }
        int max = 0;
        while(!set.isEmpty()){
            Set<Integer> cycle = new HashSet<Integer>();
            int nextIndex = set.iterator().next();
            while(!cycle.contains(nums[nextIndex])){
            	cycle.add(nums[nextIndex]);
            	set.remove(nums[nextIndex]);
            	nextIndex = nums[nextIndex];
            }
            max = Math.max(max, cycle.size());
            if(max >= nums.length / 2 + 1){//!!!len为单数有bug
            	break;
            }
        }
        return max;
    }
	//把用过的元素设成max
	public int arrayNesting2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
	public static void main(String[] args) {
		int[] nums = {0, 2, 1};
		System.out.println(arrayNesting(nums));
	}
}
