package d711;

public class PredictTheWinner {
	//解决分数问题，我试用了两个变量记录两个人的分数
	//最后计算谁的分数大
	//答案记录一个数，player2得的分数就是player1失去的分数
	//最后判断player1的分数是否大于0
	//计算得分失分用一个turn变量1， -1乘以分数
	//我的方法有bug， 没有maximize每轮两人的分数
	//每轮分数的max不是取start，end里的最大值， 还要考虑后面
	//https://leetcode.com/articles/predict-the-winner/#approach-1-using-recursion-accepted
	
	public static boolean predictTheWinner(int[] nums) {
		return search(nums, 0, nums.length - 1, true, 0, 0);
	}

	public static boolean search(int[] nums, int start, int end, boolean play, int sum1, int sum2) {
		if (start > end) {
			if (sum1 > sum2) {
				System.out.println(sum1);
				System.out.println(sum2);
				return true;
			}
			return false;
		}
		if (play) {
			if (search(nums, start + 1, end, !play, sum1 + nums[start], sum2)) {
				return true;
			}
			if (search(nums, start, end - 1, !play, sum1 + nums[end], sum2)) {
				return true;
			}
		} else {
			if (search(nums, start + 1, end, !play, sum1, sum2 + nums[start])) {
				return true;
			}
			if (search(nums, start, end - 1, !play, sum1, sum2 + nums[end])) {
				return true;
			}
		}
		return false;
	}
	//所以应该是后面算完返回到这一层，像minimax
	public boolean predictTheWinner2(int[] nums) {
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }
    public int winner(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + winner(nums, s + 1, e, -turn);
        int b = turn * nums[e] + winner(nums, s, e - 1, -turn);
        if(turn == 1){
            return Math.max(a, b);
        }else{
            return Math.min(a, b);
        }
    }
    
    public boolean predictTheWinner3(int[] nums) {
        return winner3(nums, 0, nums.length - 1) >= 0;
    }
    public int winner3(int[] nums, int s, int e) {
        if (s == e)
            return nums[s];
        int a = nums[s] - winner3(nums, s + 1, e);
        int b = nums[e] - winner3(nums, s, e - 1);
        return Math.max(a, b);
    }
    
    //有start， end, 考虑二维数组
    //用Integer声明数组可以了考虑数元素是否为空
    public boolean predictTheWinner4(int[] nums) {
    	int len = nums.length;
    	Integer[][] dp = new Integer[len][len];
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }
    public int predict(int[] nums, int start, int end, Integer[][] dp){
    	if(start == end){
    		return nums[start];
    	}
    	if(dp[start][end] != null){
    		return dp[start][end];
    	}else{
    		int a = nums[start] - predict(nums, start + 1, end, dp);
    		int b = nums[end] - predict(nums, start, end - 1, dp);
    		dp[start][end] = Math.max(a, b);
    	}
    	return dp[start][end];
    }
	public static void main(String[] args) {
		int[] nums = {1, 5, 2};
		System.out.println(predictTheWinner(nums));
	}
}
