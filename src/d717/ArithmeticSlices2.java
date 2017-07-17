package d717;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlices2 {
	//开始想用一个嵌套map，外层存数，内层存当前数差为x的长度
	//无法处理有重复数字[2, 2, 3, 4]
	public static int numberOfArithmeticSlices(int[] nums) {
        if(nums.length <= 2){
        	return 0;
        }
        Arrays.sort(nums);
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        for(int num : nums){
        	map.put(num, new HashMap<Integer, Integer>());
        }
        int n = nums.length;
        int min = nums[0];
        int range = 1;
        for(int i = n - 1; i >= 2; i--){
        	if(map.containsKey((nums[i] - min) / 2)){
        		range = (nums[i] - min) / 2;
        		break;
        	}
        }
        int count = 0;
        for(int i = 1; i < nums.length; i++){
        	for(int j = 1; j <= range; j++){
        		if(map.containsKey(nums[i] - j) && map.containsKey(nums[i] - j * 2)){
        			int num = (map.get(nums[i] - j).containsKey(j) ? map.get(nums[i] - j).get(j) : 0) + 1;
        			count += num;
        			map.get(nums[i]).put(j, num);
        		}
        	}
        }
        return count;
    }
	//标准方法思路一样，用数组避免数字重复
	public int numberOfArithmeticSlices2(int[] A) {
	    int res = 0;
	    Map<Integer, Integer>[] map = new Map[A.length];
	    for (int i = 0; i < A.length; i++) {
	        map[i] = new HashMap<>(i);
	        	
	        for (int j = 0; j < i; j++) {
	            long diff = (long)A[i] - A[j];
	            if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
	        		
	            int d = (int)diff;
	            int c1 = map[i].containsKey(d) ? map[i].get(d) : 0;
	            int c2 = map[j].containsKey(d) ? map[j].get(d) : 0;
	            res += c2;
	            map[i].put(d, c1 + c2 + 1);
	        }
	    }
	    return res;
	}
	public static void main(String[] args) {
		int[] nums = {2, 4, 6, 8, 10};
		System.out.println(numberOfArithmeticSlices(nums));
	}
}
