package d713;

import java.util.HashMap;

public class ContinuousSubarraySum {
	//开始想求数组的和当中， 判断有没有和减去倍数在之前出现过
	//没有考虑k为负数， 也没考虑差为2
	//考虑差，应用map记录index， 初始为-1帮助计算个数
	public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int reminderSum = 0;
        for(int i = 0; i < nums.length; i++) {
            reminderSum += nums[i];
            if(k!=0) reminderSum %= k;
            if(map.containsKey(reminderSum)) {
                if(i - map.get(reminderSum) > 1) return true;
            } else {
                map.put(reminderSum, i);
            }
        }
        return false;
    }
}
