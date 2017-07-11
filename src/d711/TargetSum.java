package d711;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
	//存储中间结果
	//想好每层和的意义，是到这层之前的和
	//第一层要把两个值的可能性加起来
	//用encoding String encodeString = index + "->" + sum; 减少map dimension
	//https://discuss.leetcode.com/topic/76245/java-simple-dfs-with-memorization/9
	public static int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Map<Integer, Integer>> cache = new HashMap<Integer, Map<Integer, Integer>>();
        for(int i = 0; i < nums.length; i++){
            cache.put(i, new HashMap<Integer, Integer>());
        }
        search(0, nums, 0, S, cache);
        int total = 0;
        for(int key : cache.get(0).keySet()){
        	total += cache.get(0).get(key);
        }
        return total;
    }
    public static int search(int index, int[] nums, int sum, int S, Map<Integer, Map<Integer, Integer>> cache){
        if(index == nums.length){
            if(sum == S){
                return 1;
            }
            return 0;
        }
        if(cache.get(index).containsKey(sum)){
            return cache.get(index).get(sum);
        }
        int plus = search(index + 1, nums, sum + nums[index], S, cache);
        int minus = search(index + 1, nums, sum - nums[index], S, cache);
        Map<Integer, Integer> map = cache.get(index);
        if(map.containsKey(sum)){
            map.put(sum, map.get(sum) + plus + minus);
        }else{
            map.put(sum, plus + minus);
        }
        return plus + minus;
    }
    //学习如何用dp找到数组内的subset和为某值的可能性个数
    //https://discuss.leetcode.com/topic/76243/java-15-ms-c-3-ms-o-ns-iterative-dp-solution-using-subset-sum-with-explanation
    public int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum2(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum2(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    }
    public static void main(String[] args) {
    	int[] nums = {1, 1, 1, 1, 1};
		System.out.println(findTargetSumWays(nums, 3));
		int[] nums2 = {0,0,0,0,0,0,0,0,1};
		System.out.println(findTargetSumWays(nums2, 1));
    	int[] nums3 = {9,7,0,3,9,8,6,5,7,6};
    	System.out.println(findTargetSumWays(nums3, 2));
	}
}
