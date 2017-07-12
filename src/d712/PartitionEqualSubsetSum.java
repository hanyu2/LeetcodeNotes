package d712;

public class PartitionEqualSubsetSum {
	//开始想法把所有的数排序， 然后从前面找到前n的和是总和的一半
	//错误， [1， 2， 3， 4， 5， 6， 7]
	//[1,3,4,6], [2,5,7] 不一定肯定是最小的几个数能组成subset
	//仍需排序，搜索下一个能使本subset和为total一般的数字是否存在
	//不要用total/2， 用sum*2判断，除法有round问题
	//后来想直接搜索，从大往小搜，当和大于一半退出，这样能更早退出
	//超时，后来想把数组分成大致相等的两块，一半搜索找出所有和的可能性，一半搜索时找另一半有没有能组成一半的可能
	//减小搜索深度，超时
	//看讨论题目01背包，尝试组成一个subset，对于当前元素要或者不要
	//01背包需要二维数组
	public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % 2 == 1){
            return false;
        }
        sum /= 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;
        for(int j = 1; j <= sum; j++){
            dp[0][j] = false;   
        }
        for(int i = 1; i <= n; i++){
            dp[i][0] = true;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                dp[i][j] = dp[i - 1][j];
                if(j >= nums[i - 1]){
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
	
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7};
		int[] nums2 = {1, 2, 5};
		//System.out.println(canPartition(nums2));
	}
}
