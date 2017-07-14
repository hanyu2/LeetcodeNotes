package d714;

public class BurstBallon {
	//先遍历，找中间的数，找最大， 在找最半边最大，右半边最大
	//实际上只需要两个重点， 二维数组
	public int maxCoins(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int n = nums.length; 

        int[] arr = new int[n + 2];
        arr[0] = 1;
        for(int i = 1; i <= n; i++){
        	arr[i] = nums[i - 1];
        }
        arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];
        return search(0, n + 1, arr, dp);
    }
    public int search(int start, int end, int[] nums, int[][] dp){
    	if(start == end - 1){
    		return 0;
    	}
        if(dp[start][end] != 0){
            return dp[start][end];
        }
        int max = 0;
        for(int i = start + 1; i < end; i++){
        	max = Math.max(max, nums[start]*nums[i]*nums[end] + search(start, i , nums, dp) + search(i , end, nums, dp));
        }
        dp[start][end] = max;
        return max;
    }
}
